do_install:append () {
	# to hardend
	sed -i -e 's:UMASK.*:UMASK 027:' ${D}${sysconfdir}/login.defs
# TODO: set the password age limit and min length
	sed -i -e 's:LOGIN_RETRIES.*:LOGIN_RETRIES 3:' ${D}${sysconfdir}/login.defs
	sed -i -e 's:LOGIN_TIMEOUT.*:LOGIN_TIMEOUT 30:' ${D}${sysconfdir}/login.defs
}
