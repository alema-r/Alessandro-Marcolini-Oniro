SUMMARY = "OHOS Hiviewdfx innerkits"
DESCRIPTION = "OHOS interface innerkits for Hiviewdfx"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=98c2e72b17fae6c40fb14fd5e43b29ec"

SRC_URI = "git://gitee.com/openharmony/hiviewdfx_interfaces_innerkits_hilog.git;protocol=https"

PV = "1.0+git${SRCPV}"
SRCREV = "6a2507b98ba606af7383869be084a8ecbec6b095"

S = "${WORKDIR}/git"

do_install () {
        install -d ${D}${includedir}
        install -m 0755 ${S}/*.h ${D}${includedir}/
}
