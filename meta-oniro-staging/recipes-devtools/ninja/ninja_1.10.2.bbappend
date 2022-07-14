# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"
SRC_URI += "\
	file://0001-feat-support-cpu-limit-by-cgroups-on-linux.patch \
"
