do_install_append() {
	# Install main.conf file and turn on AutoEnable flag
	# to bring up BLE controller on boot.
	if [ -f ${S}/src/main.conf ]; then
		install -m 0644 ${S}/src/main.conf ${D}/${sysconfdir}/bluetooth/
		sed -i 's/#AutoEnable=false/AutoEnable=true/g' \
			${D}/${sysconfdir}/bluetooth/main.conf
	fi
}
