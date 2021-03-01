# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

# Common settings for all OpenHarmony images

inherit ohos-image

IMAGE_INSTALL_append = "\
			packagegroup-net-essentials \
			packagegroup-net-tools \
			packagegroup-ble-essentials \
			gdbserver \
			"

# This is a workaround in case the build runs on ZFS with compression on.
# It also gives us extra runtime space.
IMAGE_OVERHEAD_FACTOR = "2"
