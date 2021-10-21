LINUX_VERSION ?= "5.10.63"
LINUX_RPI_BRANCH ?= "rpi-5.10.y"
LINUX_RPI_KMETA_BRANCH ?= "yocto-5.10"

SRCREV_machine = "4117cba235d24a7c4630dc38cb55cc80a04f5cf3"
SRCREV_meta = "e0147386e9f3c4cabc6f6d256d457fd4e67eb221"

require linux-raspberrypi_5.10.inc

SRC_URI += "\
    file://powersave.cfg \
    file://android-drivers.cfg \
    file://rauc.cfg \
    "
