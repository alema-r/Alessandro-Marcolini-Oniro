# SPDX-FileCopyrightText: Huawei Inc.
# SPDX-License-Identifier: MIT

# sed uses non-literal format strings in a few places.
# This has been verified to be harmless in 4.8.
#
# Removing -Werror=format-nonliteral here allows us to use
# -Werror=format-nonliteral globally in OPTIMIZE_FOR=security mode
# while keeping sed building.

TARGET_CFLAGS:remove = "-Werror=format-nonliteral"
