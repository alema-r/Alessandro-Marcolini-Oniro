# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: MIT

SUMMARY = "Musl libc test to be run with ptest"
HOMEPAGE = "https://wiki.musl-libc.org/libc-test.html"
DESCRIPTION = "libc-test is a collection of unit tests to measure the \
correctness and robustness of a C/POSIX standard library implementation. It is \
developed as part of the musl project."
SECTION = "tests"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=43ed1245085be90dc934288117d55a3b"

inherit ptest

SRCREV = "18e28496adee3d84fefdda6efcb9c5b8996a2398"
SRC_URI = " \
    git://nsz.repo.hu:49100/repo/libc-test;branch=master \
    file://run-ptest \
"

S = "${WORKDIR}/git"

# libc-test 'make' or 'make run' command is designed to build and run tests. It
# reports both build and test failures. The commands should be run on target.
do_compile() {
    :
}

RDEPENDS:${PN}-ptest = " \
    bash \
    grep \
    musl \
    packagegroup-core-buildessential \
    sed \
"

do_install_ptest () {
    cp ${S}/Makefile ${D}${PTEST_PATH}
    cp ${S}/config.mak.def ${D}${PTEST_PATH}/config.mak
    cp -r ${S}/src ${D}${PTEST_PATH}/
}

# libc-test-ptest depends on empty libc-test.
ALLOW_EMPTY:${PN} = "1"

COMPATIBLE_HOST = "null"
COMPATIBLE_HOST:libc-musl = "(.*)"
