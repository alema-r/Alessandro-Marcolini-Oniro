# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: MIT

FILESEXTRAPATHS:prepend := "${THISDIR}/linux:"

SRC_URI += "\
    file://squashfs.cfg \
    file://rauc.cfg \
    "
