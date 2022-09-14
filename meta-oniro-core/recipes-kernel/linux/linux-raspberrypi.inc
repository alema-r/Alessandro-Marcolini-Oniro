# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

FILESEXTRAPATHS:prepend := "${THISDIR}/linux:"

SRC_URI += "\
    file://squashfs.cfg \
    file://rauc.cfg \
    "
