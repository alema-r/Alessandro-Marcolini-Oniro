# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

inherit writables

WRITABLES = "rootuser-netconfig"
WRITABLE_PATH[rootuser-netconfig] = "/etc/cni/net.d"
