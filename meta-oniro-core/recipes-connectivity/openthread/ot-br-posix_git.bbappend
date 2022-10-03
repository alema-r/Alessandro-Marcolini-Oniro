# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: MIT

inherit writables

WRITABLES = "data"
WRITABLE_PATH[data] = "/var/lib/thread/"

RDEPENDS:${PN} += "ipset"
