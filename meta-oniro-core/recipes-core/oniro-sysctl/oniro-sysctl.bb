# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

SUMMARY = "Oniro project specific sysctl settings"
SECTION = "base"
DESCTIPTION = "This recipes provides a set of Oniro Project specific settings for the kernel hardening."
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

SRC_URI = 	" 				\
		file://oniro-bpf.conf 		\
		file://oniro-general.conf 	\
		file://oniro-net-ipv4.conf 	\
		file://oniro-net-ipv6.conf 	\
		"
inherit allarch


do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
	install -d "${D}/${sysconfdir}/sysctl.d"
	install -m 0644 "${WORKDIR}/oniro-general.conf" "${D}${sysconfdir}/sysctl.d/"
	install -m 0644 "${WORKDIR}/oniro-net-ipv4.conf" "${D}${sysconfdir}/sysctl.d/"
	install -m 0644 "${WORKDIR}/oniro-net-ipv6.conf" "${D}${sysconfdir}/sysctl.d/"
	install -m 0644 "${WORKDIR}/oniro-bpf.conf" "${D}${sysconfdir}/sysctl.d/"
}

FILES:${PN} += "${sysconfdir}/sysctl.d/oniro-*"
