# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

SUMMARY = "OHOS Sysparam library"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=98c2e72b17fae6c40fb14fd5e43b29ec"
DEPENDS += "\
            syspara-lite-headers \
            syspara-lite-hals-headers \
            utils-native-lite \
            libsec \
           "
PV = "1.0+git${SRCPV}"
PVSHORT = '${@d.getVar("PV", False).split("+")[0]}'
PVMAJOR = '${@d.getVar("PV", False).split(".")[0]}'

SRCREV = "6e8a53e2682b69776ff29fa13aefcd8eb4d7e757"
SRC_URI = "git://gitee.com/openharmony/startup_frameworks_syspara_lite.git;protocol=https \
           file://0001-Temporarily-adding-sysparam-HAL-definitions.patch \
          "

S = "${WORKDIR}/git"

# Currently building only libsysparam library; in the same repository
# there is also libtoken_shared source code - when needed this recipe
# will have to be modified

CFLAGS += "-I${S}/parameter/src -fPIC -shared"

# FIXME: following flags should be taken from some sensible place
#        if necessary or removed from the source code if not used;
#        spaces have to be escaped with a backslash
CFLAGS += "\
           -DINCREMENTAL_VERSION=\"OpenHarmony\ 1.0\" \
           -DBUILD_TYPE=\"\" \
           -DBUILD_USER=\"\" \
           -DBUILD_TIME=\"${@int(time.time())}\" \
           -DBUILD_HOST=\"${BUILD_SYS}\" \
           -DBUILD_ROOTHASH=\"\" \
          "

do_compile () {
    # currently building without -lhal_sysparam as it is yet to be decided
    # how "System parameters" should be provided; for the time being sysparam HAL
    # definitions stubs added to parameter_common.c via patch as those are
    # hardcoded for each target platform in OHOS source code
    ${CC} ${CFLAGS} ${LDFLAGS} -Wl,-soname,libsysparam.so.${PVMAJOR} \
    ${S}/parameter/src/param_impl_posix/*.c \
    ${S}/parameter/src/*.c -o ${B}/libsysparam.so.${PVSHORT} -lsec
}

do_install () {
    install -d ${D}${libdir}
    oe_soinstall ${B}/libsysparam.so.${PVSHORT} ${D}${libdir}
}
