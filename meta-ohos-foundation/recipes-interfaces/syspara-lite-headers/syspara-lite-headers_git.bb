# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

SUMMARY = "OHOS Sysparam headers"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=98c2e72b17fae6c40fb14fd5e43b29ec"
PV = "1.0+git${SRCPV}"

SRCREV = "69dcf31029e2d9d79adf73259394235db78e35b1"
SRC_URI = "git://gitee.com/openharmony/startup_interfaces_kits_syspara_lite.git;protocol=https"

S = "${WORKDIR}/git"

do_install () {
    install -d ${D}${includedir}
    install -m 0755 ${S}/*.h ${D}${includedir}/
}
