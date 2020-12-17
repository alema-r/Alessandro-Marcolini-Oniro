SUMMARY = "OHOS interface kits for SAMGR"
DESCRIPTION = "Interface kits for OHOS distributed service manager "
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=98c2e72b17fae6c40fb14fd5e43b29ec"

SRC_URI = "git://gitee.com/openharmony/distributedschedule_interfaces_kits_samgr_lite.git;protocol=https"

PV = "1.0+git${SRCPV}"
SRCREV = "4acd6990320126796a28b2c3d7ad4c94932eb4b7"

S = "${WORKDIR}/git"

do_install () {
	install -d ${D}${includedir}
	install -m 0755 ${S}/samgr/*.h ${D}${includedir}/
	install -m 0755 ${S}/registry/*.h ${D}${includedir}/
}
