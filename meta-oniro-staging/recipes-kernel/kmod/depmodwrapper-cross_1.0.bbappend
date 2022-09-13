# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: MIT

do_install() {
	install -d ${D}${bindir_crossscripts}/

	# Most variables are escaped in the final script (as they are runtime
	# arguments or commands). Two exceptions here:
	# - PKGDATA_DIR: fixed via SSTATE_SCAN_FILES staging process
	# - nonarch_base_libdir: from do_install environment
	cat > ${D}${bindir_crossscripts}/depmodwrapper << EOF
#!/bin/sh
# Expected to be called as: depmodwrapper -a KERNEL_VERSION
if [ "\$1" != "-a" -o "\$2" != "-b" ]; then
    echo "Usage: depmodwrapper -a -b rootfs KERNEL_VERSION [KERNEL_PACKAGE_NAME]" >&2
    exit 1
fi

kernelpkgname="kernel"
# If no KERNEL_PACKAGE_NAME, assume "kernel".
[ -z "\$5" ] || kernelpkgname="\$5"

kernelabi=""
if [ -r "${PKGDATA_DIR}/\${kernelpkgname}-depmod/\${kernelpkgname}-abiversion" ]; then
    kernelabi=\$(cat "${PKGDATA_DIR}/\${kernelpkgname}-depmod/\${kernelpkgname}-abiversion")
fi

if [ ! -e "\$3${nonarch_base_libdir}/depmod.d/exclude.conf" ]; then
    mkdir -p "\$3${nonarch_base_libdir}/depmod.d"
    echo "exclude .debug" > "\$3${nonarch_base_libdir}/depmod.d/exclude.conf"
fi

if [ ! -r ${PKGDATA_DIR}/\${kernelpkgname}-depmod/System.map-\$4 ] || [ "\$kernelabi" != "\$4" ]; then
    echo "Unable to read: ${PKGDATA_DIR}/\${kernelpkgname}-depmod/System.map-\$4" >&2
    exec env depmod -C "\$3${nonarch_base_libdir}/depmod.d" "\$1" "\$2" "\$3" "\$4"
else
    exec env depmod -C "\$3${nonarch_base_libdir}/depmod.d" "\$1" "\$2" "\$3" -F "${PKGDATA_DIR}/\${kernelpkgname}-depmod/System.map-\$4" "\$4"
fi
EOF
	chmod +x ${D}${bindir_crossscripts}/depmodwrapper
}
