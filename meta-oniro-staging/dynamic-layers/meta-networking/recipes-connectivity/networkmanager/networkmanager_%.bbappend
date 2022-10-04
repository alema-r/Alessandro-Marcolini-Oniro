# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: MIT

# If the machine has a cellular modem it can set MACHINE_FEATURES+=modem to
# enable build-in support in NetworkManager.
PACKAGECONFIG:append:pn-networkmanager = "${@bb.utils.contains('MACHINE_FEATURES', \
                                          'modem', ' modemmanager ', '', d)}"
