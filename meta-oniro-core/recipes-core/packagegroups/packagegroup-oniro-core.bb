# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

DESCRIPTION = "Core package group groups for OS foundation"

inherit packagegroup

PACKAGES = "packagegroup-oniro-core"

RDEPENDS_packagegroup-oniro-core = "\
	oniro-mounts \
	oniro-sysctl \
	"
