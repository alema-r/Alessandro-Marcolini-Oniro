SUMMARY = "OHOS interface kits for Softbus"
DESCRIPTION = "Interface kits for OHOS communication utility - Softbus"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=98c2e72b17fae6c40fb14fd5e43b29ec"

SRC_URI = "git://gitee.com/openharmony/communication_interfaces_kits_softbuskit_lite.git;protocol=https"

PV = "1.0+git${SRCPV}"
SRCREV = "40aec6fdcdeed401adc97451434b26919e80c69a"

S = "${WORKDIR}/git"

do_install () {
	install -d ${D}${includedir}
	install -m 0755 ${S}/discovery/*.h ${D}${includedir}/
	install -m 0755 ${S}/transport/*.h ${D}${includedir}/
}
