# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

FILESEXTRAPATHS:prepend := "${THISDIR}/linux:"

SRC_URI += "file://misc.cfg"
SRC_URI += "file://squashfs.cfg"
SRC_URI += "file://hardening_allocator.cfg"
SRC_URI += "file://hardening_allocator_perf.cfg"
SRC_URI += "file://hardening_disable_misc.cfg"
SRC_URI += "file://hardening_dmesg.cfg"
SRC_URI += "file://hardening_fortify_source.cfg"
SRC_URI += "file://hardening_memory.cfg"
# SRC_URI += "file://hardening_oops.cfg"
SRC_URI += "file://hardening_toolchain.cfg"
SRC_URI += "file://hardening_usercopy.cfg"
SRC_URI += "file://hardening_validation_checks.cfg"
SRC_URI += "file://rauc.cfg"

# For the gcc-plugins build of the kernel we need to ensure the right include
# path for headers is picked up to use the natively build dependencies.
export HOSTCXXFLAGS = " -I${RECIPE_SYSROOT_NATIVE}/usr/include"

SRC_URI:append:qemuarm-efi = " file://defconfig"
SRC_URI:append:qemuarm64-efi = " file://defconfig"
