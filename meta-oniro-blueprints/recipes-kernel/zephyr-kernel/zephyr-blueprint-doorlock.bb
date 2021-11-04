# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

require recipes-kernel/zephyr-kernel/zephyr-sample.inc

SUMMARY = "Doorlock blueprint image"
DESCRIPTION = "Zephyr based image for handling door locks"
LICENSE = "Apache-2.0"

SRC_OPT_PROTO = "protocol=https"
SRC_OPT_DEST = "destsuffix=git/apps/doorlock"
SRC_OPT_NAME = "name=doorlock"
SRC_OPT_BRANCH = "branch=main"

SRC_OPTIONS = "${SRC_OPT_PROTO};${SRC_OPT_DEST};${SRC_OPT_NAME};${SRC_OPT_BRANCH}"
SRC_URI += "git://booting.oniroproject.org/distro/blueprints/doorlock/doorlock-zephyr.git;${SRC_OPTIONS}"

SRCREV_doorlock = "7d7f093c5a1285027bea3d070760b48e25ba8ced"

ZEPHYR_SRC_DIR = "${S}/apps/doorlock"

# For the moment, other boards don't have the devicetree
# overlays describing how the locks, keypad etc. are
# supposed to be connected.
COMPATIBLE_MACHINE = "(arduino-nano-33-ble)"
