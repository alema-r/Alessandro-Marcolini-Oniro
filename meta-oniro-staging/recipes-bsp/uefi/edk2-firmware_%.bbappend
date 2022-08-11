# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

export GCC5_ARM_PREFIX = "${TARGET_PREFIX}"
export CLANG38_ARM_PREFIX = "${TARGET_PREFIX}"

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
