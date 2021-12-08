FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append = "file://profile.d/tmout.sh"

do_install:append () {
    sed -i 's/umask.*/umask 027/g' ${D}/${sysconfdir}/profile
    install -d ${WORKDIR}/profile.d/ ${D}${sysconfdir}/profile.d/
    install -m 0644 ${WORKDIR}/profile.d/tmout.sh ${D}${sysconfdir}/profile.d/tmout.sh
}
