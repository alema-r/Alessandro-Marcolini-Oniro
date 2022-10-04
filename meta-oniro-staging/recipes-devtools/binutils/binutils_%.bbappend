# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: MIT

do_install:append:class-target () {
    chmod o-rx ${D}${prefix}/${TARGET_SYS}/bin/*
}
