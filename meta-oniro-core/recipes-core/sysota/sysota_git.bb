# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

DESCRIPTION = "Robust, unattended update system for Linux gateways"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSES/Apache-2.0.txt;md5=c846ebb396f8b174b10ded4771514fcc"

SRC_URI = "git://booting.oniroproject.org/distro/components/sysota.git;protocol=https;branch=main \
           file://sysotad.conf \
"
SRCREV = "3cb40a1392fd2b849db711c6a9a050d00bad8c93"
S = "${WORKDIR}/git"

# This package is built with go-mod as well as with make.
#
# The go-mod build is done in the ${B} directory but is not sufficient for
# packaging, as the package contains additional data files, manual pages and
# systemd units. The make build is done inside the ${B}/make-build
# sub-directory and is similar to what upstream CI is testing.
#
# The way go-mod prepares the build tree in go_do_configure clobbers ${B}/ and
# replaces it with a symbolic link. The make build is done in _append functions
# to see the final file system structure.
#
# To avoid compiling the code twice a trick is employed. We know go-mod.bbclass
# has already compiled the project and has stashed the binary into
# ${B}/${GO_BUILD_BINDIR}. In do_compile:append() we move those binaries over
# to where the project makefile would have built them. In addition we pass
# Go.Cmd=/bin/false to make sure that make does not attempt to compile anything
# successfully for the second time.
inherit go-mod systemd features_check

# Go import path of SystemOTA.
GO_IMPORT = "booting.oniroproject.org/distro/components/sysota"

# The make side of the build depends on zmk. We cannot rely on zmk.bbclass as
# that internally depends on autotools.bbclass but using autotools.bbclass and
# go-mod.bbclass in one recipe is not supported.
DEPENDS += "zmk-native pkgconfig-native systemd"
EXTRA_OEMAKE += "-I${RECIPE_SYSROOT_NATIVE}/usr/include"

do_configure[cleandirs] =+ "${B}/make-build"

do_configure:append() {
    # Note that path ${S}/src/${GO_IMPORT} is hard-coded in go.bbclass.

    # Depending on what The SRC_URI points to we may be building from a git
    # repository or an upstream release tarball.  In the latter case, the
    # tarball contains an autotools-like bundled copy of the zmk library for
    # Make. Remove that copy so that we always build with zmk provided by
    # Yocto.
    rm -rf ${S}/src/${GO_IMPORT}/zmk
    rm -f ${S}/src/${GO_IMPORT}/z.mk
    rm -f ${S}/src/${GO_IMPORT}/configure

    # Re-create the configure script in the source tree.
    oe_runmake --warn-undefined-variables -C ${S}/src/${GO_IMPORT} configure

    # Run the generated configure script passing --prefix=, --libexecdir= and
    # --sysconfdir=. The paths are used in generated data files, most notably
    # systemd units.
    cd ${B}/make-build/ && ${S}/src/${GO_IMPORT}/configure \
        --prefix=${prefix} \
        --libexecdir=${libexecdir} \
        --sysconfdir=${sysconfdir}
}

do_compile:append() {
    # See the "trick" paragraph above. If additional binaries are added to the
    # package, they should be moved as well.
    mkdir -p ${B}/make-build/cmd/sysotad
    mv ${B}/${GO_BUILD_BINDIR}/sysotad ${B}/make-build/cmd/sysotad

    # Run the upstream build system which prepares systemd units and manual pages.
    oe_runmake -C ${B}/make-build --warn-undefined-variables Go.Cmd=/bin/false
}

do_install:append() {
    oe_runmake -C ${B}/make-build --warn-undefined-variables install DESTDIR=${D}

    # Install the built-in configuration file.
    # See below for machine-specific overrides.
    install -D -m 0644 ${WORKDIR}/sysotad.conf ${D}${libdir}/sysota/sysotad.conf
}

# Include D-Bus configuration files in the primary package. Those contain bus
# policy for talking to SystemOTA as well as the D-Bus activation service file
# (not to be confused with the systemd service unit).
FILES:${PN} += "${datadir}/dbus-1"

# Include the built-in configuration file.
FILES:${PN} += "${libdir}/sysota/sysotad.conf"

# SystemOTA depends on RAUC and unsquashfs and mksquashfs (for tests).
RDEPENDS:${PN} += "squashfs-tools rauc"

# SystemOTA test scripts technically depend on Bash for spread integration.
RDEPENDS:${PN}-dev += "bash"

# SystemOTA heavily depends on Systemd. Individual portions are implemented as
# systemd services.
REQUIRED_DISTRO_FEATURES = "systemd"
SYSTEMD_SERVICE:${PN} = "sysotad.service"

# Specific MACHINE configurations have sysotad.conf which provides the right
# settings, like the boot loader type.
FILESEXTRAPATHS:prepend:raspberrypi4-64 := "${THISDIR}/files/raspberrypi4:"

# Make the SystemOTA package machine-specific. This lets us put the specific
# configuration file, which encodes boot loader type, into it safely.
PACKAGE_ARCH = "${MACHINE_ARCH}"

# Persist SystemOTA state directory.
inherit writables

WRITABLE_TYPE[sysota] = "persistent"
WRITABLES = "sysota"
WRITABLE_PATH[sysota] = "/var/lib/sysota"
