# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${ONIRO_COREBASE}/LICENSES/Apache-2.0.txt;md5=ef3dabb8f39493f4ea410bebc1d01755"

SRC_URI = "file://SoftAP.nmconnection"

do_install_append() {
    install -D -m0600 ${WORKDIR}/SoftAP.nmconnection ${D}${libdir}/NetworkManager/system-connections/SoftAP.nmconnection
}

FILES_${PN} += "${libdir}/NetworkManager/system-connections/"
