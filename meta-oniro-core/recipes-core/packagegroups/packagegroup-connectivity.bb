# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

DESCRIPTION = "Networking and connectivity package group"

inherit packagegroup

PACKAGES = "\
	packagegroup-net-essentials \
	packagegroup-net-tools \
	packagegroup-ble-essentials \
	"

RDEPENDS:packagegroup-net-essentials = "\
	dropbear \
	${@bb.utils.contains("COMBINED_FEATURES", "wifi", "packagegroup-base-wifi iw", "", d)} \
	networkmanager \
	"

RDEPENDS:packagegroup-net-tools = "\
	iperf3 \
	"

RDEPENDS:packagegroup-ble-essentials = "\
	${@bb.utils.contains("COMBINED_FEATURES", "bluetooth", "bluez5 obexftp", "", d)} \
	"
