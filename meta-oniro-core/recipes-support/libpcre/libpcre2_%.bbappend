# SPDX-FileCopyrightText: Huawei Inc.
# SPDX-License-Identifier: MIT

# -Werror=array-bounds gets confused over negative array indices in
# pcre2test.
# The warnings have been verifited to be harmless in 10.34; for extra
# security, this should be re-checked after updating.
#
# Removing -Werror=array-bounds here allows us to use -Werror=array-bounds
# globally in OPTIMIZE_FOR=security mode.

TARGET_CFLAGS:remove = "-Werror=array-bounds"
