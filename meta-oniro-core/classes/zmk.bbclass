# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: MIT

inherit autotools

WARN_QA:remove = "host-user-contaminated"

DEPENDS += "zmk-native"
EXTRA_OEMAKE += "-I${RECIPE_SYSROOT_NATIVE}/usr/include" 

do_configure:prepend() {
    # Create configure-like script for the autotools bbclass.
    oe_runmake -C ${S} configure
}
