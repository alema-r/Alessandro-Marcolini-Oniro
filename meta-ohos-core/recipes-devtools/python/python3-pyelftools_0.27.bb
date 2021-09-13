# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0
DESCRIPTION = "pyelftools is a pure-Python library for parsing and analyzing ELF files and DWARF debugging information"
HOMEPAGE = "https://github.com/eliben/pyelftools"
SECTION = "devel/python"
LICENSE = "PD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=5ce2a2b07fca326bc7c146d10105ccfc"

# Add DWARF-5 support, based on upstream
SRC_URI_append = " \
	file://dwarf5-constants.patch \
	file://dwarf5.patch \
"

SRC_URI[md5sum] = "061d67c669a9b1f8d07f28c47fb6a65f"
SRC_URI[sha256sum] = "cde854e662774c5457d688ca41615f6594187ba7067af101232df889a6b7a66b"

PYPI_PACKAGE = "pyelftools"

inherit pypi setuptools3

BBCLASSEXTEND = "native"
