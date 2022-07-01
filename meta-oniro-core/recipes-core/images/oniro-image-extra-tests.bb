# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

require recipes-core/images/oniro-image-extra.bb

SUMMARY = "Oniro Project Wayland image including the base OS \
software stack and tests"

IMAGE_INSTALL:append = "\
    packagegroup-oniro-tests \
"

# This adds ptest packages to the image, and enables empty root password
EXTRA_IMAGE_FEATURES:append = " ptest-pkgs debug-tweaks"

# For testing purposes, we want to have an extra ext4 format for the root
# filesystem.
IMAGE_FSTYPES:append = " ext4"
