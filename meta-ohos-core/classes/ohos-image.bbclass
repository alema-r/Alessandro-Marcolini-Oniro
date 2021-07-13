# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

# All Scenarios OS functionality for images

# List of tty to mask getty for
SYSTEMD_MASK_GETTY ?= ""

systemd_mask_getty () {
    if [ -e ${IMAGE_ROOTFS}${root_prefix}/lib/systemd/systemd ]; then
        for i in ${SYSTEMD_MASK_GETTY}; do
            systemctl --root="${IMAGE_ROOTFS}" mask "getty@${i}.service"
        done
    fi
}

IMAGE_PREPROCESS_COMMAND_append = " ${@ 'systemd_mask_getty;' if bb.utils.contains('DISTRO_FEATURES', 'systemd', True, False, d) and not bb.utils.contains('IMAGE_FEATURES', 'stateless-rootfs', True, False, d) else ''}"

IMAGE_FEATURES_append = " read-only-rootfs"

# Convert all KERNEL_DEVICETREE enties to IMAGE_BOOT_FILES entries
def dtb_boot_files(d):
    k_dt = d.getVar('KERNEL_DEVICETREE')
    if not k_dt:
        return ''
    return ' '.join(['kernel/{dtb};{dtb}'.format(dtb=dt) for dt in k_dt.split()])

# Convert all extlinux files to IMAGE_BOOT_FILES entries
def extlinux_boot_files(d):
    import os
    deploy_dir_image = d.getVar('DEPLOY_DIR_IMAGE')
    deploy_extlinux_files = os.path.join(deploy_dir_image, 'bootloader/extlinux')
    boot_files = []
    for root, _, files in os.walk(deploy_extlinux_files):
        for file in files:
            src = os.path.relpath(os.path.join(root, file), deploy_dir_image)
            dst = os.path.relpath(os.path.join(root, file), deploy_extlinux_files)
            boot_files.append('{0};{1}'.format(src, dst))
    if not boot_files:
        return ''
    return ' '.join(boot_files)

#
# wic configuration with support for system update partition setup
#

IMAGE_FSTYPES_append = " squashfs"

WKS_FILE_raspberrypi4-64 ?= "x-raspberrypi.wks.in"

WKS_FILE_seco-intel-b68 ?= "x-efi-systemd-microcode.wks.in"
IMAGE_FSTYPES_append_seco-intel-b68 = " wic.bz2 wic.bmap"

WKS_FILE_stm32mp1-av96 ?= "x-avenger96.wks.in"
IMAGE_BOOT_FILES_stm32mp1-av96 ?= " \
    kernel/${KERNEL_IMAGETYPE};${KERNEL_IMAGETYPE} \
    ${@dtb_boot_files(d)} \
    ${@extlinux_boot_files(d)} \
    "
IMAGE_FSTYPES_append_stm32mp1-av96 = " wic.bz2 wic.bmap"
WKS_FILE_DEPENDS_stm32mp1-av96 ?= " \
    u-boot-stm32mp-extlinux \
    virtual/bootloader \
    virtual/trusted-firmware-a \
    virtual/kernel \
    "
# TODO: ST u-boot hardcodes this value. Configure wic as well so it matches the
# uboot configuration.
WIC_ROOTA_PARTITION_EXTRA_ARGS_stm32mp1-av96 ?= "--uuid e91c4e10-16e6-4c0e-bd0e-77becf4a3582"

# We avoid any other fstypes (for qemu) by default as the OS depends on a
# specific partition table provided through the wic configuration.
IMAGE_FSTYPES_qemux86 ?= "wic wic.bz2"
WKS_FILE_qemux86 ?= "x-qemux86-directdisk.wks.in"
IMAGE_FSTYPES_qemux86-64 ?= "wic wic.bz2"
WKS_FILE_qemux86-64 ?= "x-qemux86-directdisk.wks.in"

WKS_FILE_seco-imx8mm-c61 ?= "x-imx-uboot-bootpart.wks.in"
