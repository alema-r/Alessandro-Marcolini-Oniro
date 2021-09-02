# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
SRC_URI += "file://busybox-1.31.1-Wformat-security.patch \
	file://allscenarios.cfg"
