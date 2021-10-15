# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

FILESEXTRAPATHS_prepend := "${THISDIR}/linux:"

SRC_URI += "file://squashfs.cfg"

# Make rootfstype defined based on ROOT_FSTYPE (selected be wic configuration)
CMDLINE = "dwc_otg.lpm_enable=0 ${SERIAL} root=/dev/mmcblk0p2 rootfstype=${ROOT_FSTYPE} rootwait ro"
