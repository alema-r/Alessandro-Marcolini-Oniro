# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

SUMMARY = "A C implementation of IETF Constrained Application Protocol (RFC 7252)"
DESCRIPTION = "libcoap is a C implementation of a lightweight application-protocol for devices that are constrained their resources such as computing power, RF range, memory, bandwidth, or network packet sizes. This protocol, CoAP, is standardized by the IETF as RFC 7252. For further information related to CoAP, see http://coap.technology."

HOMEPAGE = "http://libcoap.net/"
SECTION = "libs/network"

PROVIDES = "coap libcoap libcoap.so"

# WARNING: Code until 2017-06-01 is dual-licensed as GPLv2 and BSD 2-Clause, it is BSD after that
LICENSE = "GPLv2 | BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=faed8f005d476edd3f250599a4bb9a75 \
                    file://LICENSE;md5=649d94043490c8be38f08b781838cddb \
                    file://ext/tinydtls/LICENSE;md5=ffb073dbb36e7ec5e091047332f302c5"

LIBCOAP_VERSION = "4.3.0-rc3"
SRC_URI = "gitsm://github.com/obgm/libcoap.git;protocol=https;branch=develop"
# When using a final release:
#SRC_URI = "gitsm://github.com/obgm/libcoap.git;protocol=https;branch=release-${LIBCOAP_VERSION}"
PV = "${LIBCOAP_VERSION}+git${SRCPV}"
SRCREV = "39b75f8240b45da3ab7923b0545001e08da437bf"

S = "${WORKDIR}/git"

# FIXME: make it depend on something akin to virtual/tls so we can support openssl, gnutls as well
DEPENDS = "mbedtls"

inherit pkgconfig autotools

# Specify any options you want to pass to the configure script using EXTRA_OECONF:
EXTRA_OECONF = "--disable-doxygen --disable-manpages --with-mbedtls"

BBCLASSEXTEND = "native nativesdk"

