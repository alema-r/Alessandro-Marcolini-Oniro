# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

DESCRIPTION = "Core package group groups for OS foundation"

inherit packagegroup

PACKAGES = "packagegroup-allscenarios-core"

RDEPENDS_packagegroup-allscenarios-core = "\
	x-mounts \
	"
