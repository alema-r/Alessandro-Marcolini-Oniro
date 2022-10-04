# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: MIT

DESCRIPTION = "Containers package group for Oniro core foundation"

inherit packagegroup

PACKAGES = "packagegroup-oniro-containers"

RDEPENDS:packagegroup-oniro-containers = "\
	podman \
	"
