# SPDX-FileCopyrightText: Huawei Inc.
# SPDX-License-Identifier: MIT

# liburcu uses zero-length array tricks causing array-bounds warnings.
# The warnings have been verifited to be harmless in 0.11.1; for extra
# security, this should be re-checked after updating.
#
# Removing -Werror=array-bounds here allows us to use -Werror=array-bounds
# globally in OPTIMIZE_FOR=security mode.

TARGET_CFLAGS:remove = "-Werror=array-bounds"
