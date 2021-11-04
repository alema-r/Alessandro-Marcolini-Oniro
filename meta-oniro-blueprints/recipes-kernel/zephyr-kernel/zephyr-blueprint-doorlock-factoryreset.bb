# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

require recipes-kernel/zephyr-kernel/zephyr-sample.inc

SUMMARY = "Doorlock factory reset image"
DESCRIPTION = "Zephyr based image for doing a factory reset on the doorlock blueprint"
LICENSE = "Apache-2.0"

SRC_OPT_PROTO = "protocol=https"
SRC_OPT_DEST = "destsuffix=git/apps/doorlock-factoryreset"
SRC_OPT_NAME = "name=doorlock-factoryreset"
SRC_OPT_BRANCH = "branch=main"

SRC_OPTIONS = "${SRC_OPT_PROTO};${SRC_OPT_DEST};${SRC_OPT_NAME};${SRC_OPT_BRANCH}"
SRC_URI += "git://booting.oniroproject.org/distro/blueprints/doorlock/doorlock-factoryreset.git;${SRC_OPTIONS}"

SRCREV_doorlock-factoryreset = "375df8948aff9aca79c6e766f070b82e6ca087f7"

ZEPHYR_SRC_DIR = "${S}/apps/doorlock-factoryreset"

# For the moment, other boards don't have the devicetree
# overlays describing how the locks, keypad etc. are
# supposed to be connected.
COMPATIBLE_MACHINE = "(arduino-nano-33-ble)"
