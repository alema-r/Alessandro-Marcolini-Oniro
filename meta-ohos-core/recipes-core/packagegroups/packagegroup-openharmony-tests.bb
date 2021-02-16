DESCRIPTION = "OpenHarmony tests package group"

inherit packagegroup

PACKAGES = "\
	packagegroup-openharmony-tests \
	"

RDEPENDS_packagegroup-openharmony-tests = "\
	ohos-xts-acts \
	"
