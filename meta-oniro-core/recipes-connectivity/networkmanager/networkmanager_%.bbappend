# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

inherit writables

WRITABLES = "system-connections"
WRITABLE_PATH[system-connections] = "/etc/NetworkManager/system-connections"

PACKAGECONFIG_remove = "dhclient dnsmasq ifupdown"
