# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

DESCRIPTION = "Containers package group for Oniro core foundation"

inherit packagegroup

PACKAGES = "packagegroup-oniro-containers"

RDEPENDS:packagegroup-oniro-containers = "\
	podman \
	"
