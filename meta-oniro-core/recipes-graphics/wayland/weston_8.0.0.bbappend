# SPDX-FileCopyrightText: Huawei Inc.
# SPDX-License-Identifier: Apache-2.0

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://systemd-notify.weston-start"
SRC_URI_append_libc-musl = " file://dont-use-plane-add-prop.patch"

do_install_append() {
        install -Dm 644 ${WORKDIR}/systemd-notify.weston-start ${D}${datadir}/weston-start/systemd-notify
}
