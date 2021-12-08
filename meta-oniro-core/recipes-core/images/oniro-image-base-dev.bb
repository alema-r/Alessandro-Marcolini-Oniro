# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

require recipes-core/images/core-image-base.bb
require oniro-image-common.inc

SUMMARY = "Oniro Project development image including the base OS software stack"

IMAGE_INSTALL:append = "\
    packagegroup-net-tools \
    "

EXTRA_IMAGE_FEATURES += "dev-pkgs dbg-pkgs src-pkgs tools-sdk tools-debug tools-profile"
