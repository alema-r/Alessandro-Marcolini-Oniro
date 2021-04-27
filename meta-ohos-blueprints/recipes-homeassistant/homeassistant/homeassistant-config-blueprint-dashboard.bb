# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

SUMMARY = "Home Assistant configuration for All Scenarios OS Smart Panel \
blueprint"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${OHOS_COREBASE}/LICENSES/Apache-2.0.txt;md5=ef3dabb8f39493f4ea410bebc1d01755"
INHIBIT_DEFAULT_DEPS = "1"

inherit allarch

do_fetch[noexec] = "1"
do_unpack[noexec] = "1"
do_patch[noexec] = "1"
do_configure[noexec] = "1"
do_compile[noexec] = "1"

require recipes-homeassistant/homeassistant/homeassistant-config.inc
require recipes-homeassistant/homeassistant/homeassistant-useradd.inc

do_install() {
    install -d "${D}${HOMEASSISTANT_CONFIG_DIR}"

    # MAC addresses can be adjusted in your local.conf.
    cat >> "${D}${HOMEASSISTANT_CONFIG_DIR}/configuration.yaml" << EOF
default_config:

sensor:
  - platform: allscenarios_sensors
    mac: "${SMART_HOME_SENSORS_MAC}"

switch:
  - platform: allscenarios_switch
    mac: "${SMART_HOME_SWITCH_MAC}"
EOF

    chown -R "${HOMEASSISTANT_USER}":homeassistant "${D}${HOMEASSISTANT_CONFIG_DIR}"
}

RDEPENDS_${PN} = "smart-home-homeassistant-plugin"
FILES_${PN} += "${HOMEASSISTANT_CONFIG_DIR}/configuration.yaml"
