# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

# For specific MACHINE configurations, provide a pre-baked RAUC system config
# file. This confi file must be paired with equally tailored SystemOTA config
# file.
#
# FIXME(zyga): The file defines RAUC compatible string which is technically
# something that SystemOTA should be responsible for (make/model and remodel
# operations). This should be addressed before re-model is supported.
FILESEXTRAPATHS_prepend_raspberrypi4-64 := "${THISDIR}/files/raspberrypi4:"

# Make the RAUC package machine-specific. This lets us put the specific configuration
# file, which encodes the slot configuration, into it safely.
PACKAGE_ARCH = "${MACHINE_ARCH}"

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
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

do_install_append() {
    if [ -f ${D}${sysconfdir}/rauc/oniro-insecure-cert.pem ]; then
        bbwarn "The image is using a known, insecure test key for verifying RAUC bundles. Do not use this in production systems."
    fi
}
