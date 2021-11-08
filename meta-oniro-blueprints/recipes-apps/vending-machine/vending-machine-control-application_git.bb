# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

HOMEPAGE = "https://booting.oniroproject.org/distro/components/vending-machine-control-application"
SUMMARY = "Vending Machine control Application"
DESCRIPTION = "Vending machine server for UI app to control IO"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ba963850f6731c74878fe839d227e675"

SRC_URI = "git://booting.oniroproject.org/distro/components/${BPN};protocol=https;branch=main"
SRCREV = "e755ddda4fdd51a90e0448477183fb0bdede62fd"
SRC_URI += "file://${BPN}.service"
S = "${WORKDIR}/git"

DEPENDS="systemd pim435 i2c-tools json-c libwebsockets"

inherit pkgconfig features_check systemd

SYSTEMD_SERVICE_${PN} = "${BPN}.service"

REQUIRED_DISTRO_FEATURES = "systemd"

EXTRA_OEMAKE += "DESTDIR=${D}"
EXTRA_OEMAKE += "sysroot=${RECIPE_SYSROOT}"

VENDING_MACHINE_CONTROL_I2C_BUS ??= "0"
VENDING_MACHINE_CONTROL_I2C_BUS_rpi ?= "1"
VENDING_MACHINE_CONTROL_I2C_BUS_seco-intel-b68 ?= "2"

do_install() {
    oe_runmake install
    install -d "${D}${systemd_system_unitdir}/"
    install -m 0644 "${WORKDIR}/${BPN}.service" "${D}${systemd_system_unitdir}/"
    install -d "${D}${sysconfdir}"
    echo "VENDING_MACHINE_CONTROL_I2C_BUS=${VENDING_MACHINE_CONTROL_I2C_BUS}" > \
        "${D}${sysconfdir}/vending-machine.env"
}
