FILESEXTRAPATHS_prepend := "${THISDIR}/ot-br-posix:"

SRC_URI_append = "file://60-otbr-ip-forward.conf"

do_install_append() {
    install -d ${D}${sysconfdir}/sysctl.d/
    install -m 0644 ${WORKDIR}/60-otbr-ip-forward.conf ${D}${sysconfdir}/sysctl.d/
}
