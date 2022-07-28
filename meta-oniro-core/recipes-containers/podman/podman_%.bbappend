# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

inherit writables

WRITABLES = "rootuser-netconfig"
WRITABLE_PATH[rootuser-netconfig] = "/etc/cni/net.d"

do_install:append() {
	if ${@bb.utils.contains('PACKAGECONFIG', 'rootless', 'true', 'false', d)}; then
		# Make sure that the Oniro defaults gets overridden by this.
		mv "${D}${sysconfdir}/sysctl.d/00-podman-rootless.conf" \
			"${D}${sysconfdir}/sysctl.d/50-podman-rootless.conf"
	fi
}
