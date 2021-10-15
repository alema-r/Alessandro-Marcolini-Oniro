# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

UBOOT_EXTLINUX_KERNEL_ARGS = "rootwait ro"

inherit deploy

do_deploy[sstate-outputdirs] = "${DEPLOY_DIR_IMAGE}/bootloader/extlinux"
do_deploy() {
    # Deploy files too so wic can pick them up from DEPLOYDIR
    if ! [ -z "$(ls -A ${B})" ]; then
        cp -r --reflink=auto ${B}/* ${DEPLOYDIR}
    fi
}
addtask deploy before do_build after do_compile
