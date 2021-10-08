# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

# Common settings for all Oniro images

inherit ohos-image

IMAGE_INSTALL_append = "\
			packagegroup-oniro-core \
			packagegroup-net-essentials \
			packagegroup-ble-essentials \
			sysota \
			"

IMAGE_INSTALL_append = " ${@bb.utils.contains('MACHINE_FEATURES', 'optee', 'optee-client', '', d)} "
