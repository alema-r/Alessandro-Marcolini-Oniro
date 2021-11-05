# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

# Define the configuration set as per our distro policy
PACKAGECONFIG_pn-networkmanager = "nss systemd bluez5 wifi"

# We have no use for dlt-daemon as we use systemd for logging
PACKAGECONFIG_remove_pn-mosquitto = "dlt"

# We use NetworkManager as the default network manager (included in our
# reference images).
PACKAGECONFIG_remove_pn-systemd = "networkd"
