# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

SUMMARY = "Ultralightweight JSON parser library in ANSI C"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=218947f77e8cb8e2fa02918dc41c50d0 \
                    file://tests/unity/docs/license.txt;md5=b7dd0dffc9dda6a87fa96e6ba7f9ce6c"

SRC_URI = "https://github.com/DaveGamble/cJSON/archive/v${PV}.tar.gz"
SRC_URI[md5sum] = "ff0557033e8374033107d40ca79bd52d"
SRC_URI[sha1sum] = "b401e57edbc9377f0b4139206297984e073c4e47"
SRC_URI[sha256sum] = "fb50a663eefdc76bafa80c82bc045af13b1363e8f45cec8b442007aef6a41343"
SRC_URI[sha384sum] = "52d3d168bf6ee941e8c7fc0d8f53f81d43fb66b91e2518b7e9f0daf71ae2edf6ca721087d6ec7c781a9732e5db09f45d"
SRC_URI[sha512sum] = "8de1dedc123ed025a9cbe6764e5963eb0550f726d06a8f6bedfe05b84e852cd9c1587cd381669663073967f42be894a535ba239013f304ce544c3b15a6477c01"

S = "${WORKDIR}/cJSON-${PV}"

inherit cmake
