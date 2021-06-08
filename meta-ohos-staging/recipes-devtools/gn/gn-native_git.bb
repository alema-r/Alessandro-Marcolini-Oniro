# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

SUMMARY = "Meta-build system that generates build files for Ninja"
LICENSE = "BSD-3-Clause & Unicode"
LIC_FILES_CHKSUM = "file://LICENSE;md5=0fca02217a5d49a14dfe2d11837bb34d \
                    file://src/base/third_party/icu/LICENSE;md5=97cdee8fe9e91b393a616bc13c8081db"

SRC_URI = "git://gn.googlesource.com/gn.git;protocol=https"

DEPENDS = "ninja-native"

PV = "0.0+git${SRCPV}"
SRCREV = "39a87c0b36310bdf06b692c098f199a0d97fc810"

S = "${WORKDIR}/git"

inherit native

do_configure () {
	python3 build/gen.py --no-strip --out-path ${B}
}

do_compile () {
	ninja -C ${B}
}

do_install () {
	install -D ${B}/gn ${D}${bindir}/gn
}
