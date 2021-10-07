# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

HOMEPAGE = "https://git.ostc-eu.org/distro/components/vending-machine-control-application"
SUMMARY = "Vending Machine control Application"
DESCRIPTION = "Vending machine server for UI app to control IO"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ba963850f6731c74878fe839d227e675"

SRC_URI = "git://git.ostc-eu.org/distro/components/${BPN};protocol=https;branch=main;"
SRCREV = "e21c34cdd9b554978a80699f44fe35077b0dbb90"
S = "${WORKDIR}/git"

DEPENDS="json-c libwebsockets"

inherit pkgconfig features_check

EXTRA_OEMAKE += "DESTDIR=${D}"

do_install() {
    oe_runmake install
}
