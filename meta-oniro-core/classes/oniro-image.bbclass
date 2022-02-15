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

IMAGE_PREPROCESS_COMMAND:append = " ${@ 'systemd_mask_getty;' if bb.utils.contains('DISTRO_FEATURES', 'systemd', True, False, d) and not bb.utils.contains('IMAGE_FEATURES', 'stateless-rootfs', True, False, d) else ''} revert_volatile_dropear_hostkeys"

IMAGE_FEATURES:append = " read-only-rootfs"

#
# wic configuration with support for system update partition setup
#

IMAGE_FSTYPES:append = " squashfs"

WKS_FILE:raspberrypi4-64 ?= "x-raspberrypi.wks.in"

WKS_FILE:seco-intel-b68 ?= "x-gpt-efi-disk.wks.in"
IMAGE_FSTYPES:append:seco-intel-b68 = " wic.bz2 wic.bmap"

# We avoid any other fstypes (for qemu) by default as the OS depends on a
# specific partition table provided through the wic configuration.
IMAGE_FSTYPES:qemux86 ?= "wic wic.bz2"
WKS_FILE:qemux86 ?= "x-qemux86-directdisk.wks.in"
IMAGE_FSTYPES:qemux86-64 ?= "wic wic.bz2"
WKS_FILE:qemux86-64 ?= "x-qemux86-directdisk.wks.in"

WKS_FILE:qemu-generic-arm64 = "x-qemu-efi-disk.wks.in"
IMAGE_FSTYPES:qemu-generic-arm64 += "wic wic.qcow2"

WKS_FILE:seco-imx8mm-c61 ?= "x-imx-uboot-bootpart.wks.in"

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
