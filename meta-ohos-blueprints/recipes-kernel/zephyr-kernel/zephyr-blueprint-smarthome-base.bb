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

SRCREV_smarthome = "d45c192aa8a6b25328ab40610d6e78f50afe4150"

ZEPHYR_SRC_DIR = "${S}/apps/smarthome"

ZEPHYR_MODULES_append = "\;${S}/modules/crypto/tinycrypt"
