# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

require recipes-core/images/allscenarios-image-extra.bb

SUMMARY = "AllScenariOS Wayland image including the base OS \
software stack and tests"

IMAGE_INSTALL_append = "\
    packagegroup-allscenarios-tests \
"
