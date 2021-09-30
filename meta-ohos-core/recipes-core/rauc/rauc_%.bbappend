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
