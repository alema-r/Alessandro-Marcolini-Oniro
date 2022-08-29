# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

SUMMARY = "Libmodbus testing examples"
SECTION = "net"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ef3dabb8f39493f4ea410bebc1d01755 \
                    "
DEPENDS = "libmodbus"
SRCREV = "06daf9b6159167fe22c87603e8926ff63df5ce8d"
PV = "0.0+git${SRCPV}"

SRC_URI = "git://gitlab.eclipse.org/eclipse/oniro-blueprints/energy-gateway/modbus-testing.git;protocol=https;branch=main"

S = "${WORKDIR}/git"

inherit meson pkgconfig
