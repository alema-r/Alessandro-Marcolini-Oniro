# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

# Make the RAUC package machine-specific. This lets us put the specific configuration
# file, which encodes the slot configuration, into it safely.
PACKAGE_ARCH = "${MACHINE_ARCH}"

# Generate RAUC's system.conf configuration file and place it in the workdir
# where it is picked up by the logic from rauc-target.inc.
#
# FIXME(zyga): The file defines RAUC compatible string which is technically
# something that SystemOTA should be responsible for (make/model and remodel
# operations). This should be addressed before re-model is supported.
SRC_URI:append = " \
  file://system.conf.in \
  "

# Define the slots and compatible strings for reference boards.
RAUC_COMPAT:raspberrypi4-64 := "Raspberry Pi 4"
RAUC_SLOT_A:raspberrypi4-64 := "/dev/mmcblk0p2"
RAUC_SLOT_B:raspberrypi4-64 := "/dev/mmcblk0p3"

RAUC_COMPAT:qemux86 := "QEMU x86"
RAUC_SLOT_A:qemux86 := "/dev/sda2"
RAUC_SLOT_B:qemux86 := "/dev/sda3"

RAUC_COMPAT:qemux86-64 := "QEMU x86-64"
RAUC_SLOT_A:qemux86-64 := "/dev/sda2"
RAUC_SLOT_B:qemux86-64 := "/dev/sda3"

RAUC_COMPAT:seco-intel-b68 := "SECO Alvin"
RAUC_SLOT_A:seco-intel-b68 := "/dev/sda2"
RAUC_SLOT_B:seco-intel-b68 := "/dev/sda3"

do_install:prepend() {
    sed \
        -e 's,@RAUC_COMPAT@,${RAUC_COMPAT},g' \
        -e 's,@RAUC_SLOT_A@,${RAUC_SLOT_A},g' \
        -e 's,@RAUC_SLOT_B@,${RAUC_SLOT_B},g' \
        <"${WORKDIR}/system.conf.in" >"${WORKDIR}/system.conf"
}


# Use the known insecure public key which is a part of this layer as the key
# baked into our reference images.
#
# This key is meant to be insecure for two reasons:
#
# 1) There are no binary updates available for the reference images, nor there
# are any binary updates that come out which are production-grade and can be
# deployed directly. This is the responsibility of the integrator for a
# specific product. Having a known-insecure key should discourage people from
# using artifacts coming out of CI as updates for anything not related to
# testing.
#
# 2) There is no need to use a sophisticated secure key storage environment in
# order to build reference images to allow CI-made artifacts to be signed and
# useful for testing.

# Set RAUC_KEYRING_FILE which is is defined in meta-rauc. This still allows any
# downstream overrides to define it more strongly and use a different private
# key. This variable is also set up to append to SRC_URI, so no additional
# declaration is needed.
RAUC_KEYRING_FILE ?= "oniro-insecure-cert.pem"

do_install:append() {
    if [ -f ${D}${sysconfdir}/rauc/oniro-insecure-cert.pem ]; then
        bbwarn "The image is using a known, insecure test key for verifying RAUC bundles. Do not use this in production systems."
    fi
}

# Install Oniro specific override for RAUC state directory.

SRC_URI:append = " \
  file://rauc-state-dir.conf \
  "

FILES:${PN}-service += "\
	${systemd_unitdir}/system/rauc.service.d/*.conf \
	"

do_install:append() {
    install -D -m 644 ${WORKDIR}/rauc-state-dir.conf --target-directory=${D}${systemd_unitdir}/system/rauc.service.d/
}
