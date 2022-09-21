# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

inherit writables

WRITABLES = "data"
WRITABLE_PATH[data] = "/var/lib/thread/"

RDEPENDS:${PN} += "ipset"
