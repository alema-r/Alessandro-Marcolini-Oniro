# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

DESCRIPTION = "OpenHarmony tests package group"

inherit packagegroup

PACKAGES = "\
	packagegroup-openharmony-tests \
	"

RDEPENDS_packagegroup-openharmony-tests = "\
	ohos-xts-acts \
	"
