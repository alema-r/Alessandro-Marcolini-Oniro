FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI_append = "file://80-wireless.network"

do_install_append() {
    install -D -m0644 ${WORKDIR}/80-wireless.network ${D}${systemd_unitdir}/network/
}
