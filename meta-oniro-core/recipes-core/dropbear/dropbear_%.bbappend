# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: MIT

inherit writables

WRITABLES = "hostkeys"
WRITABLE_PATH[hostkeys] = "/etc/dropbear"
