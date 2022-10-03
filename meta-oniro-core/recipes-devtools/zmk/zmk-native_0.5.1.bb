# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: MIT

SUMMARY = "Collection of reusable makefiles"
LICENSE = "LGPLv3"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e6a600fd5e1d9cbde2d983680233ad02"

SRC_URI = "https://github.com/zyga/zmk/releases/download/v${PV}/zmk-${PV}.tar.gz \
           "
SRC_URI[md5sum] = "753acb0dba113f14f1e2a898748bdb13"
SRC_URI[sha256sum] = "541874f8244060acdbf1da3ea19cf3f3074f8b9e40e6873ea546136e88033d93"

DEPENDS += "make-native gawk-native"

inherit autotools native
