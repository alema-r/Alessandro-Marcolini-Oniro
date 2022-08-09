# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

export GCC5_ARM_PREFIX = "${TARGET_PREFIX}"
export CLANG38_ARM_PREFIX = "${TARGET_PREFIX}"

do_install:prepend() {
	# Some platforms use an ARCH suffix.
	if [ ! -e "${B}/Build/${EDK2_PLATFORM}" ]; then
		ln -s ${EDK2_PLATFORM}-${EDK2_ARCH} ${B}/Build/${EDK2_PLATFORM}
	fi
}
