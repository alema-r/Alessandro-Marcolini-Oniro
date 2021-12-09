# SPDX-FileCopyrightText: Huawei Inc.
# SPDX-License-Identifier: Apache-2.0

# diffutils uses non-literal format strings in a few places.
# This has been verified to be harmless in 3.7
#
# Removing -Werror=format-nonliteral here allows us to use
# -Werror=format-nonliteral globally in OPTIMIZE_FOR=security mode
# while keeping diffutils building.

TARGET_CFLAGS:remove = "-Werror=format-nonliteral"
