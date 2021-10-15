# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

inherit useradd

HOMEASSISTANT_USER ?= "homeassistant"
HOMEASSISTANT_USER[doc] = "User the home-assistent service runs as."

USERADD_PACKAGES = "${PN}"
GROUPADD_PARAM_${PN} = "--system homeassistant"
USERADD_PARAM_${PN} = "\
    --system --no-create-home --shell /bin/false \
    --home ${HOMEASSISTANT_CONFIG_DIR} \
    --groups homeassistant,dialout --gid homeassistant \
    ${HOMEASSISTANT_USER}"
