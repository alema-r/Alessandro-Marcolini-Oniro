# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

SUMMARY = "OpenThread Border Router"
SECTION = "net"
LICENSE = "BSD-3-Clause & MIT & Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=87109e44b2fda96a8991f27684a7349c \
                    file://third_party/Simple-web-server/repo/LICENSE;md5=852b3f7f320b19f6431487b8b2fb1d74 \
                    file://third_party/angular-material/repo/LICENSE;md5=3d0c299b7dd4267c3ef21a705cf6a5c6 \
                    file://third_party/angular/repo/LICENSE.md;md5=0d83982330e37f011a2cef9b15cb43aa \
                    file://third_party/cJSON/repo/LICENSE;md5=218947f77e8cb8e2fa02918dc41c50d0 \
                    file://third_party/d3js/repo/LICENSE;md5=e863f3b38093ec361d12ed4ff001c403 \
                    file://third_party/http-parser/repo/LICENSE-MIT;md5=9bfa835d048c194ab30487af8d7b3778 \
                    file://third_party/mdl/repo/LICENSE;md5=6f740e801ce5a08c6c113bf72859bff2 \
                    file://third_party/openthread/repo/LICENSE;md5=543b6fe90ec5901a683320a36390c65f \
                    "

SRC_URI = "gitsm://github.com/openthread/ot-br-posix.git;protocol=https;branch=main \
           file://0001-build-drop-Werror-to-avoid-compilation-breaks.patch \
           file://0001-web-service-ot-client-add-needed-header-for-fd_set-c.patch \
           "

PV = "0.2+git${SRCPV}"
SRCREV = "5de1086daa12fda3d6d2b8b7d0f8954b709d5f66"

S = "${WORKDIR}/git"

DEPENDS = "autoconf-archive dbus readline avahi jsoncpp boost"

inherit cmake systemd writables

WRITABLES = "data"
WRITABLE_PATH[data] = "/var/lib/thread/"

SYSTEMD_SERVICE_${PN} = "otbr-agent.service otbr-web.service"

EXTRA_OECMAKE = "-DBUILD_TESTING=OFF -DOTBR_DBUS=ON -DOTBR_REST=ON -DOTBR_WEB=ON -DCMAKE_LIBRARY_PATH=${libdir}"

FILES_${PN} += "${systemd_unitdir}/*"
FILES_${PN} += "${datadir}/*"

RCONFLICTS_${PN} = "ot-daemon"
