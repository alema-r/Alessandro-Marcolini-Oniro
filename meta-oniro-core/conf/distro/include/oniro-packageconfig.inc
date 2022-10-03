# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: MIT

# Define the configuration set as per our distro policy
PACKAGECONFIG:pn-networkmanager = "nss systemd bluez5 wifi nmcli"

# We have no use for dlt-daemon as we use systemd for logging
PACKAGECONFIG:remove:pn-mosquitto = "dlt"

# We use NetworkManager as the default network manager (included in our
# reference images).
PACKAGECONFIG:remove:pn-systemd = "networkd wheel-group"

# Enable podman rootless support
PACKAGECONFIG:append:pn-podman = " rootless"
