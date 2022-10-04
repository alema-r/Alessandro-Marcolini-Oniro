# SPDX-FileCopyrightText: Huawei Inc.
# SPDX-License-Identifier: MIT

# grub's MD-RAID1 implementation uses zero-length array tricks causing
# array-bounds warnings.
# The warnings have been verifited to be harmless in 2.06; for extra
# security, this should be re-checked after updating.
#
# Removing -Werror=array-bounds here allows us to use -Werror=array-bounds
# globally in OPTIMIZE_FOR=security mode.

TARGET_CFLAGS:remove = "-Werror=array-bounds"

# Make sure that GRUB can load squashfs, so that it can mount the system image
# and load the kernel, or other modules, from there directly. In addition add
# echo and halt modules, so that the GRUB boot script can print diagnostic
# messages or shut down on error.
GRUB_BUILDIN:append = " squash4 halt echo"

# TODO oe-core avoids armv7ve builds. This is more of a blanket fix because
# grub supports this arch. It doesn't support hardfp configuration that can't
# be forced into softfp with a compiler flag. qemuarm-efi defaults to softfp.
COMPATIBLE_HOST:qemuarm-efi = 'arm.*-(linux.*|freebsd.*)'
