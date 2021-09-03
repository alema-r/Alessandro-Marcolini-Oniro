# SPDX-FileCopyrightText: Huawei Inc.
# SPDX-License-Identifier: Apache-2.0

# openssl's opt tool uses a non-literal format string for
# help texts.
# This has been verified to be harmless in 1.1.1k (the "unsafe"
# use of a printf style function happens only with hardcoded,
# safe strings).
#
# Removing -Werror=format-nonliteral here allows us to use
# -Werror=format-nonliteral globally in OPTIMIZE_FOR=security mode
# while keeping openssl building.

TARGET_CFLAGS_remove = "-Werror=format-nonliteral"
