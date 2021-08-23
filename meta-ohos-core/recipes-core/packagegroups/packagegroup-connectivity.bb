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

RDEPENDS_packagegroup-net-essentials = "\
	dhcp-client \
	dropbear \
	${@bb.utils.contains("COMBINED_FEATURES", "wifi", "packagegroup-base-wifi iw", "", d)} \
	networkmanager \
	"

RDEPENDS_packagegroup-net-tools = "\
	iperf3 \
	"

RDEPENDS_packagegroup-ble-essentials = "\
	${@bb.utils.contains("COMBINED_FEATURES", "bluetooth", "bluez5 obexftp", "", d)} \
	"
