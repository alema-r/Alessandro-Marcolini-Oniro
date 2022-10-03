# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: MIT

# Add a new user named oniro with default password oniro
inherit extrausers

# This is a sha512 hash of the word "oniro"
# To generate a new password run the following command:
# $ mkpasswd -m sha512crypt
# WARNING: this is a temporary solution until we have a provisioning solution to set up the initial password
ONIRO_USER_PASSWORD ?= "\$6\$cI/pmFRW1S8seZ24\$e/7XAuVsOBgoAEmlKQnk54.jQEpRuQmmzik6.1Osaji7ca.04N70Ji.PN86sFXBvqwDGhhSr.jqZsDDA8OVuy."

EXTRA_USERS_PARAMS = "\
    useradd -p '${ONIRO_USER_PASSWORD}' oniro; \
"

IMAGE_INSTALL:append = " sudo"

# Add to secure_path:
#	- /bin
#	- /usr/bin
# 	- /usr/local/bin
#   - /sbin
#   - /usr/sbin
# 	- /usr/local/sbin
# Add oniro to the sudo users
init_user_oniro () {
	echo "oniro ALL=(ALL:ALL) ALL" > ${IMAGE_ROOTFS}/etc/sudoers.d/oniro
	echo "Defaults secure_path=/bin:/usr/bin:/usr/local/bin:/sbin:/usr/sbin:/usr/local/sbin" >> ${IMAGE_ROOTFS}/etc/sudoers.d/oniro
}

ROOTFS_POSTPROCESS_COMMAND:append = " init_user_oniro;" 
