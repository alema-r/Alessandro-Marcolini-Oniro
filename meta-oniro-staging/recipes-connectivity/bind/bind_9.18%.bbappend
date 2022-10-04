# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: MIT

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"
SRC_URI += "file://bind-libunwind-header.patch"
