# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: MIT

FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"
SRC_URI += " \
	file://0001-Respect-BUILD_CFLAGS-in-GCC_ALL_CC_FLAGS.patch \
"

export GCC5_ARM_PREFIX = "${TARGET_PREFIX}"
export CLANG38_ARM_PREFIX = "${TARGET_PREFIX}"

# This is passed in the 0001-Respect-BUILD_CFLAGS-in-GCC_ALL_CC_FLAGS.patch
# patch so that the build can find the right headers in the sysroot.
export TARGET_SYSROOT = "${RECIPE_SYSROOT}"

EDK2_BUILD_RELEASE:toolchain-clang = "1"

do_configure:prepend() {
	sed -i -e "s#-target ${HOST_ARCH}-linux-gnu.*#-target ${HOST_SYS}#" ${S}/BaseTools/Conf/tools_def.template
}

do_install:prepend() {
	# Some platforms use an ARCH suffix.
	if [ ! -e "${B}/Build/${EDK2_PLATFORM}" ]; then
		ln -s ${EDK2_PLATFORM}-${EDK2_ARCH} ${B}/Build/${EDK2_PLATFORM}
	fi
}
