# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: MIT

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://run-ptest"

inherit ptest

do_install_ptest () {
	cp ${S}/src/import/Makefile ${D}${PTEST_PATH}
	install -d ${D}${PTEST_PATH}/test
	cp -r ${S}/src/import/test/system ${D}${PTEST_PATH}/test

	# Some compatibility links for the Makefile assumptions.
	install -d ${D}${PTEST_PATH}/bin
	ln -s ${bindir}/podman ${D}${PTEST_PATH}/bin/podman
	ln -s ${bindir}/podman-remote ${D}${PTEST_PATH}/bin/podman-remote
}

RDEPENDS:${PN}-ptest += " \
	bash \
	bats \
	buildah \
	catatonit \
	coreutils \
	file \
	gnupg \
	jq \
	make \
	tar \
"
