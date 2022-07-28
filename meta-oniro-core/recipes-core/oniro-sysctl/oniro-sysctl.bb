# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

SUMMARY = "Oniro project specific sysctl settings"
SECTION = "base"
DESCTIPTION = "This recipes provides a set of Oniro Project specific settings for the kernel hardening."
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

SRC_URI = " \
	file://10-oniro-bpf.conf \
	file://10-oniro-general.conf \
	file://10-oniro-net-ipv4.conf \
	file://10-oniro-net-ipv6.conf \
	"
inherit allarch

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
	install -d "${D}/${sysconfdir}/sysctl.d"
	install -m 0644 "${WORKDIR}/10-oniro-general.conf" "${D}${sysconfdir}/sysctl.d/"
	install -m 0644 "${WORKDIR}/10-oniro-net-ipv4.conf" "${D}${sysconfdir}/sysctl.d/"
	install -m 0644 "${WORKDIR}/10-oniro-net-ipv6.conf" "${D}${sysconfdir}/sysctl.d/"
	install -m 0644 "${WORKDIR}/10-oniro-bpf.conf" "${D}${sysconfdir}/sysctl.d/"
}
