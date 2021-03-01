# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

SUMMARY = "HiChain"
DESCRIPTION = "Trusted interconnection between devices"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=98c2e72b17fae6c40fb14fd5e43b29ec"

SRC_URI = "git://gitee.com/openharmony/security_services_hichainsdk_lite.git;protocol=https"

PV = "1.0+git${SRCPV}"
SRCREV = "66085041cffc18adc3979f6e602e8062e8c4ab0e"

S = "${WORKDIR}/git"

do_compile () {
}

do_install () {
	install -d ${D}${includedir}
	install -m 0755 ${S}/source/huks_adapter/*.h ${D}${includedir}/
}
