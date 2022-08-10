# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

KBRANCH ?= "oniro/v5.10/base"
KMETA = "kernel-meta"

require recipes-kernel/linux/linux-yocto.inc

SRCREV_machine ?= "452ea6a15ed2ac74789b7b3513777cc94ea3b751"
SRCREV_meta ?= "3b283fa8d4068ff68457b93e07d321c6c06d37e0"

SRC_URI = "git://gitlab.eclipse.org/eclipse/oniro-core/linux.git;protocol=https;name=machine;branch=${KBRANCH}; \
           git://gitlab.eclipse.org/eclipse/oniro-core/linux-meta.git;type=kmeta;protocol=https;name=meta;branch=oniro/v5.10;destsuffix=${KMETA} \
           file://fix-gcc-plugins-with-gcc-11.patch \
"

require recipes-kernel/linux/linux-oniro-tweaks-all.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"
LINUX_VERSION ?= "5.10.61"

DEPENDS += "${@bb.utils.contains('ARCH', 'x86', 'elfutils-native', '', d)}"
DEPENDS += "openssl-native util-linux-native gmp-native"

PV = "${LINUX_VERSION}+git${SRCPV}"
PROVIDES="linux-oniro virtual/kernel"

KCONF_BSP_AUDIT_LEVEL = "1"

COMPATIBLE_MACHINE = "^(qemuarm64|qemux86|qemux86-64|qemuriscv64|qemuriscv32|qemu-generic-arm64)$"

# Functionality flags
KERNEL_EXTRA_FEATURES ?= "features/netfilter/netfilter.scc"
KERNEL_FEATURES:append = " ${KERNEL_EXTRA_FEATURES}"
KERNEL_FEATURES:append:qemuall=" cfg/virtio.scc features/drm-bochs/drm-bochs.scc"
KERNEL_FEATURES:append:qemux86=" cfg/sound.scc cfg/paravirt_kvm.scc"
KERNEL_FEATURES:append:qemux86-64=" cfg/sound.scc cfg/paravirt_kvm.scc"
KERNEL_FEATURES:append = " ${@bb.utils.contains("TUNE_FEATURES", "mx32", " cfg/x32.scc", "", d)}"
KERNEL_FEATURES:append = " ${@bb.utils.contains("DISTRO_FEATURES", "ptest", " features/scsi/scsi-debug.scc", "", d)}"
KERNEL_FEATURES:append = " ${@bb.utils.contains("DISTRO_FEATURES", "ptest", " features/gpio/mockup.scc", "", d)}"
