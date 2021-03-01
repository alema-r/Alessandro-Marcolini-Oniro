# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

SUMMARY = "IAM lite"
DESCRIPTION = "Application permission management"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=98c2e72b17fae6c40fb14fd5e43b29ec"

DEPENDS += "utils-native-lite samgr-kits hiviewdfx-innerkits-hilog"

SRC_URI = "git://gitee.com/openharmony/security_services_iam_lite.git;protocol=https"

PV = "1.0+git${SRCPV}"
PVSHORT = '${@d.getVar("PV", False).split("+")[0]}'
PVMAJOR = '${@d.getVar("PV", False).split(".")[0]}'

SRCREV = "ff4d69b9d1b6a31ebf0c14b2ce6cb8f6122e3340"

S = "${WORKDIR}/git"

CFLAGS_IAM = "${CFLAGS} -I${S}/pms_base/include -fPIC -shared"
LDFLAGS_IAM = "${LDFLAGS} -L${B} -Wl,-soname,lib${PN}.so.${PVMAJOR}"

do_compile () {
	${CC} ${CFLAGS_IAM} ${LDFLAGS_IAM} ${S}/pms_base/src/permission_service.c \
		-o ${B}/lib${PN}.so.${PVSHORT}
}

do_install () {
	install -d ${D}${includedir}
	install -m 0755 ${S}/pms_base/include/*.h ${D}${includedir}/
	install -d ${D}${libdir}
	oe_soinstall ${B}/lib${PN}.so.${PVSHORT} ${D}${libdir}
}
