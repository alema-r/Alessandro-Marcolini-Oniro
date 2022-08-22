# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

require recipes-kernel/linux/linux-oniro-tweaks-all.inc

SRC_URI += "file://fix-efi.cfg-drop-acpi-dependency.patch;patchdir=${KMETA}"
 
KERNEL_FEATURES:append = " ${@bb.utils.contains("MACHINE_FEATURES", "efi", " cfg/efi.scc", "", d)}"
