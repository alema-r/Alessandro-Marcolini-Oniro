# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

SRC_URI_append = " \
    file://0001-xlat-update-IPPROTO_-constants.patch \
    file://0002-xlat-add-IPPROTO_ETHERNET-to-inet_protocols.patch \
    file://0003-xlat-remove-IPPROTO_MAX.patch \
    file://0004-Regenerate-xlat-headers.patch \
    "
