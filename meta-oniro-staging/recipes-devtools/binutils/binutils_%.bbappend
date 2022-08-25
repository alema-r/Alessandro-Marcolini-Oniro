# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

do_install:append:class-target () {
    chmod o-rx ${D}${prefix}/${TARGET_SYS}/bin/*
}
