# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: MIT

FILESEXTRAPATHS:prepend := "${ONIRO_COREBASE}/../meta-raspberrypi/recipes-kernel/linux/files:"

require linux-raspberrypi-v7.inc
require recipes-kernel/linux/linux-raspberrypi_5.15.bb
