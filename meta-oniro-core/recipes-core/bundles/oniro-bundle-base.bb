# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

inherit bundle

SUMMARY = "SysOTA/RAUC Update Bundle for Oniro Base Image"

RAUC_BUNDLE_COMPATIBLE:raspberrypi4-64 = "Raspberry Pi 4"
# TODO: add compatibility strings for other machines.

RAUC_BUNDLE_DESCRIPTION = "SysOTA/RAUC Update Bundle"

RAUC_BUNDLE_SLOTS = "system"
RAUC_BUNDLE_FORMAT = "verity"

RAUC_SLOT_system = "oniro-image-base"
RAUC_SLOT_system[type] = "image"
RAUC_SLOT_system[fstype] = "squashfs"

# There are two squashfs filesystems used, one to contain the bundle and one to
# contain the system image inside the bundle. The outer squashfs should not use
# any strong compression as it is effectively pointless. Since there is no
# option to avoid compression completely, use the weakest/fastest compression
# available.
BUNDLE_ARGS += ' --mksquashfs-args="-comp zstd -Xcompression-level 1" '

# Those need to be synchronized with the keys used by RAUC.
RAUC_KEY_FILE ?= "${THISDIR}/../rauc/insecure-keys/key.pem"
RAUC_CERT_FILE ?= "${THISDIR}/../rauc/insecure-keys/cert.pem"
