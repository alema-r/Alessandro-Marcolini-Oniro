# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

SUMMARY = "OpenHarmony i18n library"
DESCRIPTION = "OpenHarmony library providing i18n capabilities like date, \
time and numbers formatting or months and weekdays handling."

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a832eda17114b48ae16cda6a500941c2"

SRC_URI = "git://gitee.com/openharmony/global_i18n_lite.git;protocol=https \
           file://GNUmakefile \
"
SRCREV = "1de9116474acc560b79dc5b5ecf5354c4d7089c0"
PV = "0.0+git${SRCPV}"
S = "${WORKDIR}/git"

inherit zmk

DEPENDS += "ohos-libsec"

# The OHOS uses unversioned versioned libraries.
# Move the naked .so files to the primary package.
SOLIBS = ".so"
FILES_SOLIBSDEV = ""

do_configure_prepend() {
    # global_i18n_lite component is being built using zmk compatible makefile
    cp ${WORKDIR}/GNUmakefile ${S}
}
