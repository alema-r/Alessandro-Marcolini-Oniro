# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

# WIC configuration definitions that are meant to be common across the
# supported devices. This configuration is taking into consideration OTA design
# and strategies.

# Boot partition
BOOT_PARTITION_LABEL ?= "boot"
BOOT_PARTITION_SIZE ?= "512M"

# Root partitions
# No label as default filesystem is squashfs.
# The SECO B68 board pulls in almost half a Gigabyte worth of linux-firmware.
ROOT_PARTITION_SIZE:seco-intel-b68 ?= "2G"
ROOT_PARTITION_SIZE ?= "1G"
ROOTA_PARTITION_SIZE = "${ROOT_PARTITION_SIZE}"
ROOTB_PARTITION_SIZE = "${ROOT_PARTITION_SIZE}"

# Device data partition
DEVDATA_PARTITION_LABEL ?= "devdata"
DEVDATA_PARTITION_SIZE ?= "8M"

# System data partition
SYSDATA_PARTITION_LABEL ?= "sysdata"
SYSDATA_PARTITION_SIZE ?= "512M"

# Application data partition
APPDATA_PARTITION_LABEL ?= "appdata"
APPDATA_PARTITION_SIZE ?= "1G"

# Default root filesystem type is squashfs.
ROOT_FSTYPE ?= "squashfs"

WIC_BOOTLOADER_TIMEOUT ?= "5"

#
# Use this to include extra wic configuration per partition
#
WIC_ROOTA_PARTITION_EXTRA_ARGS ??= ""
WIC_ROOTB_PARTITION_EXTRA_ARGS ??= ""
WIC_DEVDATA_PARTITION_EXTRA_ARGS ??= ""
WIC_SYSDATA_PARTITION_EXTRA_ARGS ??= ""
WIC_APPDATA_PARTITION_EXTRA_ARGS ??= ""

WIC_ROOTA_PARTITION ?= "part / --source rootfs --use-uuid --fstype=${ROOT_FSTYPE} \
                        ${@bb.utils.contains('ROOT_FSTYPE', 'ext4', '--mkfs-extraopts \'-T default\'', '', d)} \
                        --align 4096 --fixed-size ${ROOTA_PARTITION_SIZE} \
                        ${WIC_ROOTA_PARTITION_EXTRA_ARGS}"
WIC_ROOTB_PARTITION ?= "part / --source rootfs --use-uuid --fstype=${ROOT_FSTYPE} \
                        ${@bb.utils.contains('ROOT_FSTYPE', 'ext4', '--mkfs-extraopts \'-T default\'', '', d)} \
                        --align 4096 --fixed-size ${ROOTB_PARTITION_SIZE} \
                        ${WIC_ROOTB_PARTITION_EXTRA_ARGS}"
WIC_DEVDATA_PARTITION ?= "part --fstype=ext4 --label ${DEVDATA_PARTITION_LABEL} --mkfs-extraopts '-T default' --align 4096 --fixed-size ${DEVDATA_PARTITION_SIZE} ${WIC_DEVDATA_PARTITION_EXTRA_ARGS}"
WIC_SYSDATA_PARTITION ?= "part --fstype=ext4 --label ${SYSDATA_PARTITION_LABEL} --mkfs-extraopts '-T default' --align 4096 --fixed-size ${SYSDATA_PARTITION_SIZE} ${WIC_SYSDATA_PARTITION_EXTRA_ARGS}"
WIC_APPDATA_PARTITION ?= "part --fstype=ext4 --label ${APPDATA_PARTITION_LABEL} --mkfs-extraopts '-T default' --align 4096 --fixed-size ${APPDATA_PARTITION_SIZE} ${WIC_APPDATA_PARTITION_EXTRA_ARGS}"

# The construction of the image depends on the files deployed by the
# oniro-grub-bootconf package, namely the empty grubenv file.
do_image_wic[depends] += " \
    oniro-grub-bootconf:do_deploy \
"
