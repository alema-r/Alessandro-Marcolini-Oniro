# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

ONIRO_FLAVOUR = "${@'${ONIRO_FLAVOUR_PRETTY}'.lower()}"
DISTRO = "oniro-${ONIRO_FLAVOUR}"
DISTRO_NAME = "Oniro Project Base ${ONIRO_FLAVOUR_PRETTY} Distro"

require conf/distro/include/security_flags.inc

INHERIT += "oniro-sanity"

BB_DANGLINGAPPENDS_WARNONLY:forcevariable = "0"

# Bump this each time bblayers.conf.sample changes incompatibly. For example,
# when integrating new layers.
ONIRO_REQUIRED_LAYERS_CONF_VERSION = "2"
# oe-core's sanity check sanity_check_conffiles function only runs the
# functions in BBLAYERS_CONF_UPDATE_FUNCS when the associated variable is not
# None. Making sure this variable has a default value, we force running the
# check even when ONIRO_LAYERS_CONF_VERSION is not defined - providing the
# relevant error message.
ONIRO_LAYERS_CONF_VERSION ??= "-1"
