# SPDX-FileCopyrightText: Huawei Inc.
# SPDX-License-Identifier: MIT

# openssl's opt tool uses a non-literal format string for
# help texts.
# This has been verified to be harmless in 1.1.1k (the "unsafe"
# use of a printf style function happens only with hardcoded,
# safe strings).
#
# Removing -Werror=format-nonliteral here allows us to use
# -Werror=format-nonliteral globally in OPTIMIZE_FOR=security mode
# while keeping openssl building.

TARGET_CFLAGS:remove = "-Werror=format-nonliteral"
