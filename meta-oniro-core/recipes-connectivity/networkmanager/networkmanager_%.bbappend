# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: MIT

inherit writables

WRITABLES = "system-connections"
WRITABLE_PATH[system-connections] = "/etc/NetworkManager/system-connections"
