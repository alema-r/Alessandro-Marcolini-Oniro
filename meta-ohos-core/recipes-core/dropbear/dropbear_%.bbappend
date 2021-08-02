# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

inherit writables

WRITABLES = "hostkeys"
WRITABLE_PATH[hostkeys] = "/etc/dropbear"
