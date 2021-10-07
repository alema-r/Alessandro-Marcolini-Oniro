# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

require recipes-graphics/images/core-image-weston.bb
require allscenarios-image-common.inc

SUMMARY = "AllScenariOS Wayland image including the base OS \
software stack"

# The weston image pulls in package manager. We have a root filesystem that is
# defaulted to read-only so a package manager would make little sense using
# such a image feature.
IMAGE_FEATURES_remove = "package-management"
