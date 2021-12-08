# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

FILESEXTRAPATHS:prepend := "${ONIRO_COREBASE}/assets:"
SPLASH_IMAGES:append = " file://psplash-oniro-img.png;outsuffix=oniro"
ALTERNATIVE_PRIORITY_psplash-oniro[psplash] = "200"

EXTRA_OECONF += "--enable-img-fullscreen --disable-startup-msg --disable-progress-bar"

# Change background color and splash image with no progress bar
FILESEXTRAPATHS:prepend := "${THISDIR}/psplash:"
SRC_URI += " \
    file://0001-psplash-colors.h-color-change.patch \
    "
