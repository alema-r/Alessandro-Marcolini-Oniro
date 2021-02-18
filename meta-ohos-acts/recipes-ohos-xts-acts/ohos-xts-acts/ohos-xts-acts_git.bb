LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=98c2e72b17fae6c40fb14fd5e43b29ec"

SRC_URI = "git://git.ostc-eu.org/OSTC/OHOS/components/staging/xts_acts.git;protocol=https;branch=ostc"
SRCREV = "8a797c4ee9bf451e030430bbd73293ee8e0fe54d"
S = "${WORKDIR}/git"
PV = "0.0+git${SRCPV}"

# The OHOS uses unversioned versioned libraries.
# Move the naked .so files to the primary package.
SOLIBS = ".so"
FILES_SOLIBSDEV = ""

inherit zmk

DEPENDS += "ohos-googletest ohos-libsec"
