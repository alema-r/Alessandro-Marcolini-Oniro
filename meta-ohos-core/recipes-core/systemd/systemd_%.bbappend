# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

# We support musl integration and the current status is stable for the scope of
# the project.
deltask warn_musl

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
SRC_URI += "file://systemd-244-musl-1.2.2.patch"
