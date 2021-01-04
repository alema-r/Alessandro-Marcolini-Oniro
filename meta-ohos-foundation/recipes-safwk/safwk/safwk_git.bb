SUMMARY = "OHOS foundation"
DESCRIPTION = "OHOS foundation main application"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=98c2e72b17fae6c40fb14fd5e43b29ec"

DEPENDS += "utils-native-lite libsec hiviewdfx-innerkits-hilog hiviewdfx-hilog samgr samgr-kits softbus"

SRC_URI = "git://gitee.com/openharmony/distributedschedule_services_safwk_lite.git;protocol=https"

PV = "1.0+git${SRCPV}"
SRCREV = "26e0346b72cc7f8ac98395f9f047f684b7137471"

S = "${WORKDIR}/git"

# Debug flag enabled by default for now
CFLAGS += "-DDEBUG_SERVICES_SAFWK_LITE"
LDFLAGS += "-lrt"

do_compile () {
	${CC} ${CFLAGS} ${LDFLAGS} src/*.c -o ${B}/foundation -lsamgr -lsec -lhiviewdfx-hilog -lsoftbus
}

do_install () {
	install -d ${D}${base_bindir}
	install -m 0755 ${B}/foundation ${D}${base_bindir}
}

FILES_${PN} += "${base_bindir}/foundation"

PROVIDES = "foundation"
