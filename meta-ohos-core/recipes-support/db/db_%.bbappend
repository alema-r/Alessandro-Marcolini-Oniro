# SPDX-FileCopyrightText: Huawei Inc.
# SPDX-License-Identifier: Apache-2.0

# db converter uses non-literal format strings
# in a few places.
# This has been verified to be harmless in 5.3.28.
#
# Removing -Werror=format-nonliteral here allows us to use
# -Werror=format-nonliteral globally in OPTIMIZE_FOR=security mode
# while keeping db building.

TARGET_CFLAGS_remove = "-Werror=format-nonliteral"
