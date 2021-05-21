# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

SUMMARY = "wpantund, Userspace WPAN Network Daemon"
SECTION = "net"
LICENSE = "Apache-2.0 & MIT & BSL-1.0 & BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e7820bc7f7d1638a6b54fc2e8d7fb103 \
                    file://third_party/assert-macros/LICENSE;md5=cbf35ecdc8161026afe4da2906fab204 \
                    file://third_party/boost/LICENSE;md5=e4224ccaecb14d942c71d31bef20d78c \
                    file://third_party/fgetln/LICENSE;md5=389e03d2254ecad45d0d9bbdefef7129 \
                    file://third_party/openthread/LICENSE;md5=543b6fe90ec5901a683320a36390c65f \
                    file://third_party/pt/LICENSE;md5=dcd598b69cad786beea33da7b1ae14b7 \
                    "

SRC_URI = "gitsm://github.com/openthread/wpantund.git;protocol=https \
           file://0001-util-netif-mgmt-only-include-missing-strlcpy-header-.patch \
           "

PV = "0.07.01+git${SRCPV}"
SRCREV = "820468161c87816a3e94366ebbe2a932650416e3"

S = "${WORKDIR}/git"

DEPENDS = "autoconf-archive dbus readline"

inherit pkgconfig perlnative autotools
