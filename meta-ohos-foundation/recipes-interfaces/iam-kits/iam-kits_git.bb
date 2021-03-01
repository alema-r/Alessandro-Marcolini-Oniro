# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

SUMMARY = "OHOS interface kits for IAM lite"
DESCRIPTION = "Interface kits for the application permission management"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=98c2e72b17fae6c40fb14fd5e43b29ec"

SRC_URI = "git://gitee.com/openharmony/security_interfaces_kits_iam_lite.git;protocol=https"

PV = "1.0+git${SRCPV}"
SRCREV = "ae411bce509c90bb09b49f2f3371f116313880d6"

S = "${WORKDIR}/git"

do_install () {
	install -d ${D}${includedir}
	install -m 0755 ${S}/*.h ${D}${includedir}/
}
