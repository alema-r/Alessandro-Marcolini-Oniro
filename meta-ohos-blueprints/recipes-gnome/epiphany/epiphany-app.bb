# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

SUMMARY = "Epiphany app mode support files"
BUGTRACKER = "https://gitlab.gnome.org/GNOME/epiphany"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${OHOS_COREBASE}/LICENSES/Apache-2.0.txt;md5=ef3dabb8f39493f4ea410bebc1d01755"

inherit allarch features_check

EPIPHANY_APP ?= "HomeAssistant"
EPIPHANY_URL ?= "http://localhost:8123"
EPIPHANY_RDEPENDS ?= "python3-homeassistant"
EPIPHANY_SERVICE_ENABLED ?= "0"

# The services depend on weston
REQUIRED_DISTRO_FEATURES = "wayland"

SRC_URI = "\
    file://epiphany-app@.service \
    file://${EPIPHANY_APP}/* \
    "

do_install() {
    # Setup the system service
    install -d "${D}${systemd_unitdir}/system/multi-user.target.wants"
    install -m 0644 "${WORKDIR}/epiphany-app@.service" "${D}${systemd_unitdir}/system"
    sed -i -e 's,@EPIPHANY_APP@,${EPIPHANY_APP},g' \
        -e 's,@EPIPHANY_URL@,${EPIPHANY_URL},g' "${D}${systemd_unitdir}/system/epiphany-app@.service"
    if [ "${EPIPHANY_SERVICE_ENABLED}" = "1" ]; then
        install -d "${D}${sysconfdir}/systemd/system/multi-user.target.wants"
        ln -s "${systemd_unitdir}/system/epiphany-app@.service" \
            "${D}${sysconfdir}/systemd/system/multi-user.target.wants/epiphany-app@${EPIPHANY_APP}.service"
    fi

    # The systemd service override is optional and app specific (for example it
    # can add dependencies for a local HTTP service).
    if [ -f "${WORKDIR}/${EPIPHANY_APP}/service-override.conf" ]; then
        mkdir -p "${D}${sysconfdir}/systemd/system/epiphany-app@${EPIPHANY_APP}.service.d"
        cp "${WORKDIR}/${EPIPHANY_APP}/service-override.conf" \
            "${D}${sysconfdir}/systemd/system/epiphany-app@${EPIPHANY_APP}.service.d"
    fi

    # Setup the app
    WD_APPDIR="${WORKDIR}/${EPIPHANY_APP}"
    D_APPDIR="${D}/home/root/.local/share/epiphany-${EPIPHANY_APP}"
    mkdir -p "$D_APPDIR"
    cp "$WD_APPDIR/epiphany-${EPIPHANY_APP}.desktop" "$D_APPDIR"
    touch "$D_APPDIR/.app"
}

FILES_${PN} += " \
    /home/root \
    ${systemd_unitdir} \
    " 

RDEPENDS_${PN} = "\
    ${EPIPHANY_RDEPENDS} \
    epiphany \
    weston-init \
    "
