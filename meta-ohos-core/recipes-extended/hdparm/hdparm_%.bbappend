# SPDX-FileCopyrightText: Huawei Inc.
# SPDX-License-Identifier: Apache-2.0

# hdparm's sysfs I/O functions trigger warnings with
# -Wformat-nonliteral, but the format strings are checked
# by other means.
# This has been verified to be harmless in 9.58.
#
# Removing -Werror=format-nonliteral here allows us to use
# -Werror=format-nonliteral globally in OPTIMIZE_FOR=security mode
# while keeping hdparm building.

TARGET_CFLAGS_remove = "-Werror=format-nonliteral"
