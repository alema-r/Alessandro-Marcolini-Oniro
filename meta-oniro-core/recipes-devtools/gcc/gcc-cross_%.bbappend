# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: MIT

do_configure:prepend () {
    if ! echo ${@d.getVar("TARGET_OS")} | grep -qi linux; then
        # Building Zephyr with DWARF-5 is problematic because
        # its kernel tests use pyelftools, which has incomplete
        # DWARF-5 support (even after applying preliminary
        # support patches). Use DWARF-4 on Zephyr and FreeRTOS
        # for the time being.
        sed -i -e 's,Var(dwarf_version) Init(5),Var(dwarf_version) Init(4),' ${S}/gcc/common.opt
    fi
}
