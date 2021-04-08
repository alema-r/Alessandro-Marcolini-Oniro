FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI_append = "\
    file://0001-BaseTools-fix-ucs-2-lookup-on-python-3.9.patch \
    file://0002-BaseTools-Work-around-array.array.tostring-removal-i.patch \
    "
