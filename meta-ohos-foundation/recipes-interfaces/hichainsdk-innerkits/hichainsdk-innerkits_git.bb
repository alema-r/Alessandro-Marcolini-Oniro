SUMMARY = "HiChain innerkits"
DESCRIPTION = "Innerkits for trusted interconnection between devices"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=98c2e72b17fae6c40fb14fd5e43b29ec"

SRC_URI = "git://gitee.com/openharmony/security_interfaces_innerkits_hichainsdk_lite.git;protocol=https"

PV = "1.0+git${SRCPV}"
SRCREV = "d0416fb8da9a853de592d3e6be91c63c8319d02e"

S = "${WORKDIR}/git"

do_install () {
	install -d ${D}${includedir}
	install -m 0755 ${S}/*.h ${D}${includedir}/
}
