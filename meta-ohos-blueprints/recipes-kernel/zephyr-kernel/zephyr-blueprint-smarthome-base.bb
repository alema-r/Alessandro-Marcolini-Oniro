# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

require recipes-kernel/zephyr-kernel/zephyr-sample.inc

SUMMARY = "SmartHome blueprint image: IOT endpoint"
DESCRIPTION = "Zephyr based image targeting IOT endpoints"
LICENSE = "Apache-2.0"

SRC_OPT_PROTO = "protocol=https"
SRC_OPT_DEST = "destsuffix=git/apps/smarthome"
SRC_OPT_NAME = "name=smarthome"
SRC_OPT_BRANCH = "branch=develop"

SRC_OPTIONS = "${SRC_OPT_PROTO};${SRC_OPT_DEST};${SRC_OPT_NAME};${SRC_OPT_BRANCH}"
SRC_URI += "git://git.ostc-eu.org/OSTC/OHOS/components/smart_home_blueprint_zephyr.git;${SRC_OPTIONS}"

SRCREV_smarthome = "b59f146fcb9c265b57c6466d9c70127de5c7dce2"

ZEPHYR_SRC_DIR = "${S}/apps/smarthome"

ZEPHYR_MODULES_append = "\;${S}/modules/crypto/tinycrypt"

do_configure_append() {
	if [ ! -z "${MAC}" ]; then
		sed -i "s/\(SMART_HOME_BT_MAC_ADDRESS\).*/\1 \"${MAC}\"/" ${ZEPHYR_SRC_DIR}/src/config.h
	fi
}
