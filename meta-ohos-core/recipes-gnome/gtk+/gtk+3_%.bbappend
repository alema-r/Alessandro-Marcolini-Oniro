# SPDX-FileCopyrightText: Huawei Inc.
# SPDX-License-Identifier: Apache-2.0

# gtk uses zero-length array tricks that trigger warnings with
# -Warray-bounds.
#
# Those have been verified to be harmless in 3.24.14.
#
# Removing -Werror=array-bounds here allows us to use -Werror=array-bounds
# globally in OPTIMIZE_FOR=security mode while keeping gtk building.

TARGET_CFLAGS_remove = "-Werror=array-bounds"
