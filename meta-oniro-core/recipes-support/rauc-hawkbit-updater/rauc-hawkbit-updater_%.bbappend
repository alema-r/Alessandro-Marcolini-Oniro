FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
    file://0001-Do-not-include-glibc-specific-bits-types-struct_tm.h.patch \
    file://0001-hawkbit-client-do-not-pass-NULL-format-to-g_strdup_v.patch"

# Stash the default configuration file as an example. The configuration
# directory must be an empty mount point for persistent state. This is
# simplistic, in a real-world-scenario we would need to have a provisioning
# stage that populates this configuration file.
#
# This is the first part of the adaptation to read-only images.
do_install:append() {
    install -d 755 ${D}/usr/share/${PN}
    mv ${D}${sysconfdir}/${PN}/config.conf /${D}/usr/share/${PN}/example.conf
}

inherit writables

# Persist RAUC hawkbit updater configuration directory.
WRITABLES = "rauc-hawkbit-updater-cfg"
WRITABLE_TYPE[rauc-hawkbit-updater-cfg] = "persistent"
WRITABLE_PATH[rauc-hawkbit-updater-cfg] = "/etc/rauc-hawkbit-updater"

# Do not start the updater service unless the configuration file is present.
#
# This the second part of the adaptation to read-only images.

FILESEXTRAPATHS:append:= "${THISDIR}/files:"

SRC_URI:append = " \
    file://is-provisioned.conf \
    "

FILES:${PN} += "\
    ${systemd_unitdir}/system/rauc-hawkbit-updater.service.d/*.conf \
    "

do_install:append() {
    install -D -m 644 ${WORKDIR}/is-provisioned.conf --target-directory=${D}${systemd_unitdir}/system/rauc-hawkbit-updater.service.d/
}
