# Base this image on core-image-weston
include recipes-graphics/images/core-image-weston.bb

IMAGE_INSTALL_append = " \
			packagegroup-net-essentials \
			packagegroup-net-tools \
			packagegroup-ble-essentials \
			"
