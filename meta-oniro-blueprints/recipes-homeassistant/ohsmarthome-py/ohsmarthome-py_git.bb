# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

SUMMARY = "Oniro Project SmartHome device interface library"
HOMEPAGE = "https://booting.oniroproject.org/distro/components/ohsmarthome-py"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSES/Apache-2.0.txt;md5=c846ebb396f8b174b10ded4771514fcc"

SRC_OPT_CLONE_DIR = "git/components/ohsmarthome-py"
SRC_OPT_PROTO = "protocol=https"
SRC_OPT_DEST = "destsuffix=${SRC_OPT_CLONE_DIR}"
SRC_OPT_NAME = "name=ohsmarthome-py"
SRC_OPT_BRANCH = "branch=master"

SRC_OPTIONS = "${SRC_OPT_PROTO};${SRC_OPT_DEST};${SRC_OPT_NAME};${SRC_OPT_BRANCH}"
SRC_URI = "git://booting.oniroproject.org/distro/components/ohsmarthome-py.git;${SRC_OPTIONS}"
SRCREV_ohsmarthome-py = "3ea56efa56937939565c127810de55a05c9e4703"

S = "${WORKDIR}/${SRC_OPT_CLONE_DIR}"

inherit setuptools3

RDEPENDS_${PN} += "\
    python3-bluepy \
    "
