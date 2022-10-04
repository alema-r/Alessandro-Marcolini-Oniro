# SPDX-FileCopyrightText: Huawei Inc.
# SPDX-License-Identifier: MIT

# make uses non-literal format strings in a few places.
# This has been verified to be harmless in 4.3
#
# Removing -Werror=format-nonliteral here allows us to use
# -Werror=format-nonliteral globally in OPTIMIZE_FOR=security mode
# while keeping make building.

TARGET_CFLAGS:remove = "-Werror=format-nonliteral"
