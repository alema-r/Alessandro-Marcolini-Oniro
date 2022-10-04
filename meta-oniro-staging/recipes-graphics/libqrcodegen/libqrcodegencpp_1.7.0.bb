# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: MIT

SUMMARY = "QR Code generator library"
DESCRIPTION = "This project aims to be the best, clearest QR Code generator library in multiple languages. The primary goals are flexible options and absolute correctness. Secondary goals are compact implementation size and good documentation comments."
AUTHOR = "Project Nayuki"
HOMEPAGE = "https://www.nayuki.io/page/qr-code-generator-library"
SECTION = "libs"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://qrcodegen.cpp;beginline=4;endline=21;md5=842e38efabbb76f96a6436248ce83149"

SRC_URI = "git://github.com/nayuki/QR-Code-generator.git;protocol=https"
SRCREV = "4d13c303dc878ffae8df6d0e0a7513fe860a092c"

S = "${WORKDIR}/git/cpp"

do_install () {
    install -D -t ${D}${libdir} -m 644 ${S}/libqrcodegencpp.a
    install -D -t ${D}${bindir} -m 755 ${S}/QrCodeGeneratorDemo
    install -D -t ${D}${includedir} -m 644 ${S}/qrcodegen.hpp
}

PACKAGES =+ "${PN}-demo"
FILES:${PN}-demo = "${bindir}/QrCodeGeneratorDemo"
