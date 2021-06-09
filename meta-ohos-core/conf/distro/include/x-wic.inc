# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

# WIC configuration definitions that are meant to be common across the
# supported devices. This configuration is taking into consideration OTA design
# and strategies.

BOOT_PARTITION_SIZE ?= "512M"
# The SECO B68 board pulls in almost half a Gigabyte worth of linux-firmware.
ROOT_PARTITION_SIZE_seco-intel-b68 ?= "1536M"
ROOT_PARTITION_SIZE ?= "1G"
ROOTA_PARTITION_SIZE = "${ROOT_PARTITION_SIZE}"
ROOTB_PARTITION_SIZE = "${ROOT_PARTITION_SIZE}"
DEVICEDATA_PARTITION_SIZE ?= "8M"
SYSDATA_PARTITION_SIZE ?= "512M"
APPDATA_PARTITION_SIZE ?= "1G"

#
# Use this to include extra wic configuration per partition
#
WIC_ROOTA_PARTITION_EXTRA_ARGS ??= ""
WIC_ROOTB_PARTITION_EXTRA_ARGS ??= ""
WIC_DEVICEDATA_PARTITION_EXTRA_ARGS ??= ""
WIC_SYSDATA_PARTITION_EXTRA_ARGS ??= ""
WIC_APPDATA_PARTITION_EXTRA_ARGS ??= ""

WIC_ROOTA_PARTITION      ?= "part / --source rootfs --use-uuid --fstype=ext4 --label x-sys-a    --mkfs-extraopts '-T default' --align 4096 --fixed-size ${ROOTA_PARTITION_SIZE}      ${WIC_ROOTA_PARTITION_EXTRA_ARGS}"
WIC_ROOTB_PARTITION      ?= "part                              --fstype=ext4 --label x-sys-b    --mkfs-extraopts '-T default' --align 4096 --fixed-size ${ROOTB_PARTITION_SIZE}      ${WIC_ROOTB_PARTITION_EXTRA_ARGS}"
WIC_DEVICEDATA_PARTITION ?= "part                              --fstype=ext4 --label x-dev-data --mkfs-extraopts '-T default' --align 4096 --fixed-size ${DEVICEDATA_PARTITION_SIZE} ${WIC_DEVICEDATA_PARTITION_EXTRA_ARGS}"
WIC_SYSDATA_PARTITION    ?= "part                              --fstype=ext4 --label x-sys-data --mkfs-extraopts '-T default' --align 4096 --fixed-size ${SYSDATA_PARTITION_SIZE}    ${WIC_SYSDATA_PARTITION_EXTRA_ARGS}"
WIC_APPDATA_PARTITION    ?= "part                              --fstype=ext4 --label x-app-data --mkfs-extraopts '-T default' --align 4096 --fixed-size ${APPDATA_PARTITION_SIZE}    ${WIC_APPDATA_PARTITION_EXTRA_ARGS}"
