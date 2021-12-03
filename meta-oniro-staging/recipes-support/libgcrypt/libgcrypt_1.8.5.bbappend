# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://CVE-2021-33560_2.patch"

# A fix marked as a fix for CVE-2021-33560 in dunfell is in fact
# fixing CVE-2021-40528. Mark it as whitelisted until solved properly
# (renaming the fix in the upstream)
# For the mismatch between CVE-2021-33560 and CVE-2021-40528 see
# https://dev.gnupg.org/T5328
CVE_CHECK_WHITELIST += "CVE-2021-40528"
