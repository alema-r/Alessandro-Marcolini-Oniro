# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

# Common settings for all Oniro images

inherit oniro-image

# Remove some of the default Poky IMAGE_FEATURES we inherited, but do not need.
# Best practices here are IMAGE_FEATURES should be used within image recipes.
IMAGE_FEATURES:remove = " nfs-server nfs-client nfs-utils"

IMAGE_INSTALL:append = "\
			packagegroup-oniro-containers \
			packagegroup-oniro-core \
			packagegroup-net-essentials \
			packagegroup-ble-essentials \
			sysota \
			rauc-hawkbit-updater \
			kernel-image \
			"

IMAGE_INSTALL:append = " ${@bb.utils.contains('MACHINE_FEATURES', 'optee', 'optee-client', '', d)} "

# If the machine has a cellular modem it can set MACHINE_FEATURES+=modem to
# add the needed packages into the image. In the special case of an USB cellular
# modem adding MACHINE_FEATURES+="modem usbmodem" ensures to have the usb-modeswitch
# package as well.
IMAGE_INSTALL:append = " ${@bb.utils.contains('MACHINE_FEATURES', 'modem', 'networkmanager-wwan modemmanager', '', d)} "
IMAGE_INSTALL:append = " ${@bb.utils.contains('MACHINE_FEATURES', 'usbmodem', 'usb-modeswitch', '', d)} "
