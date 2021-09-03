# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=98c2e72b17fae6c40fb14fd5e43b29ec"

SRC_URI = "git://git.ostc-eu.org/OSTC/OHOS/components/staging/xts_acts.git;protocol=https;branch=ostc-next"
SRCREV = "defe7649c4baa33b405785201b9d686722ef66da"
S = "${WORKDIR}/git"
PV = "0.0+git${SRCPV}"

# The OHOS uses unversioned versioned libraries.
# Move the naked .so files to the primary package.
SOLIBS = ".so"
FILES_SOLIBSDEV = ""

inherit zmk

# jffs2 image size defaults to 1MB
ACTS_JFFS2_IMG_SIZE ?= "0x100000"

# FIXME this should be fixed properly instead of adding this workaround.
# When using modern compilers with -Warray-bounds, they find at least one
# legitimate issue with the code in xts:
# | ../git/kernel_lite/mem_posix/src/ActsMemApiTest.cpp:229:25: error: 'void* mempcpy(void*, const void*, size_t)' forming offset [13, 15] is out of the bounds [0, 13] of object 'srcStr' with type 'char [13]' [-Werror=array-bounds]
# |   229 |     pt = (char *)mempcpy(destStr, srcStr, 16);
# |       |                  ~~~~~~~^~~~~~~~~~~~~~~~~~~~~
# | ../git/kernel_lite/mem_posix/src/ActsMemApiTest.cpp:224:10: note: 'srcStr' declared here
# |   224 |     char srcStr[] = "this is str1";
# |       |          ^~~~~~
#
# (Copying 16 characters from a string of 12)
#
# However, making the error non-fatal is no worse than not using
# -Werror=array-bounds globally (previous situation), so it is an
# ok workaround until this is fixed properly.
TARGET_CFLAGS_remove = "-Werror=array-bounds"

do_install_append() {
    # Prepare jffs2 image to be mounted on the target
    # mkfs.jffs2 requires a directory to make an image out of
    mkdir -p ${B}/.jffs-empty-dir
    mkfs.jffs2 --root=${B}/.jffs-empty-dir --pad=${ACTS_JFFS2_IMG_SIZE} -o ${B}/jffs2.img
    rm -rf ${B}/.jffs-empty-dir
    install -d ${D}${datadir}/acts-data
    install -m 644 ${B}/jffs2.img ${D}${datadir}/acts-data
}

FILES_${PN} += "${datadir}/acts-data/*"

DEPENDS += "\
    libcap \
    mtd-utils-native \
    ohos-global-i18n-lite \
    ohos-googletest \
    ohos-libsec \
"
DEPENDS_append_toolchain-clang += "compiler-rt"
RRECOMMENDS_${PN} += "\
    kernel-module-block2mtd \
    ltp \
"
