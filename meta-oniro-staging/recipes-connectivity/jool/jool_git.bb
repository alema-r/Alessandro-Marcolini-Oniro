# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

include jool.inc

DESCRIPTION = "Jool is an Open Source SIIT and NAT64 for Linux."

LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI += "file://0001-Build-system-changes-to-be-handled-by-yocto-recipe.patch"

S = "${WORKDIR}/git"

DEPENDS = "libnl iptables"
DEPENDS_append_libc-musl = " argp-standalone"

inherit pkgconfig autotools

CFLAGS += "-I${STAGING_INCDIR}"
LDFLAGS += "-L${STAGING_LIBDIR}"

do_compile_append () {
        oe_runmake -C ${S}/src/usr/iptables/
}

do_install_append () {
	install -d ${D}/${libdir}/xtables
        install ${S}/src/usr/iptables/*.so ${D}/${libdir}/xtables
}

FILES_${PN} += "${datadir}/bash-completion"
FILES_${PN} += "${libdir}/xtables/*.so"
