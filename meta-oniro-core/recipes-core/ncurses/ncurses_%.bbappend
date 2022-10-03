# SPDX-FileCopyrightText: Huawei Inc.
# SPDX-License-Identifier: MIT

# ncurses' termcap to terminfo converter uses a non-literal
# format string.
# This has been verified to be harmless in 6.2 (the "unsafe"
# use of sscanf happens only with hardcoded, safe strings).
#
# Removing -Werror=format-nonliteral here allows us to use
# -Werror=format-nonliteral globally in OPTIMIZE_FOR=security mode
# while keeping ncurses building.

TARGET_CFLAGS:remove = "-Werror=format-nonliteral"
