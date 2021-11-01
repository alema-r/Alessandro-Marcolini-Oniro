# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

# Oniro Project functionality for images

# List of tty to mask getty for
SYSTEMD_MASK_GETTY ?= ""

systemd_mask_getty () {
    if [ -e ${IMAGE_ROOTFS}${root_prefix}/lib/systemd/systemd ]; then
        for i in ${SYSTEMD_MASK_GETTY}; do
            systemctl --root="${IMAGE_ROOTFS}" mask "getty@${i}.service"
        done
    fi
}

# read_only_rootfs_hook
revert_volatile_dropear_hostkeys () {
    sed -i '/DROPBEAR_RSAKEY_DIR/d' ${IMAGE_ROOTFS}/etc/default/dropbear
}

IMAGE_PREPROCESS_COMMAND_append = " ${@ 'systemd_mask_getty;' if bb.utils.contains('DISTRO_FEATURES', 'systemd', True, False, d) and not bb.utils.contains('IMAGE_FEATURES', 'stateless-rootfs', True, False, d) else ''} revert_volatile_dropear_hostkeys"

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

#
# Deploy boot partition artifacts to the root partition. This is part of the
# system update design where the update payload is defined as the root
# filesystem. In order to avoid multiple payloads, we include the boot assets
# in the root filesystem.
#

# The path relative to the root of the root filesystem where to deploy the boot
# artifacts.
ROOTFS_BOOT_ARTIFACTS_PATH ?= "usr/lib/sysota/boot-assets"

def run_cmd(cmd):
    """
    Run a cmd (string) and fail if exit code is non zero.
    """

    import subprocess
    try:
        subprocess.check_call(cmd.split())
    except subprocess.CalledProcessError as e:
        bb.fatal("Command \"%s\" failed with exit code %s." % (cmd , e.returncode))

python deploy_boot_artifacts_to_rootfs() {
    """
    This functionality follows the general logic in bootimg-partition but is
    adapted to work as a root filesystem post install function.
    """

    import re
    from glob import glob

    boot_files = d.getVar('IMAGE_BOOT_FILES')
    if not boot_files:
        # Nothing to do if IMAGE_BOOT_FILES is not defined. Custom wic plugins
        # are not supported.
        return
    deploy_dir = d.getVar('DEPLOY_DIR_IMAGE')
    rootfs_dir = d.getVar('IMAGE_ROOTFS')
    rootfs_boot_dir = d.getVar('ROOTFS_BOOT_ARTIFACTS_PATH')
    if rootfs_boot_dir:
        rootfs_dir = os.path.join(rootfs_dir, rootfs_boot_dir)

    deploy_files = []
    for src_entry in re.findall(r'[\w;\-\./\*]+', boot_files):
        if ';' in src_entry:
            dst_entry = tuple(src_entry.split(';'))
            if not dst_entry[0] or not dst_entry[1]:
                bb.fatal('Malformed IMAGE_BOOT_FILES entry: %s' % src_entry)
        else:
            dst_entry = (src_entry, src_entry)
        bb.debug(1, 'Destination entry: %r' % str(dst_entry))
        deploy_files.append(dst_entry)

    install_task = [];
    for deploy_entry in deploy_files:
        src, dst = deploy_entry
        if '*' in src:
            # by default install files under their basename
            entry_name_fn = os.path.basename
            if dst != src:
                # unless a target name was given, then treat name
                # as a directory and append a basename
                entry_name_fn = lambda name: \
                                os.path.join(dst,
                                             os.path.basename(name))

            srcs = glob(os.path.join(deploy_dir, src))

            bb.debug(1, 'Globbed sources: %s' % ', '.join(srcs))
            for entry in srcs:
                src = os.path.relpath(entry, deploy_dir)
                entry_dst_name = entry_name_fn(entry)
                install_task.append((src, entry_dst_name))
        else:
            install_task.append((src, dst))

    for task in set(install_task):
        src_path, dst_path = task
        bb.debug(1, 'Install %s as %s' % (src_path, dst_path))
        if os.path.exists(os.path.join(rootfs_dir, dst_path)):
            bb.fatal("%s already exists in the root filesystem. Fix to avoid overwriting files." % dst_path)
        install_cmd = "install -m 0644 -D %s %s" \
                      % (os.path.join(deploy_dir, src_path),
                         os.path.join(rootfs_dir, dst_path))
        run_cmd(install_cmd)
}

ROOTFS_POSTPROCESS_COMMAND += "deploy_boot_artifacts_to_rootfs;"
