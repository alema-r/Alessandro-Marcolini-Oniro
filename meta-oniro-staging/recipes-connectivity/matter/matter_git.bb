SUMMARY = "Matter"
DESCRIPTION = "Matter (formerly Project CHIP) is creating more connections \
               between more objects, simplifying development for manufacturers \
               and increasing compatibility for consumers, guided by the \
               Connectivity Standards Alliance (formerly Zigbee Alliance)."

HOMEPAGE = "https://github.com/project-chip/connectedhomeip"

LICENSE = "Apache-2.0 & MIT & BSD-3-Clause & BSD-1-Clause"

LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327 \
                    file://third_party/pigweed/repo/LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57 \
                    file://third_party/nlfaultinjection/repo/LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57 \
                    file://third_party/nlfaultinjection/repo/third_party/nlbuild-autotools/repo/LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57 \
                    file://third_party/nlfaultinjection/repo/third_party/cstyle/repo/LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57 \
                    file://third_party/nlunit-test/repo/LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57 \
                    file://third_party/nlunit-test/repo/third_party/nlbuild-autotools/repo/LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57 \
                    file://third_party/jsoncpp/repo/LICENSE;md5=5d73c165a0f9e86a1342f32d19ec5926\
                    file://third_party/nlassert/repo/LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57 \
                    file://third_party/nlassert/repo/third_party/nlbuild-autotools/repo/LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57 \
                    file://third_party/nlio/repo/LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57 \
                    file://third_party/nlio/repo/third_party/nlbuild-autotools/repo/LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57 \
                    file://third_party/lwip/repo/lwip/COPYING;md5=59a383b05013356e0c9899b06dc5da3f \
                    file://third_party/jlink/segger_rtt/License.txt;md5=24baa8b9507d8bdedb09af1bd52ab12e \
                    file://third_party/inipp/repo/inipp/LICENSE.txt;md5=2958b7575a79b95a26f7e4b4b86830b6 \
"

inherit gn pkgconfig

DEPENDS += "avahi glib-2.0-native glib-2.0"

# Matter has over 45 submodules listed, many of them for embedded bare metal
# SDK's we do  not use for our Linux build. Instead using the gitsm fetcher and
# fetch them recursively, we hand update them here.
SRC_URI = "git://github.com/project-chip/connectedhomeip.git;protocol=https;name=matter;branch=master \
           git://github.com/google/pigweed.git;protocol=https;destsuffix=git/third_party/pigweed/repo;name=pigweed;branch=main \
           git://github.com/open-source-parsers/jsoncpp.git;protocol=https;destsuffix=git/third_party/jsoncpp/repo;name=jsoncpp;branch=master \
           git://github.com/nestlabs/nlfaultinjection.git;protocol=https;destsuffix=git/third_party/nlfaultinjection/repo;name=nlfaultinjection;branch=master \
           git://github.com/nestlabs/nlunit-test.git;protocol=https;destsuffix=git/third_party/nlunit-test/repo;name=nlunit-test;branch=master \
           git://github.com/nestlabs/nlassert.git;protocol=https;destsuffix=git/third_party/nlassert/repo;name=nlassert;branch=master \
           git://github.com/nestlabs/nlio.git;protocol=https;destsuffix=git/third_party/nlio/repo;name=nlio;branch=master \
           file://0001-projectmatter-use-Yocto-toolchain-and-flags.patch \
           file://0002-mbedtls-disable-building-integrated-library-in-Yocto.patch \
           file://0001-BUILD.gn-enbale-all-Linux-examples-in-the-default-bu.patch \
           "

PV = "0.0+git${SRCPV}"
SRCREV_matter = "65440ab4d97ea1dfa5762a3a3a2558f716eb0ef5"
SRCREV_pigweed = "c4dac15049d9742f0263f09ae9ec452fc57dfeb6"
SRCREV_jsoncpp = "42e892d96e47b1f6e29844cc705e148ec4856448"
SRCREV_nlfaultinjection = "e0de0ab4f52c1d1cc7f3948557a1abd0fceeb5ef"
SRCREV_nlunit-test = "0c8c9073af9c07aa089861295b7d7ced56ad174d"
SRCREV_nlassert = "c5892c5ae43830f939ed660ff8ac5f1b91d336d3"
SRCREV_nlio = "0e725502c2b17bb0a0c22ddd4bcaee9090c8fb5c"
SRCREV_FORMAT = "matter"

S = "${WORKDIR}/git"

# GN does not pick up the python3 wheel native modules and fails with wheel import
GN_ARGS += "chip_enable_python_modules=false"

# GCC v11 reports multiple problems that span across project's source code and
# submodules. Temporarily disabling those warnings for the whole project
TARGET_CFLAGS:append = " -Wno-format-truncation -Wno-stringop-truncation -Wno-format-security -Wno-unused-result"

do_install() {
    install -d ${D}${bindir}
    install ${B}/address-resolve-tool ${D}${bindir}
    install ${B}/chip-cert ${D}${bindir}
    install ${B}/chip-echo-requester ${D}${bindir}
    install ${B}/chip-echo-responder ${D}${bindir}
    install ${B}/chip-im-initiator ${D}${bindir}
    install ${B}/chip-im-responder ${D}${bindir}
    install ${B}/chip-shell ${D}${bindir}
    install ${B}/chip-tool ${D}${bindir}
    install ${B}/spake2p ${D}${bindir}
    install ${B}/chip-all-clusters-app ${D}${bindir}
    install ${B}/chip-bridge-app ${D}${bindir}
    install ${B}/chip-door-lock-app ${D}${bindir}
    install ${B}/chip-lighting-app ${D}${bindir}
    install ${B}/chip-tv-app ${D}${bindir}
    install ${B}/chip-tv-casting-app ${D}${bindir}
    install ${B}/thermostat-app ${D}${bindir}
}
