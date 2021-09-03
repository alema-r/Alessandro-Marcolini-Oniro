# SPDX-FileCopyrightText: Huawei Inc.
# SPDX-License-Identifier: Apache-2.0

# unzip uses some #define trickery that triggers warnings with
# -Wformat-nonliteral.
# This has been verified to be harmless in 6.0.
#
# Removing -Werror=format-nonliteral here allows us to use
# -Werror=format-nonliteral globally in OPTIMIZE_FOR=security mode
# while keeping unzip building.

TARGET_CFLAGS_remove = "-Wformat-nonliteral -Werror=format-nonliteral"
