# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: MIT

SUMMARY = "Oniro project specific modprobe settings"
SECTION = "base"
DESCRIPTION = "This recipes provides a set of Oniro Project specific settings \
for managing kernel module"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

SRC_URI = "file://oniro-kernel-mod.conf"
inherit allarch

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
	install -D -m 0644 "${WORKDIR}/oniro-kernel-mod.conf" "${D}${sysconfdir}/modprobe.d/oniro-kernel-mod.conf"
}

FILES:${PN} += "${sysconfdir}/modeprobe.d/oniro-kernel-mod.conf"
