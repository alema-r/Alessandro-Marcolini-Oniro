# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

SUMMARY = "OHOS common headers"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=98c2e72b17fae6c40fb14fd5e43b29ec"
PV = "1.0+git${SRCPV}"

SRCREV = "f599e73a996a10d77223a31db63c459a4c04f7f3"
SRC_URI = "git://gitee.com/openharmony/utils_native_lite.git;protocol=https"

S = "${WORKDIR}/git"

do_install () {
    install -d ${D}${includedir}
    install -m 0755 ${S}/include/*.h ${D}${includedir}/
}
