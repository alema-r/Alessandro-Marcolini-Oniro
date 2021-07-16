
do_install_append () {
    sed -i 's/umask.*/umask 027/g' ${D}/${sysconfdir}/profile
}
