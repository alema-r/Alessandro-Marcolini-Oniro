require recipes-core/images/openharmony-image-extra.bb

SUMMARY = "OpenHarmony Wayland image including the base OS \
software stack and tests"

IMAGE_INSTALL_append = "\
    packagegroup-openharmony-tests \
"
