# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: MIT

inherit writables

WRITABLES = "rootuser-netconfig"
WRITABLE_PATH[rootuser-netconfig] = "/etc/cni/net.d"
