# SPDX-FileCopyrightText: Huawei Inc.
# SPDX-License-Identifier: Apache-2.0

# zip uses some #define trickery that triggers warnings with
# -Wformat-nonliteral.
# This has been verified to be harmless in 3.0.
#
# Removing -Werror=format-nonliteral here allows us to use
# -Werror=format-nonliteral globally in OPTIMIZE_FOR=security mode
# while keeping zip building.

TARGET_CFLAGS:remove = "-Werror=format-nonliteral"
