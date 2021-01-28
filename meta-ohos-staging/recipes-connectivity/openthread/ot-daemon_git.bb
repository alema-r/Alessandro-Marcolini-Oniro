SUMMARY = "OpenThread Daemon (OT Daemon) is an OpenThread POSIX build mode that runs OpenThread as a service."
SECTION = "net"
LICENSE = "BSD-3-Clause & Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=543b6fe90ec5901a683320a36390c65f \
                    file://third_party/nlbuild-autotools/repo/LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

# While we have more third_party code with different licenses here we do not use
# them in the posix daemon build.

SRC_URI = "gitsm://github.com/openthread/openthread.git;protocol=https"

PV = "0.1+git${SRCPV}"
SRCREV = "4d50cbadbc3c584c7174f4be41fe65e2016a7ece"

S = "${WORKDIR}/git"

do_compile() {
    # TODO check again if we could avoid this before upstreaming
    ./bootstrap
    oe_runmake -f src/posix/Makefile-posix DAEMON=1 HOST=${BUILD_SYS}
}

do_install() {
    install -d ${D}/${sbindir}
    install -m 755 output/posix/bin/* ${D}/${sbindir}
}
