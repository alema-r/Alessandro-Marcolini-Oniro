# SPDX-FileCopyrightText: 2021 Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://0001-components-met-Use-API-v2.patch"

RRECOMMENDS_${PN} += "smart-home-homeassistant-plugin"
