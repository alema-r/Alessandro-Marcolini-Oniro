# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

SUMMARY = "OHOS third-party libraries"
LICENSE = "MulanPSL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=bf1e53b0cdc806a88b7a66486dc5e61f"

PV = "1.0+git${SRCPV}"
PVSHORT = '${@d.getVar("PV", False).split("+")[0]}'
PVMAJOR = '${@d.getVar("PV", False).split(".")[0]}'

SRCREV = "8355937ae5ae9bf01af491b39ff42f68246959c4"
SRC_URI = "git://gitee.com/openharmony/third_party_bounds_checking_function.git;protocol=https"

S = "${WORKDIR}/git"

CFLAGS += "-I${S}/include -fPIC -shared"
LDFLAGS += "-Wl,-soname,${PN}.so.${PVMAJOR}"

do_compile () {
    ${CC} ${CFLAGS} ${LDFLAGS} ${S}/src/*.c -o ${B}/${PN}.so.${PVSHORT}
}

do_install () {
    install -d ${D}${includedir}
    install -d ${D}${libdir}
    install -m 0755 ${S}/include/*.h ${D}${includedir}/
    oe_soinstall ${B}/${PN}.so.${PVSHORT} ${D}${libdir}
}
