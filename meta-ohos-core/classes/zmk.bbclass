# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

inherit autotools

DEPENDS += "zmk-native"
EXTRA_OEMAKE += "-I${RECIPE_SYSROOT_NATIVE}/usr/include" 

do_configure_prepend() {
    # Create configure-like script for the autotools bbclass.
    oe_runmake -C ${S} configure
}
