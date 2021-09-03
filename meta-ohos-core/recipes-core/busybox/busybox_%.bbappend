# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
SRC_URI += "file://busybox-1.31.1-Wformat-security.patch \
	file://allscenarios.cfg"

# busybox implements some of its own string handling that triggers
# warnings with -Wformat-nonliteral.
# This has been verified to be harmless in 1.31.1.
#
# Removing -Werror=format-nonliteral here allows us to use
# -Werror=format-nonliteral globally in OPTIMIZE_FOR=security mode
# while keeping busybox building.

TARGET_CFLAGS_remove = "-Wformat-nonliteral -Werror=format-nonliteral"

