# Base this image on core-image-base
include recipes-core/images/core-image-base.bb

IMAGE_INSTALL_append = " \
			packagegroup-net-essentials \
			packagegroup-net-tools \
			packagegroup-ble-essentials \
			"
