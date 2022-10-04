# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: MIT

SRC_URI += "file://Cargo.toml-do-not-abort-on-panic.patch"

# this is needed because the libraries are unversioned
SOLIBS = ".so"
FILES_SOLIBSDEV = ""

do_install:append() {
    # meta-rust installs the libraries in rust directory, moving them to ${libdir}
    mv ${D}${libdir}/rust/*.so ${D}${libdir}/
    rm -rf ${D}${libdir}/rust
}
