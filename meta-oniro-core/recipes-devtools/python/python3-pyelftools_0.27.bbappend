# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

# Add DWARF-5 support, based on upstream
SRC_URI:append = " \
	file://dwarf5-constants.patch \
	file://dwarf5.patch \
"
