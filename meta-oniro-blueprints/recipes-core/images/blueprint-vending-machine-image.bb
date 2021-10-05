# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

HOMEPAGE = "https://docs.oniroproject.org/"
SUMMARY = "Vending machine blueprint image"
DESCRIPTION = "The Vending Machine image is composed of a couple of applications"
LICENSE = "Apache-2.0"

require recipes-core/images/oniro-image-base.bb

REQUIRED_DISTRO_FEATURES = "wayland"

IMAGE_INSTALL_append = "\
	vending-machine-control-application \
	vending-machine-ui-application \
	weston \
	weston-init \
	"
