# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

SUMMARY = "Systemd mount units for the provided partitions"
SECTION = "base"
DESCRIPTION = "This recipe provides a set of mount units for the partition \
table used for the OS. It becomes the foundation of various functionalities. \
For example, runtime state management as part of a separatae state partition \
(sysdata)"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

SRC_URI = " \
    file://run-mount-boot.mount \
    file://run-mount-devdata.mount \
    file://run-mount-appdata.mount \
    file://run-mount-sysdata.mount \
    "

inherit allarch systemd

SYSTEMD_SERVICE_${PN} = " \
    run-mount-boot.mount \
    run-mount-sysdata.mount \
    run-mount-devdata.mount \
    run-mount-appdata.mount \
    "
SYSTEMD_AUTO_ENABLE = "enable"

LABELS = " \
    ${BOOT_PARTITION_LABEL} \
    ${DEVDATA_PARTITION_LABEL} \
    ${APPDATA_PARTITION_LABEL} \
    ${SYSDATA_PARTITION_LABEL} \
    "

do_install () {
    install -d ${D}${systemd_unitdir}/system
    for label in ${LABELS}; do
        install -m 0644 "${WORKDIR}/run-mount-${label}.mount" "${D}${systemd_unitdir}/system"
    done
}

FILES_${PN} += "${systemd_unitdir}"
