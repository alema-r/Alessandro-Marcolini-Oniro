# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

PACKAGES_append = "\
    packagegroup-thread-br \
    packagegroup-thread-client \
    "

RDEPENDS_packagegroup-thread-br = "\
    ${@bb.utils.contains("DISTRO_FEATURES", "thread-border-router", "ot-br-posix", "", d)} \
    ${@bb.utils.contains("DISTRO_FEATURES", "thread-border-router", "wpantund", "", d)} \
    hostapd \
    iptables \
    tayga \
    "

RDEPENDS_packagegroup-thread-client = "\
    ${@bb.utils.contains("DISTRO_FEATURES", "thread-client", "ot-daemon", "", d)} \
    "
