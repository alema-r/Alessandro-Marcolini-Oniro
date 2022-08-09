# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

require conf/machine/include/qemu.inc

PREFERRED_PROVIDER_virtual/kernel ?= "linux-yocto"
KCONFIG_MODE = "--alldefconfig"
MACHINE_EXTRA_RRECOMMENDS += "kernel-modules linux-firmware"

SERIAL_CONSOLES ?= "115200;ttyAMA0 115200;hvc0"
SERIAL_CONSOLES_CHECK = "${SERIAL_CONSOLES}"

# This provides the bios for runqemu
EXTRA_IMAGEDEPENDS += "edk2-firmware"

IMAGE_FSTYPES += "wic wic.gz"
WKS_FILE ?= "x-gpt-efi-disk.wks.in"

# For runqemu
QB_MACHINE_VIRT_HIGHMEM ?= "on"
QB_MACHINE = "-machine virt,highmem=${QB_MACHINE_VIRT_HIGHMEM}"
QB_SMP = "-smp 4"
# For graphics to work we need to define the VGA device as well as the necessary USB devices
QB_GRAPHICS = "-device virtio-gpu-pci"
QB_OPT_APPEND = "-device qemu-xhci -device usb-tablet -device usb-kbd"
# Virtio Networking support
QB_TAP_OPT = "-netdev tap,id=net0,ifname=@TAP@,script=no,downscript=no"
QB_NETWORK_DEVICE = "-device virtio-net-pci,netdev=net0,mac=@MAC@"
# Virtio block device
QB_ROOTFS_OPT = "-drive id=disk0,file=@ROOTFS@,if=none,format=raw"
# Virtio serial console
QB_SERIAL_OPT = "-device virtio-serial-pci -chardev null,id=virtcon -device virtconsole,chardev=virtcon"
QB_TCPSERIAL_OPT = "-device virtio-serial-pci -chardev socket,id=virtcon,port=@PORT@,host=127.0.0.1 -device virtconsole,chardev=virtcon"

# Boot the wic image via EFI
QB_DEFAULT_FSTYPE = "wic"
QB_DEFAULT_KERNEL = "none"
QB_DEFAULT_BIOS = "uefi.bin"
QB_ROOTFS_EXTRA_OPT += "bootindex=0"
EFI_PROVIDER = "grub-efi"
MACHINE_FEATURES += "efi"
