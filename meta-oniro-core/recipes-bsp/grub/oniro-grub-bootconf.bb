# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

SUMMARY = "GRUB configuration file specialized for Oniro reference images"
DESCRIPTION = "This GRUB configuration file has the following features: \
- Support for A/B immutable system image \
- Support for try-mode booting compatible with RAUC \
- Kernel is loaded from the system image \
"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

SRC_URI = " \
	file://grub.cfg \
	file://grubenv \
"

inherit deploy

RPROVIDES:${PN} += "virtual-grub-bootconf"

# Get definitions of the EFI_ variables.
require conf/image-uefi.conf

S = "${WORKDIR}"

do_install() {
	# Install the boot assets into the rootfs. Those can be used to handle boot
	# asset updates by SysOTA later on. Note that external grub.cfg is
	# temporary. It must be merged into the grub binary after a period of
	# testing.
	install -d ${D}${EFI_FILES_PATH}
	install -m 644 grub.cfg ${D}${EFI_FILES_PATH}/grub.cfg
	sed -i "s/@KERNEL_IMAGETYPE@/${KERNEL_IMAGETYPE}/g" ${D}${EFI_FILES_PATH}/grub.cfg
	install -m 644 grubenv ${D}${EFI_FILES_PATH}/grubenv
}

do_deploy() {
	# Install the boot assets into DEPLOYDIR. The deploy bbclass
	# eventually copies those into the boot partition.
	install -m 644 ${D}${EFI_FILES_PATH}/grub.cfg ${DEPLOYDIR}
	# Unlike grub.cfg, nothing installs this file to the boot partition
	# automatically. It is handled by extending IMAGE_EFI_BOOT_FILES from
	# conf/distro/oniro-linux.conf.
	install -m 644 ${D}${EFI_FILES_PATH}/grubenv ${DEPLOYDIR}
}

# Cargo-cult from a similar recipe.
addtask deploy after do_install before do_build

FILES:${PN} = "\
	${EFI_FILES_PATH}/grub.cfg \
	${EFI_FILES_PATH}/grubenv \
"

PACKAGE_ARCH = "${MACHINE_ARCH}"
