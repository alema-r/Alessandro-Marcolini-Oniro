# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

DESCRIPTION = "AllScenariOS tests package group"

inherit packagegroup

PACKAGES = "\
	packagegroup-allscenarios-tests \
	"

RDEPENDS_packagegroup-allscenarios-tests = "\
	ohos-xts-acts \
	"
