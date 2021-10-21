# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

HOMEPAGE = "https://git.ostc-eu.org/distro/components/vending-machine-ui-application"
SUMMARY = "Vending Machine UI Application"
DESCRIPTION = "Vending machine front end client, it's a native GUI made with LVGL"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ba963850f6731c74878fe839d227e675"

SRC_URI = "git://git.ostc-eu.org/distro/components/${BPN};protocol=https;branch=main;"
SRCREV = "f9b9a709a299782f6f3accc7af8c743057294f95"
SRC_URI += "file://${BPN}.service"
S = "${WORKDIR}/git"

DEPENDS= "json-c libwebsockets lv-drivers lv-lib-png lvgl"

inherit pkgconfig features_check systemd

SYSTEMD_SERVICE_${PN} = "${BPN}.service"

REQUIRED_DISTRO_FEATURES = "systemd wayland"

EXTRA_OEMAKE += "DESTDIR=${D}"
EXTRA_OEMAKE += "sysroot=${RECIPE_SYSROOT}"

do_install() {
    oe_runmake install
    install -d "${D}${systemd_system_unitdir}/"
    install -m 0644 "${WORKDIR}/${BPN}.service" "${D}${systemd_system_unitdir}/"
}
