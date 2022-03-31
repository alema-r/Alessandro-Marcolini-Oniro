# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

COMPATIBLE_HOST:libc-musl = "(i.86|x86_64|arm|aarch64).*-linux"

RDEPENDS:${PN} += "perl"

# bpf depends on clang/llvm. It is removed on x86 and arm by default for backward compatibility.
# Reference: http://cgit.openembedded.org/meta-openembedded/tree/meta-oe/recipes-kernel/kernel-selftest/kernel-selftest.bb?h=master#n22
# clang/llvm is not needed until we want to enable bpf.
DEPENDS:remove = "clang-native llvm-native"

TEST_LIST = "\
    rtc \
    cpufreq \
    cpu-hotplug \
"

do_install:append() {
    cp ${S}/tools/testing/selftests/run_kselftest.sh ${D}/usr/kernel-selftest
    cp -R ${S}/tools/testing/selftests/kselftest ${D}/usr/kernel-selftest
}
