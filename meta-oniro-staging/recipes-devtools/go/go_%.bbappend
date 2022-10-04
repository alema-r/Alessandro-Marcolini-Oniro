# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: MIT

do_configure:prepend () {
    # FIXME quick and dirty workaround for
    # https://github.com/golang/go/issues/51790
    # until we've fixed this properly

    if [ "${@d.getVar("TARGET_LIBC")}" = "musl" ]; then
        sed -i -e 's,ld-linux-x86-64.so.2,ld-musl-x86_64.so.1,' ${S}/src/cmd/link/internal/amd64/obj.go
        if echo ${@d.getVar("TARGET_FPU")} | grep -q soft; then
            sed -i -e 's,ld-linux.so.3,ld-musl-arm.so.1,' ${S}/src/cmd/link/internal/arm/obj.go
        else
            sed -i -e 's,ld-linux.so.3,ld-musl-armhf.so.1,' ${S}/src/cmd/link/internal/arm/obj.go
        fi
        sed -i -e 's,ld-linux-aarch64.so.1,ld-musl-aarch64.so.1,' ${S}/src/cmd/link/internal/arm64/obj.go
        sed -i -e 's,ld.so.1,ld-musl-mips.so.1,' ${S}/src/cmd/link/internal/mips/obj.go
        sed -i -e 's,ld64.so.1,ld-musl-mips64.so.1,' ${S}/src/cmd/link/internal/mips64/obj.go
        sed -i -e 's,ld64.so.1,ld-musl-powerpc64.so.1,' ${S}/src/cmd/link/internal/ppc64/obj.go
        sed -i -e 's,ld.so.1,ld-musl-riscv64.so.1,' ${S}/src/cmd/link/internal/riscv64/obj.go
        sed -i -e 's,ld64.so.1,ld-musl-s390x.so.1,' ${S}/src/cmd/link/internal/s390x/obj.go
        sed -i -e 's,ld-linux.so.2,ld-musl-i386.so.1,' ${S}/src/cmd/link/internal/x86/obj.go
    fi
}
