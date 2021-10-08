# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

do_configure_prepend(){
	bbwarn "ISC BIND 9.11 or its libs is pulled in into the build. It is unsupported and possibly insecure!"
	bbwarn "If ISC BIND is pulled in by dhcp-client, please consider switching to use NetworkManager."
}
