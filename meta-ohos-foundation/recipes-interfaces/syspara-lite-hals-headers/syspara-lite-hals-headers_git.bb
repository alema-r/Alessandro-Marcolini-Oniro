# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

SUMMARY = "OHOS Sysparam HAL headers"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=98c2e72b17fae6c40fb14fd5e43b29ec"
PV = "1.0+git${SRCPV}"

SRCREV = "6b186327def1b2876334f2eebb78e0e5bce747bb"
SRC_URI = "git://gitee.com/openharmony/startup_hals_syspara_lite.git;protocol=https"

S = "${WORKDIR}/git"

do_install () {
    install -d ${D}${includedir}
    install -m 0755 ${S}/*.h ${D}${includedir}/
}
