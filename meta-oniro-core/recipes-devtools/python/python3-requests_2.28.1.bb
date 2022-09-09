# SPDX-FileCopyrightText: Le Van Quach <levan.quach@kalpa.it>
#
# SPDX-License-Identifier: MIT

DESCRIPTION = "Python HTTP for Humans."
HOMEPAGE = "https://github.com/psf/requests"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=34400b68072d710fecd0a2940a0d1658"

SRC_URI[sha256sum] = "7c5599b102feddaa661c826c56ab4fee28bfd17f5abca1ebbe3e7f19d7c97983"

inherit pypi setuptools3

RDEPENDS:${PN} += " \
    ${PYTHON_PN}-email \
    ${PYTHON_PN}-json \
    ${PYTHON_PN}-ndg-httpsclient \
    ${PYTHON_PN}-netserver \
    ${PYTHON_PN}-pyasn1 \
    ${PYTHON_PN}-pyopenssl \
    ${PYTHON_PN}-pysocks \
    ${PYTHON_PN}-urllib3 \
    ${PYTHON_PN}-chardet \
    ${PYTHON_PN}-charset-normalizer \
    ${PYTHON_PN}-certifi \
    ${PYTHON_PN}-idna \
"

CVE_PRODUCT = "cpe:2.3:a:python:requests:*:*:*:*:*:*:*:*"

BBCLASSEXTEND = "native nativesdk"
