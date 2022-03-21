FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
    file://0001-Do-not-include-glibc-specific-bits-types-struct_tm.h.patch \
    file://0001-hawkbit-client-do-not-pass-NULL-format-to-g_strdup_v.patch"
