# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=98c2e72b17fae6c40fb14fd5e43b29ec"

SRC_URI = "git://git.ostc-eu.org/OSTC/OHOS/components/staging/xts_acts.git;protocol=https;branch=ostc"
SRCREV = "607363f6b871d1de5e7d7fb26dd473e8aedba904"
S = "${WORKDIR}/git"
PV = "0.0+git${SRCPV}"

# The OHOS uses unversioned versioned libraries.
# Move the naked .so files to the primary package.
SOLIBS = ".so"
FILES_SOLIBSDEV = ""

inherit zmk

# jffs2 image size defaults to 1MB
ACTS_JFFS2_IMG_SIZE ?= "0x100000"

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

DEPENDS += "ohos-googletest ohos-libsec mtd-utils-native"
DEPENDS_append_toolchain-clang += "compiler-rt"
RRECOMMENDS_${PN} += "\
    kernel-module-block2mtd \
    ltp \
"
