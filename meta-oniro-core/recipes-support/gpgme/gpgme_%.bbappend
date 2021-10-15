# SPDX-FileCopyrightText: Huawei Inc.
# SPDX-License-Identifier: Apache-2.0

# gpgme uses zero-length array tricks causing array-bounds warnings.
# The warnings have been verifited to be harmless in 1.13.1; for extra
# security, this should be re-checked after updating.
#
# Removing -Werror=array-bounds here allows us to use -Werror=array-bounds
# globally in OPTIMIZE_FOR=security mode.

TARGET_CFLAGS_remove = "-Werror=array-bounds"
