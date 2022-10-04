# SPDX-FileCopyrightText: Huawei Inc.
# SPDX-License-Identifier: MIT

# cairo uses zero-length array tricks that trigger warnings with
# -Warray-bounds.
#
# Those have been verified to be harmless in 1.16.0.
#
# Removing -Werror=array-bounds here allows us to use -Werror=array-bounds
# globally in OPTIMIZE_FOR=security mode while keeping mesa building.

TARGET_CFLAGS:remove = "-Werror=array-bounds"
