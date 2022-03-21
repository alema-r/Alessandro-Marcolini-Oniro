# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

# Common settings for all Oniro images

inherit oniro-image

IMAGE_INSTALL:append = "\
			packagegroup-oniro-core \
			packagegroup-net-essentials \
			packagegroup-ble-essentials \
			sysota \
			rauc-hawkbit-updater \
			kernel-image \
			"

IMAGE_INSTALL:append = " ${@bb.utils.contains('MACHINE_FEATURES', 'optee', 'optee-client', '', d)} "
