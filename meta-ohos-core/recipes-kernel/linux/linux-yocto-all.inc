# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

FILESEXTRAPATHS_prepend := "${THISDIR}/linux:"

SRC_URI += "file://misc.cfg"

# For the gcc-plugins build of the kernel we need to ensure the right include
# path for headers is picked up to use the natively build dependencies.
export HOSTCXXFLAGS = " -I${RECIPE_SYSROOT_NATIVE}/usr/include"
