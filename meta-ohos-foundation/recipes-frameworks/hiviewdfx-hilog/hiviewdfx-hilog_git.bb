# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

SUMMARY = "Hiviewdfx Hilog"
DESCRIPTION = "Hiviewdfx Hilog logging module"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=98c2e72b17fae6c40fb14fd5e43b29ec"

DEPENDS += "hiviewdfx-innerkits-hilog libsec"

SRC_URI = "git://gitee.com/openharmony/hiviewdfx_frameworks_hilog_lite.git;protocol=https"

PV = "1.0+git${SRCPV}"
PVSHORT = '${@d.getVar("PV", False).split("+")[0]}'
PVMAJOR = '${@d.getVar("PV", False).split(".")[0]}'

SRCREV = "e8b70de7579e2634b16fac90adbc5d10755dae2e"

S = "${WORKDIR}/git"

CFLAGS += "-I${S}/include -fPIC -shared"
LDFLAGS += "-Wl,-soname,lib${PN}.so.${PVMAJOR} -lsec"

do_compile () {
	${CC} ${CFLAGS} ${LDFLAGS} featured/*.c -o ${B}/lib${PN}.so.${PVSHORT}
}

do_install () {
	install -d ${D}${libdir}
	oe_soinstall ${B}/lib${PN}.so.${PVSHORT} ${D}${libdir}
}
