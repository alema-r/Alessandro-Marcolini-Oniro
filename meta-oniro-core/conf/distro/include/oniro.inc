# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

ONIRO_FLAVOUR = "${@'${ONIRO_FLAVOUR_PRETTY}'.lower()}"
DISTRO = "oniro-${ONIRO_FLAVOUR}"
DISTRO_NAME = "Oniro Project Base ${ONIRO_FLAVOUR} Distro"

require conf/distro/include/security_flags.inc

INHERIT += "oniro-sanity"

# Configuration needed for meta-java layer
PREFERRED_PROVIDER_virtual/java-initial-native ?= "cacao-initial-native"
PREFERRED_PROVIDER_virtual/java-native ?= "cacao-native"

BB_DANGLINGAPPENDS_WARNONLY:forcevariable = "0"
