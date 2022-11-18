FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"
SRC_URI += " file://pam.d/common-password \
"

RDEPENDS:${PN} += "pam-passwdqc"
#RDEPENDS:${PN}-runtime += "${MLPREFIX}pam-plugin-cracklib-${libpam_suffix} \
#"
