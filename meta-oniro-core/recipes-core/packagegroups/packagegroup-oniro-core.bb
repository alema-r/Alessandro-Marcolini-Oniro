# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: MIT

DESCRIPTION = "Core package group groups for OS foundation"

inherit packagegroup

PACKAGES = "packagegroup-oniro-core"

RDEPENDS:packagegroup-oniro-core = "\
	oniro-mounts \
	oniro-sysctl \
	oniro-modprobe \
	"
