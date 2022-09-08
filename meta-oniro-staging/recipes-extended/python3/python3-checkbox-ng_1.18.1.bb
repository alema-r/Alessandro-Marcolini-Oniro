# SPDX-FileCopyrightText: Le Van Quach <levan.quach@kalpa.it>
#
# SPDX-License-Identifier: MIT

SUMMARY = "Checkbox-ng is a hardware testing tool useful for certifying laptops, desktops, servers and IOT devices with Ubuntu."
HOMEPAGE = "https://launchpad.net/checkbox-project"

LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

SRC_URI[sha256sum] = "6be1033802167ab2f7dfb21a47e76e326152dd8441ba8d6f6d0765f06bbc67e5"

SRC_URI += "file://0001-Adjust-for-deprecated-jinja2.Markup-alias.patch \
           file://0002-Adjust-for-deprecated-jinja2.environmentfilter-alias.patch \
           file://0003-Adjust-for-jinja2.escape-alias.patch \
           file://checkbox-ng.service \
           file://0001-Modify-restart-logic-in-order-to-run-checkbox-servic.patch \
           file://0001-Adjust-autoescape-for-Jinja2.patch \
           "

inherit pypi setuptools3

RDEPENDS:${PN} += " \
	${PYTHON_PN}-core \
	${PYTHON_PN}-jinja2 \
	${PYTHON_PN}-pkg-resources \
	${PYTHON_PN}-psutil \
	${PYTHON_PN}-requests \
	${PYTHON_PN}-tqdm \
	${PYTHON_PN}-urwid \
	${PYTHON_PN}-xlsxwriter \
"

# The checkbox-ng-service is a remotely accessible network service used by
# checkbox in some testing scenarios.

inherit systemd

PACKAGES =+ "${PN}-service"
SYSTEMD_PACKAGES = "${PN}-service"
SYSTEMD_SERVICE:${PN}-service = "checkbox-ng.service"

do_install:append() {
    install -D -m 0644 ${WORKDIR}/checkbox-ng.service -t ${D}${systemd_unitdir}/system/
}

RDEPENDS:${PN}-service += " \
    ${PN} \
"
