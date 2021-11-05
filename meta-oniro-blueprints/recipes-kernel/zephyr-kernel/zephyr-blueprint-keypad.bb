# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

require recipes-kernel/zephyr-kernel/zephyr-sample.inc

SUMMARY = "Keypad blueprint image"
DESCRIPTION = "Zephyr based image for the touchscreen-based keypad"
LICENSE = "Apache-2.0"

SRC_OPT_PROTO = "protocol=https"
SRC_OPT_DEST = "destsuffix=git/apps/keypad"
SRC_OPT_NAME = "name=keypad"
SRC_OPT_BRANCH = "branch=master"

SRC_OPTIONS = "${SRC_OPT_PROTO};${SRC_OPT_DEST};${SRC_OPT_NAME};${SRC_OPT_BRANCH}"
SRC_URI += "git://booting.oniroproject.org/distro/blueprints/keypad/zephyr-keypad.git;${SRC_OPTIONS}"

SRCREV_keypad = "b102af9f385993279e25e5c7cf335edfd08ef7d9"

ZEPHYR_SRC_DIR = "${S}/apps/keypad"
ZEPHYR_MODULES_append = "\;${S}/modules/lib/gui/lvgl"

# We will support the Arduino Nano 33 BLE too in the future. For now we only
# support the display on the Nordic reference devkit.
COMPATIBLE_MACHINE = "(nrf52840dk-nrf52840)"

# TODO Once we add more compatible machines, this should depend on the
# MACHINE value.
EXTRA_OECMAKE_append = " -DSHIELD=adafruit_2_8_tft_touch_v2"
