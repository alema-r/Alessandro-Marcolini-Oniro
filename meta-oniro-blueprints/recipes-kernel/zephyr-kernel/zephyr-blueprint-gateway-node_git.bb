# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

require recipes-kernel/zephyr-kernel/zephyr-sample.inc

SUMMARY = "Gateway blueprint: OpenThread node"
DESCRIPTION = "Zephyr based OpenThread node image"
LICENSE = "Apache-2.0"

SRC_OPT_PROTO = "protocol=https"
SRC_OPT_DEST = "destsuffix=git/apps/openthread-node"
SRC_OPT_NAME = "name=otnode"
SRC_OPT_BRANCH = "branch=main"

SRC_OPTIONS = "${SRC_OPT_PROTO};${SRC_OPT_DEST};${SRC_OPT_NAME};${SRC_OPT_BRANCH}"
SRC_URI += "git://booting.oniroproject.org/distro/blueprints/gateway/openthread-node-zephyr.git;${SRC_OPTIONS}"

SRCREV_otnode = "a688fa8b08d56d0619f122a4eb426ddd9bc343a0"

ZEPHYR_SRC_DIR = "${S}/apps/openthread-node"

ZEPHYR_MODULES_append = "\;${S}/modules/lib/mbedtls"
ZEPHYR_MODULES_append = "\;${S}/modules/lib/openthread"

# The overlay config and OpenThread itself imposes some specific requirements
# towards the boards (e.g. flash layout and ieee802154 radio) so we need to
# limit to known working machines here.
COMPATIBLE_MACHINE = "(arduino-nano-33-ble)"
