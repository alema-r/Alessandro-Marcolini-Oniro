# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: MIT

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"
SRC_URI += "\
	file://0001-feat-support-cpu-limit-by-cgroups-on-linux.patch \
	file://0002-Introduce-MAXLOAD_NINJA-for-setting-average-load.patch \
"
