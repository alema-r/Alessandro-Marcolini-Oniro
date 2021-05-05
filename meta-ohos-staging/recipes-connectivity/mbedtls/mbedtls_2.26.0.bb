# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

SUMMARY = "Lightweight crypto and SSL/TLS library"
DESCRIPTION = "mbedtls is a lean open source crypto library          \
for providing SSL and TLS support in your programs. It offers        \
an intuitive API and documented header files, so you can actually    \
understand what the code does."

HOMEPAGE = "https://tls.mbed.org/"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SECTION = "libs"

S = "${WORKDIR}/git"
SRCREV = "e483a77c85e1f9c1dd2eb1c5a8f552d2617fe400"
SRC_URI = "git://github.com/ARMmbed/mbedtls.git;protocol=https \
file://0001-ssl_tls-Increase-size-of-padbuf-to-64.patch \
file://0002-workaround-gcc9-Wformat-truncation-false-positives.patch"

inherit cmake

PACKAGECONFIG ??= "shared-libs programs"
PACKAGECONFIG[shared-libs] = "-DUSE_SHARED_MBEDTLS_LIBRARY=ON,-DUSE_SHARED_MBEDTLS_LIBRARY=OFF"
PACKAGECONFIG[programs] = "-DENABLE_PROGRAMS=ON,-DENABLE_PROGRAMS=OFF"

EXTRA_OECMAKE = "-DENABLE_ZLIB_SUPPORT:BOOL=ON -DLINK_WITH_PTHREAD:BOOL=ON -DENABLE_TESTING:BOOL=OFF -DLIB_INSTALL_DIR:STRING=${libdir}"

PROVIDES += "polarssl"
RPROVIDES_${PN} = "polarssl"

PACKAGES =+ "${PN}-programs"
FILES_${PN}-programs = "${bindir}/"

DEPENDS = "zlib"

BBCLASSEXTEND = "native nativesdk"
