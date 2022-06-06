# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

require recipes-core/images/oniro-image-extra.bb

SUMMARY = "Oniro Project Wayland development image including the base OS \
software stack and tests"

IMAGE_INSTALL:append = "\
    packagegroup-net-tools \
    "

EXTRA_IMAGE_FEATURES += "dev-pkgs dbg-pkgs src-pkgs tools-sdk tools-debug tools-profile"

# With the EXTRA_IMAGE_FEATURES we need to bump the default size of the rootfs
ROOT_PARTITION_SIZE = "2500M"
