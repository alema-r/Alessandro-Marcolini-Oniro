require recipes-core/images/openharmony-image-base.bb

SUMMARY = "Dashboard demo image: gateway"
DESCRIPTION = "Home Assistant based image targeting a gateway device"
LICENSE = "Apache-2.0"

BROWSER ?= "epiphany"

IMAGE_INSTALL_append = "\
	${BROWSER} \
	python3-appdaemon \
	python3-homeassistant \
	weston \
	weston-examples \
	weston-init \
	"

# 100 MiB of additional storage for config and runtime data.
IMAGE_ROOTFS_EXTRA_SPACE = "102400"

# This is a workaround in case the build runs on ZFS with compression on.
# It also gives us extra runtime space.
IMAGE_OVERHEAD_FACTOR = "2"
