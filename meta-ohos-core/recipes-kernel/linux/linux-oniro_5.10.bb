# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

KBRANCH ?= "oniro/v5.10/base"
KMETA = "kernel-meta"

require recipes-kernel/linux/linux-yocto.inc

# board-specific branches
KBRANCH_qemuarm ?= "oniro/v5.10/hw/qemuarm"

SRCREV_machine_qemuarm ?= "b8d9461a3957dba555b1ae82481cf57f3ce9e8b1"
SRCREV_machine ?= "452ea6a15ed2ac74789b7b3513777cc94ea3b751"
SRCREV_meta ?= "3b283fa8d4068ff68457b93e07d321c6c06d37e0"

SRC_URI = "git://git.ostc-eu.org/OSTC/OHOS/components/kernel/linux.git;protocol=http;name=machine;branch=${KBRANCH}; \
	    git://git.ostc-eu.org/OSTC/OHOS/components/kernel/linux-meta.git;type=kmeta;protocol=http;name=meta;branch=oniro/v5.10;destsuffix=${KMETA}"

require recipes-kernel/linux/linux-oniro-tweaks-all.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"
LINUX_VERSION ?= "5.10.61"

DEPENDS += "${@bb.utils.contains('ARCH', 'x86', 'elfutils-native', '', d)}"
DEPENDS += "openssl-native util-linux-native gmp-native"

PV = "${LINUX_VERSION}+git${SRCPV}"
PROVIDES="linux-oniro virtual/kernel"

KCONF_BSP_AUDIT_LEVEL = "1"

COMPATIBLE_MACHINE = "qemuarm|qemuarm64|qemux86|qemux86-64|qemuriscv64|qemuriscv32"

# Functionality flags
KERNEL_EXTRA_FEATURES ?= "features/netfilter/netfilter.scc"
KERNEL_FEATURES_append = " ${KERNEL_EXTRA_FEATURES}"
KERNEL_FEATURES_append_qemuall=" cfg/virtio.scc features/drm-bochs/drm-bochs.scc"
KERNEL_FEATURES_append_qemux86=" cfg/sound.scc cfg/paravirt_kvm.scc"
KERNEL_FEATURES_append_qemux86-64=" cfg/sound.scc cfg/paravirt_kvm.scc"
KERNEL_FEATURES_append = " ${@bb.utils.contains("TUNE_FEATURES", "mx32", " cfg/x32.scc", "", d)}"
KERNEL_FEATURES_append = " ${@bb.utils.contains("DISTRO_FEATURES", "ptest", " features/scsi/scsi-debug.scc", "", d)}"
KERNEL_FEATURES_append = " ${@bb.utils.contains("DISTRO_FEATURES", "ptest", " features/gpio/mockup.scc", "", d)}"
