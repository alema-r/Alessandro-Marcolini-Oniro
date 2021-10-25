# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

FILESEXTRAPATHS_prepend := "${THISDIR}/linux:"

SRC_URI += "file://blueprints-misc.cfg"

# Silence the console cursor
CMDLINE_append = " vt.global_cursor_default=0"
