# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

SUMMARY = "Home Assistant custom component for All Scenarios OS SmartHome device"
HOMEPAGE = "https://git.ostc-eu.org/OSTC/OHOS/components/smart_home_homeassistant_plugin/"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSES/Apache-2.0.txt;md5=c846ebb396f8b174b10ded4771514fcc"

SRC_OPT_CLONE_DIR = "git/components/smart_home_homeassistant_plugin"
SRC_OPT_PROTO = "protocol=https"
SRC_OPT_DEST = "destsuffix=${SRC_OPT_CLONE_DIR}"
SRC_OPT_NAME = "name=smart_home_homeassistant_plugin"
SRC_OPT_BRANCH = "branch=master"

SRC_OPTIONS = "${SRC_OPT_PROTO};${SRC_OPT_DEST};${SRC_OPT_NAME};${SRC_OPT_BRANCH}"
SRC_URI = "git://git.ostc-eu.org/OSTC/OHOS/components/smart_home_homeassistant_plugin.git;${SRC_OPTIONS}"
SRCREV_smart_home_homeassistant_plugin = "3f3b0aa9b6b5570b01dea26265577871a4bb3ab5"

S = "${WORKDIR}/${SRC_OPT_CLONE_DIR}"

require recipes-homeassistant/homeassistant/homeassistant-config.inc
require recipes-homeassistant/homeassistant/homeassistant-useradd.inc

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    install -d ${D}${HOMEASSISTANT_CONFIG_DIR}/custom_components/allscenarios_sensors/
    install ${S}/custom_components/allscenarios_sensors/*.py \
        ${D}${HOMEASSISTANT_CONFIG_DIR}/custom_components/allscenarios_sensors/

    install -d ${D}${HOMEASSISTANT_CONFIG_DIR}/custom_components/allscenarios_switch/
    install ${S}/custom_components/allscenarios_switch/*.py \
        ${D}${HOMEASSISTANT_CONFIG_DIR}/custom_components/allscenarios_switch/

    chown -R "${HOMEASSISTANT_USER}":homeassistant "${D}${HOMEASSISTANT_CONFIG_DIR}"
}

RDEPENDS_${PN} += " \
    python3-homeassistant \
    ohsmarthome-py \
    "
