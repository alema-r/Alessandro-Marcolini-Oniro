# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

require zephyr-blueprint-smarthome-base.bb

SUMMARY = "SmartHome blueprint image: sensors"
DESCRIPTION = "Zephyr based image targeting sensors board"
LICENSE = "Apache-2.0"

# Comment out features not used by this image
do_configure_append() {
	sed -i '/^CONFIG_GROVE_LED_CTRL/ s/./#&/' ${ZEPHYR_SRC_DIR}/prj.conf
}
