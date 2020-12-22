SUMMARY = "OHOS SAMGR"
DESCRIPTION = "OHOS distributed services manager"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=98c2e72b17fae6c40fb14fd5e43b29ec"

DEPENDS += "utils-native-lite libsec samgr-kits hiviewdfx-innerkits-hilog"

SRC_URI = "git://gitee.com/openharmony/distributedschedule_services_samgr_lite.git;protocol=https"

PV = "1.0+git${SRCPV}"
PVSHORT = '${@d.getVar("PV", False).split("+")[0]}'
PVMAJOR = '${@d.getVar("PV", False).split(".")[0]}'

SRCREV = "626f7131c567cef9f96eb7cee24ee2eaa8cac4f5"

S = "${WORKDIR}/git"

CFLAGS_SAMGR = "${CFLAGS} -I${S}/samgr/adapter -I${S}/samgr -I${S}/samgr/source -I${S}/samgr/registry -fPIC -shared"
LDFLAGS_SAMGR = "${LDFLAGS} -lsec -pthread -L${B} -Wl,-soname,lib${PN}.so.${PVMAJOR}"

def get_target_adapter(d):
    targetOs = d.getVar('TARGET_OS', True)
    if targetOs == "linux" or targetOs == "liteos_a":
        targetAdapter = "posix"
    elif targetOs == "liteos_m":
        targetAdapter = "cmsis"
    else:
        raise bb.parse.SkipPackage("OS '%s' is not supported by ohos-foundation" % targetOs)
    return targetAdapter

TARGET_ADAPTER = "${@get_target_adapter(d)}"

do_compile () {
	${CC} ${CFLAGS_SAMGR} ${LDFLAGS_SAMGR}			\
		${S}/samgr/source/*.c				\
		${S}/samgr/registry/*.c				\
		${S}/samgr/adapter/${TARGET_ADAPTER}/*.c	\
		-o ${B}/lib${PN}.so.${PVSHORT}
}

do_install () {
	install -d ${D}${libdir}
	oe_soinstall ${B}/lib${PN}.so.${PVSHORT} ${D}${libdir}
}
