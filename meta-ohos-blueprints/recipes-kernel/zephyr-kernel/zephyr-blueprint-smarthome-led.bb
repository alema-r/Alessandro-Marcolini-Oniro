# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

require zephyr-blueprint-smarthome-base.bb

SUMMARY = "SmartHome blueprint image: smart LED"
DESCRIPTION = "Zephyr based image targeting smart LED board"
LICENSE = "Apache-2.0"

# Comment out features not used by this image
do_configure_append() {
	sed -i '/^CONFIG_I2C/ s/./#&/' ${ZEPHYR_SRC_DIR}/prj.conf
	sed -i '/^CONFIG_DISPLAY/ s/./#&/' ${ZEPHYR_SRC_DIR}/prj.conf
	sed -i '/^CONFIG_GROVE_LCD_RGB/ s/./#&/' ${ZEPHYR_SRC_DIR}/prj.conf
	sed -i '/^CONFIG_SENSOR/ s/./#&/' ${ZEPHYR_SRC_DIR}/prj.conf
	sed -i '/^CONFIG_DHT/ s/./#&/' ${ZEPHYR_SRC_DIR}/prj.conf
	sed -i '/^CONFIG_GROVE_HPS/ s/./#&/' ${ZEPHYR_SRC_DIR}/prj.conf
}
