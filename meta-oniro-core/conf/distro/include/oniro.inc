# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

require conf/distro/include/security_flags.inc

INHERIT += "oniro-sanity"

# Configuration needed for meta-java layer
PREFERRED_PROVIDER_virtual/java-initial-native ?= "cacao-initial-native"
PREFERRED_PROVIDER_virtual/java-native ?= "cacao-native"
