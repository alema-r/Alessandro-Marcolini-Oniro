FILESEXTRAPATHS_prepend := "${THISDIR}/ot-br-posix:"

SRC_URI_append = " \
                  file://60-otbr-ip-forward.conf \
                  file://otbr-configuration \
                  file://otbr-configuration.service \
                 "

do_install_append() {
    install -d ${D}${sysconfdir}/sysctl.d/
    install -m 0644 ${WORKDIR}/60-otbr-ip-forward.conf ${D}${sysconfdir}/sysctl.d/
    install -D ${WORKDIR}/otbr-configuration ${D}${sbindir}/
    install -D -m 0644 ${WORKDIR}/otbr-configuration.service ${D}${systemd_unitdir}/system/
    install -m 0755 ${WORKDIR}/build/tools/pskc ${D}${sbindir}/
}

SYSTEMD_SERVICE_${PN} += " otbr-configuration.service"
