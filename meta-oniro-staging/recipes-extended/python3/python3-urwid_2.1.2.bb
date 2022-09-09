# SPDX-FileCopyrightText: Le Van Quach <levan.quach@kalpa.it>
#
# SPDX-License-Identifier: MIT

SUMMARY = "Urwid is a console user interface library for Python."
HOMEPAGE = "http://urwid.org"

LICENSE = "LGPL-2.1-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=243b725d71bb5df4a1e5920b344b86ad"

SRC_URI[sha256sum] = "588bee9c1cb208d0906a9f73c613d2bd32c3ed3702012f51efe318a3f2127eae"

inherit pypi setuptools3

RDEPENDS:${PN} += " \
	${PYTHON_PN}-codecs \
	${PYTHON_PN}-core \
	${PYTHON_PN}-curses \
	${PYTHON_PN}-datetime \
	${PYTHON_PN}-io \
	${PYTHON_PN}-math \
	${PYTHON_PN}-shell \
"
