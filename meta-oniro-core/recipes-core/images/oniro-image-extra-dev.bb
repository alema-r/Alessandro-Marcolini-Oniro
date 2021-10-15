# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

require recipes-core/images/oniro-image-extra.bb

SUMMARY = "Oniro Project Wayland development image including the base OS \
software stack and tests"

IMAGE_INSTALL_append = "\
    packagegroup-net-tools \
    "

EXTRA_IMAGE_FEATURES += "dev-pkgs dbg-pkgs src-pkgs tools-sdk tools-debug tools-profile"

# With the EXTRA_IMAGE_FEATURES we need to bump the size of the rootfs slightly
ROOT_PARTITION_SIZE = "1300M"
