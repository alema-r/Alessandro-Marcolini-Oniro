# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

SRC_URI += " \
    file://Cargo.toml-do-not-abort-on-panic.patch \
    file://zenohc.pc \
"

# this is needed because the library is unversioned
SOLIBS = ".so"
FILES_SOLIBSDEV = ""

do_install:append() {
    # install pkgconfig file so that the library is discoverable with pkgconfig 
    install -D -m0600 ${WORKDIR}/zenohc.pc ${D}${libdir}/pkgconfig/zenohc.pc

    # meta-rust installs the library in rust directory, moving it to ${libdir}
    mv ${D}${libdir}/rust/libzenohc.so ${D}${libdir}/libzenohc.so
    rm -rf ${D}${libdir}/rust

    # install header files
    install -d ${D}${includedir}/
    install -m 600 ${S}/include/zenoh.h ${S}/include/zenoh_macros.h ${S}/include/zenoh_concrete.h \
        ${S}/include/zenoh_commons.h ${D}${includedir}/
}
