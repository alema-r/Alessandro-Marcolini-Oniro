# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: MIT

# Make rootfstype defined based on ROOT_FSTYPE (selected by wic configuration)
CMDLINE_ROOT_FSTYPE = "rootfstype=${ROOT_FSTYPE} ro"
