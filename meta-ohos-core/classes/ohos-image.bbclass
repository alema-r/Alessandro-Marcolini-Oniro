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
