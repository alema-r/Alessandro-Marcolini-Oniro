SUMMARY = "Collection of reusable makefiles"
LICENSE = "LGPLv3"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e6a600fd5e1d9cbde2d983680233ad02"

SRC_URI = "https://github.com/zyga/zmk/releases/download/v${PV}/zmk-${PV}.tar.gz"
SRC_URI[md5sum] = "5ed6b855f70e85972094bab078944fdd"
SRC_URI[sha256sum] = "b006f312aba2c6d7bc6d11e08709da7ce4b7dac71612a4a66491797158f1c30b"

DEPENDS += "make-native gawk-native"

inherit autotools native
