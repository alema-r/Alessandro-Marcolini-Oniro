# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: MIT

python depmod_all_kernels() {
    import re
    import subprocess

    rootfs_dir = d.getVar('IMAGE_ROOTFS')
    pkgdatadir = d.getVar('PKGDATA_DIR')

    modules_dir = os.path.join(rootfs_dir, 'lib', 'modules')

    # PKGDATA_DIR can include multiple kernels so we run depmod for each
    # one of them.
    for direntry in os.listdir(pkgdatadir):
        match = re.match('(.*)-depmod', direntry)
        if not match:
            continue
        kernel_package_name = match.group(1)

        kernel_abi_ver_file = oe.path.join(pkgdatadir, direntry, kernel_package_name + '-abiversion')
        if not os.path.exists(kernel_abi_ver_file):
            bb.fatal("No kernel-abiversion file found (%s), cannot run depmod, aborting" % kernel_abi_ver_file)

        with open(kernel_abi_ver_file) as f:
            kernel_ver = f.read().strip(' \n')

        versioned_modules_dir = os.path.join(rootfs_dir, modules_dir, kernel_ver)

        bb.utils.mkdirhier(versioned_modules_dir)

        bb.note("Running depmodwrapper for %s ..." % versioned_modules_dir)
        depmod_cmd = ['depmodwrapper', '-a', '-b', rootfs_dir, kernel_ver, kernel_package_name]
        try:
            subprocess.check_output(depmod_cmd, stderr=subprocess.STDOUT)
        except subprocess.CalledProcessError as e:
            bb.fatal("Command '%s' returned %d:\n%s" % (e.cmd, e.returncode, e.output))
}
