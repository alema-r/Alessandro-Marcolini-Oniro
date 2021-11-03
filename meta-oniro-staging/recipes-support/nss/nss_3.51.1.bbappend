# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
SRC_URI += "file://CVE-2020-12403.patch file://CVE-2020-12403_2.patch"

