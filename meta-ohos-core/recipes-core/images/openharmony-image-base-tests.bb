require recipes-core/images/openharmony-image-base.bb

SUMMARY = "OpenHarmony image including the base OS software stack and tests"

IMAGE_INSTALL_append = "\
    packagegroup-openharmony-tests \
"
