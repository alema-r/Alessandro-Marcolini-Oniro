LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=98c2e72b17fae6c40fb14fd5e43b29ec"

SRC_URI = "git://git.ostc-eu.org/OSTC/OHOS/components/staging/xts_acts.git;protocol=https \
           file://0001-Add-makefile-based-on-zmk.patch \
           "
SRCREV = "2a3e429e72f8612e74a7d1e32567be11ef1541c4"
S = "${WORKDIR}/git"
PV = "0.0+git${SRCPV}"

# The OHOS uses unversioned versioned libraries.
# Move the naked .so files to the primary package.
SOLIBS = ".so"
FILES_SOLIBSDEV = ""

inherit zmk

DEPENDS += "ohos-googletest ohos-libsec"
