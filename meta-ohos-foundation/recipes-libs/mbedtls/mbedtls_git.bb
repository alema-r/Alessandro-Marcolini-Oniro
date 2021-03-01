# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

SUMMARY = "Mbed TLS"
DESCRIPTION = "C library for X.509 certificate manipulation and the SSL/TLS and DTLS"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=302d50a6369f5f22efdb674db908167a"

SRC_URI = "git://gitee.com/openharmony/third_party_mbedtls.git;protocol=https"

PV = "1.0+git${SRCPV}"
SRCREV = "43547c1c8a8ab35694acefb7c8b26323dd71eafa"

S = "${WORKDIR}/git"

DEPENDS = "zlib"

inherit cmake perlnative python3native
