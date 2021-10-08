# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

require recipes-core/images/oniro-image-base.bb

SUMMARY = "Dashboard blueprint image: gateway"
DESCRIPTION = "Home Assistant based image targeting a gateway device"
LICENSE = "Apache-2.0"

IMAGE_INSTALL_append = "\
	epiphany-app \
	homeassistant-config-blueprint-dashboard \
	python3-appdaemon \
	python3-homeassistant \
	weston \
	weston-examples \
	weston-init \
	"

# 500 MiB of additional storage for config and runtime data.
# We need a bit more because Google Safe Browsing database case easily go to
# around 200MiB.
# https://gitlab.gnome.org/GNOME/epiphany/-/issues/477
IMAGE_ROOTFS_EXTRA_SPACE = "524288"

# This is a workaround in case the build runs on ZFS with compression on.
# It also gives us extra runtime space.
IMAGE_OVERHEAD_FACTOR = "2"
