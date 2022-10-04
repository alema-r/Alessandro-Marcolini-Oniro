# SPDX-FileCopyrightText: Huawei Inc.
# SPDX-License-Identifier: MIT

# gmp uses non-literal format strings to implement
# its own printing functions.
# This has been verified to be harmless in 6.2.0.
#
# Removing -Werror=format-nonliteral here allows us to use
# -Werror=format-nonliteral globally in OPTIMIZE_FOR=security mode
# while keeping gmp building.

TARGET_CFLAGS:remove = "-Werror=format-nonliteral"
