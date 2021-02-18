LICENSE = "MulanPSL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=2ba4788337b3607f139c2267ed50d32b"

SRC_URI = "git://git.ostc-eu.org/OSTC/OHOS/components/staging/third_party_bounds_checking_function.git;protocol=https;branch=ostc"
SRCREV = "256b1fc30d387f47cac1a376b2f1f28148b94804"
S = "${WORKDIR}/git"
PV = "0.0+git${SRCPV}"

# The OHOS fork of libsec is not versioned properly.
# Move the naked .so files to the primary package.
SOLIBS = ".so"
FILES_SOLIBSDEV = ""

inherit zmk
