# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

require recipes-core/images/oniro-image-base.bb

SUMMARY = "Oniro Project image including the base OS software stack and tests"

IMAGE_INSTALL:append = "\
    packagegroup-oniro-tests \
    ltp \
    perf \
    kernel-selftest \
"

# This adds ptest packages to the image
EXTRA_IMAGE_FEATURES:append = " ptest-pkgs"

# For testing purposes, we want to have an extra ext4 format for the root
# filesystem.
IMAGE_FSTYPES:append = " ext4"

ROOT_PARTITION_SIZE = "2500M"
