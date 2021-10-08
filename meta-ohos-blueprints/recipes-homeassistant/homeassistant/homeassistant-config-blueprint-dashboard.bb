# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

SUMMARY = "Home Assistant configuration for All Scenarios OS Smart Panel \
blueprint"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${ONIRO_COREBASE}/LICENSES/Apache-2.0.txt;md5=ef3dabb8f39493f4ea410bebc1d01755"
INHIBIT_DEFAULT_DEPS = "1"

inherit allarch

HOMEASSISTANT_TRUSTED_NETWORK ?= "192.168.0.0/24"

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

homeassistant:
  auth_providers:
    - type: trusted_networks
      trusted_networks:
        - "${HOMEASSISTANT_TRUSTED_NETWORK}"
        - 127.0.0.1
        - ::1
      allow_bypass_login: true
    - type: homeassistant

automation: !include automations.yaml
EOF

    # Preload automation configuration for the loader
    # Trigger on/off LED based on the human presence sensor input.
    cat >> "${D}${HOMEASSISTANT_CONFIG_DIR}/automations.yaml" << EOF
- id: '1621503053201'
  alias: Light on
  description: ''
  trigger:
  - entity_id: sensor.all_scenarios_os_smarthome_device_presence
    from: 'False'
    platform: state
    to: 'True'
  condition: []
  action:
  - data: {}
    entity_id: switch.all_scenarios_os_smarthome_device_light
    service: switch.turn_on
- id: '1621503121253'
  alias: Light off
  description: ''
  trigger:
  - entity_id: sensor.all_scenarios_os_smarthome_device_presence
    from: 'True'
    platform: state
    to: 'False'
  condition: []
  action:
  - data: {}
    entity_id: switch.all_scenarios_os_smarthome_device_light
    service: switch.turn_off
EOF

    chown -R "${HOMEASSISTANT_USER}":homeassistant "${D}${HOMEASSISTANT_CONFIG_DIR}"
}

RDEPENDS_${PN} = "smart-home-homeassistant-plugin"
FILES_${PN} += "${HOMEASSISTANT_CONFIG_DIR}"
