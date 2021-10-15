# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

include jool.inc

DESCRIPTION = "Jool SIIT and NAT64 kernel modules for Linux."

LIC_FILES_CHKSUM = "file://../../COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

S = "${WORKDIR}/git/src/mod/"

DEPENDS = "virtual/kernel"

inherit module

EXTRA_OEMAKE += "KERNEL_DIR=${STAGING_KERNEL_DIR} \
                 KERNEL_PATH=${STAGING_KERNEL_DIR} \
                 KERNEL_VERSION=${KERNEL_VERSION} \
                 CC="${KERNEL_CC}" LD="${KERNEL_LD}" \
                 AR="${KERNEL_AR}" \
                 O=${STAGING_KERNEL_BUILDDIR} \
                 KBUILD_EXTRA_SYMBOLS="${B}/common/Module.symvers" \
                 "

MODULES_MODULE_SYMVERS_LOCATION="common/Module.symvers"

module_do_compile () {
        unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS
        # There is a race exposed in parallel build if the common module is not
        # build first. It provides the Module.symvers file which the siit and
        # nat64 modules depend on. Which means we run this first to build the
        # dependency and the rest with the second command.
        oe_runmake -C common
        oe_runmake ${MAKE_TARGETS}
}
