# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

# Backport 3 upstreamed patches to make edk2 work with gcc 12.x

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"
SRC_URI += "file://0003-edk2-DevicePath-gcc12-workaround.patch"
