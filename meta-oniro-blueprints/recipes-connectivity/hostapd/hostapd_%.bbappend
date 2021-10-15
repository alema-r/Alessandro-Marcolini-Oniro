FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI_append = "file://hostapd.conf \
                  file://hostapd.service \
                 "

do_install_append() {
    install -m 0644 ${WORKDIR}/hostapd.conf ${D}${sysconfdir}
}
