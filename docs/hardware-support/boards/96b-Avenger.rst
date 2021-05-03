.. SPDX-FileCopyrightText: Huawei Inc.
..
.. SPDX-License-Identifier: CC-BY-4.0

.. _SupportedBoardAvenger96:

96Boards Avenger96
##################

.. contents::
   :depth: 3

Overview
********

Avenger96 is a STM32MP157xx (Cortex-A7 + Cortex-M4) development board designed
by the 96Boards initiative. Due to presence of the application processors and
the microcontroller, Avenger96 can simultaneously run Linux and Zephyr kernels.
The application processor is responsible for powering up and programming the
microcontroller with the appropriate image. Linux provides interfaces to
communicate with the program running on the microcontroller.

Hardware
********

* For detailed specification, see `Avenger96 product page on the 96Boards website <https://www.96boards.org/product/avenger96/>`_.
* For hardware user manual and schematics, see `96Boards GitHub documentation repository <https://github.com/96boards/documentation/blob/master/consumer/avenger96/hardware-docs/files/avenger96-hardware-user-manual.pdf>`_.

For more details on Avenger96 board, see `Avenger96 product page <https://www.96boards.org/product/avenger96/>`_.

Working with the board
**********************

Supported images
================

.. list-table:: Supported images
  :widths: auto
  :header-rows: 1

  * - Image  Name
    - Size
    - Description
  * - allscenarios-image-base
    - Approximate 100-200 MB
    - All Scenarios OS image including the base OS software stack
  * - allscenarios-image-extra
    - Approximate 100-200 MB
    - All Scenarios OS Wayland image including the base OS software stack


Building OHOS image
===================

To clone the source code, perform the procedure in: :ref:`Setting up a repo workspace <RepoWorkspace>`.

Linux image
-----------

1. Source the environment with proper template settings, flavour being *linux*
   and target machine being *stm32mp1-av96*. Pay attention to how relative
   paths are constructed. The value of *TEMPLATECONF* is relative to the
   location of the build directory *./build-linux*, that is going
   to be created after this step:

.. code-block:: console

   $ TEMPLATECONF=../sources/meta-ohos/flavours/linux . ./sources/poky/oe-init-build-env build-ohos-linux

2. You will find yourself in the newly created build directory. Call *bitbake*
   to build the image. For example, if you are using *allscenarios-image-base*
   run the following command:

.. code-block:: console

   $ MACHINE=stm32mp1-av96 bitbake allscenarios-image-base

To generate images for eMMC on SD card, refer to the :ref:`Flashing OHOS image <Flashing_ohos>`.

Zephyr image
------------

1. Source the environment with proper template settings, flavour being *zephyr*
   and target machine being *96b-avenger96*:

.. code-block:: console

   $ TEMPLATECONF=../sources/meta-ohos/flavours/zephyr . ./sources/poky/oe-init-build-env build-ohos-zephyr

2. You will find yourself in the newly created build directory. Call *bitbake*
   to build the image. The image name is the name of the Zephyr application.

.. code-block:: console

   $ MACHINE=96b-avenger96 bitbake zephyr-philosophers

3. The output file will be located in the build directory
   *./tmp-newlib/deploy/images/96b-avenger96/*.

.. _Flashing_ohos:

Flashing OHOS image
*******************

For Linux, STM meta-layer provide a convenient shell script that helps you to
create an SD card image. You can also use the `STM32 Cube Programmer <https://wiki.dh-electronics.com/index.php/Avenger96_Image_Programming>`__.

For Zephyr, there is no automation as for now. To have the ELF file in the filesystem:

* Copy the image manually to the filesystem using a method of your choice
* Include it in the image before flashing the card/eMMC
* Copy the file manually to the card or just *scp* it to the board after you set up networking.

Linux image
===========

SD card
-------

The Avenger96 board supports multiple boot options which are selected by the
DIP-switch S3. Make sure the boot switch is set to boot from the SD-Card.

To set the boot option from the SD card using DIP-switch S3, set the BOOT 0
(Switch 1) and BOOT 2 (Switch 3) to 1 and set BOOT 1 (Switch 2) to 0 on the
circuit board.

For more information on Avenger96 boot options, see `Getting Started with the Avenger96 <https://www.96boards.org/documentation/consumer/avenger96/getting-started/#starting-the-board-for-the-first-time>`__.

1. After the image is built, run the following script with flash layout TSV file provided as an argument. From the build directory created
   during the environment source. For example, if you are using
   allscenarios-image-base run the following command:

.. code-block:: console

   $ cd tmp/deploy/images/stm32mp1-av96
   $ ./scripts/create_sdcard_from_flashlayout.sh ./flashlayout_allscenarios-image-base/extensible/FlashLayout_sdcard_stm32mp157a-av96-extensible.tsv

2. The following output is displayed. For the image to be flashed to the card,
   copy and paste the commands to the terminal to flash the image onto the
   card.

::

   [WARNING]: A previous raw image are present on this directory
   [WARNING]:    ./flashlayout_allscenarios-image-base/extensible/../../FlashLayout_sdcard_stm32mp157a-av96-extensible.raw
   [WARNING]: would you like to erase it: [Y/n]

   Create Raw empty image: ./flashlayout_allscenarios-image-base/extensible/../../FlashLayout_sdcard_stm32mp157a-av96-extensible.raw of 2368MB
   Create partition table:
   [CREATED] part 1:    fsbl1 [partition size 256.0 KiB]
   [CREATED] part 2:    fsbl2 [partition size 256.0 KiB]
   [CREATED] part 3:     ssbl [partition size 2.0 MiB]
   [CREATED] part 4:     boot [partition size 64.0 MiB]
   [CREATED] part 5: vendorfs [partition size 16.0 MiB]
   [CREATED] part 6:   rootfs [partition size 2.2 GiB]

   Partition table from ./flashlayout_allscenarios-image-base/extensible/../../FlashLayout_sdcard_stm32mp157a-av96-extensible.raw

   Populate raw image with image content:
   [ FILLED ] part 1:    fsbl1, image: arm-trusted-firmware/tf-a-stm32mp157a-av96-trusted.stm32
   [ FILLED ] part 2:    fsbl2, image: arm-trusted-firmware/tf-a-stm32mp157a-av96-trusted.stm32
   [ FILLED ] part 3:     ssbl, image: bootloader/u-boot-stm32mp157a-av96-trusted.stm32
   [ FILLED ] part 4:     boot, image: st-image-bootfs-poky-stm32mp1-av96.ext4
   [ FILLED ] part 5: vendorfs, image: st-image-vendorfs-poky-stm32mp1-av96.ext4
   [ FILLED ] part 6:   rootfs, image: allscenarios-image-base-stm32mp1-av96.ext4

   ###########################################################################
   ###########################################################################

   RAW IMAGE generated: ./flashlayout_allscenarios-image-base/extensible/../../FlashLayout_sdcard_stm32mp157a-av96-extensible.raw

   WARNING: before to use the command dd, please umount all the partitions
           associated to SDCARD.
       sudo umount `lsblk --list | grep mmcblk0 | grep part | gawk '{ print $7 }' | tr '\n' ' '`

   To put this raw image on sdcard:
       sudo dd if=./flashlayout_allscenarios-image-base/extensible/../../FlashLayout_sdcard_stm32mp157a-av96-extensible.raw of=/dev/mmcblk0 bs=8M conv=fdatasync status=progress

   (mmcblk0 can be replaced by:
        sdX if it's a device dedicated to receive the raw image
             (where X can be a, b, c, d, e)

   ###########################################################################
   ###########################################################################

3. To unmount the card, call the ``umount`` command printed by the
   ``create_sdcard_from_flashlayout.sh`` script.

4. To flash the image card, call the ``dd`` command printed by the
   ``create_sdcard_from_flashlayout.sh`` script.

5. Put the card to the board and turn it on.

STM32 Cube Programmer
---------------------

After you build the image, follow the instructions in `Avenger96 Image Programming <https://wiki.dh-electronics.com/index.php/Avenger96_Image_Programming>`_,
pointing the program to the
*./tmp/deploy/images/stm32mp1-av96/flashlayout_allscenarios-image-base/trusted/FlashLayout_emmc_stm32mp157a-av96-trusted.tsv*
flash layout file.

.. _zephyr-image-1:

Zephyr image
============

**Prerequisites**

* Linux is running on the board.
* Make sure that Linux is built with *remoteproc* support. To check status of remoteproc do:

.. code-block:: console

   root@stm32mp1-av96:~# dmesg | grep remoteproc
   [    2.336231] remoteproc remoteproc0: m4 is available

1. Copy the Zephyr image to the board using a method of your choice.

2. Check what the ``remoteproc`` framework knows about the name and location of
   the firmware file. The default values are presented as follows. Empty path
   defaults to ``/lib/firmware``:

::

   root@stm32mp1-av96:~# cat /sys/module/firmware_class/parameters/path
   <empty>

   root@stm32mp1-av96:~# cat /sys/class/remoteproc/remoteproc0/firmware
   rproc-m4-fw

3. Configure the name and the location to suit your needs. For example, the
   firmware is located in ``/root/zephyr.elf``:

::

   root@stm32mp1-av96:~# echo "/root" > /sys/module/firmware_class/parameters/path
   root@stm32mp1-av96:~# echo "zephyr.elf" >  /sys/class/remoteproc/remoteproc0/firmware

4. Power up the Cortex-M4 core:

::

   root@stm32mp1-av96:~# echo start > /sys/class/remoteproc/remoteproc0/state
   remoteproc remoteproc0: powering up m4
   remoteprocroc remoteproc0: Booting fw image rproc-m4-fw, size 591544
   rproc-srm-core m4@0:m4_system_resources: bound m4@0:m4_system_resources:m4_led (ops 0xc0be1210)
   remoteproc remoteproc0: remote processor m4 is now

5. Firmware output can be inspected with:

::

   root@stm32mp1-av96:~# cat /sys/kernel/debug/remoteproc/remoteproc0/trace0
   Philosopher 5 [C:-2]        STARVING
   Philosopher 3 [P: 0]    DROPPED ONE FORK
   Philosopher 3 [P: 0]  THINKING [  25 ms ]
   Philosopher 2 [P: 1]   EATING  [  425 ms ]
   Philosopher 3 [P: 0]        STARVING
   Philosopher 4 [C:-1]        STARVING
   Philosopher 4 [C:-1]    HOLDING ONE FORK
   Philosopher 4 [C:-1]   EATING  [  800 ms ]
   Philosopher 3 [P: 0]    HOLDING ONE FORK
   Philosopher 2 [P: 1]    DROPPED ONE FORK
   Philosopher 2 [P: 1]  THINKING [  725 ms ]
   Philosopher 1 [P: 2]   EATING  [  225 ms ]

There is no fully-featured console available in Linux yet, so typing commands
to the Zephyr application is not possible.

Testing the board
*****************

Serial port
===========

To connect the USB converter serial port to the low-speed connector, see `Hardware User Manual <https://github.com/96boards/documentation/blob/master/consumer/avenger96/hardware-docs/files/avenger96-hardware-user-manual.pdf>`__.

.. warning::

   * The low speed connector is 1.8V tolerant, therefore the converter must be 1.8V tolerant.
   * Do not connect 5V or 3.3V tolerant devices to the connector to avoid SoC damage.

Ethernet
========

Wired connection works out of the box. You can use standard tools like ``ip``,
``ifconfig`` to configure the connection. The connection seems to have stable
1Gb/s bandwidth.

USB Host
========

Just plug something to the USB port. The board seems to work fine with an
external 500GB USB 3.0 HDD.

::

   root@stm32mp1-av96:~# lsusb
   Bus 002 Device 003: ID 0930:0b1f Toshiba Corp.
   Bus 002 Device 002: ID 0424:2513 Standard Microsystems Corp. 2.0 Hub
   Bus 002 Device 001: ID 1d6b:0002 Linux Foundation 2.0 root hub
   Bus 001 Device 001: ID 1d6b:0002 Linux Foundation 2.0 root hub
   root@stm32mp1-av96:~# lsusb -t
   /:  Bus 02.Port 1: Dev 1, Class=root_hub, Driver=ehci-platform/2p, 480M
       |__ Port 1: Dev 2, If 0, Class=Hub, Driver=hub/3p, 480M
           |__ Port 2: Dev 3, If 0, Class=Mass Storage, Driver=usb-storage, 480M
   /:  Bus 01.Port 1: Dev 1, Class=root_hub, Driver=dwc2/1p, 480M
   root@stm32mp1-av96:~# mount | grep sda
   /dev/sda1 on /home/root/sda1 type vfat (rw,relatime,fmask=0022,dmask=0022,codepage=437,iocharset=iso8859-1,shortname=mixed,errors=remount-ro)

USB OTG
=======

The board supports that feature. For now it only works in DFU mode with STM32
Cube Programmer. Using the board as USB Gadget is currently under development.

eMMC
====

It can be used to store the firmware with STM32 Cube Programmer. It can also be
mounted under Linux booted from another medium:

::

   root@stm32mp1-av96:~# mount /dev/mmcblk2p4 emmc/
   [ 3006.721643] EXT4-fs (mmcblk2p4): recovery complete
   [ 3006.726627] EXT4-fs (mmcblk2p4): mounted filesystem with ordered data mode. Opts: (null)
   [ 3006.733931] ext4 filesystem being mounted at /home/root/emmc supports timestamps until 2038 (0x7fffffff)
   root@stm32mp1-av96:~# ls -l emmc
   drwxr-xr-x    2 root     root          1024 Mar  9 12:34 bin
   drwxr-xr-x    2 root     root          1024 Mar  9 12:34 boot
   drwxr-xr-x    2 root     root          1024 Mar  9 12:34 dev
   drwxr-xr-x   17 root     root          1024 Mar  9 12:34 etc
   drwxr-xr-x    3 root     root          1024 Mar  9 12:34 home
   drwxr-xr-x    3 root     root          1024 Mar  9 12:34 lib
   drwx------    2 root     root         12288 Jan 12  2021 lost+found
   drwxr-xr-x    2 root     root          1024 Mar  9 12:34 media
   drwxr-xr-x    2 root     root          1024 Mar  9 12:34 mnt
   dr-xr-xr-x    2 root     root          1024 Mar  9 12:34 proc
   drwxr-xr-x    2 root     root          1024 Jan  1  2000 run
   drwxr-xr-x    2 root     root          1024 Mar  9 12:34 sbin
   dr-xr-xr-x    2 root     root          1024 Mar  9 12:34 sys
   lrwxrwxrwx    1 root     root             8 Mar  9 12:34 tmp -> /var/tmp
   drwxr-xr-x   10 root     root          1024 Mar  9 12:34 usr
   drwxr-xr-x    8 root     root          1024 Mar  9 12:34 var

Radio
=====

Radio relies on proprietary BRCM firmware. It is already included in the image.

WiFi
----

WiFi can be controlled with ``wpa_supplicant``, which is a standard Linux tool.
Please refer to the tool manual for the details.

Example ``wpa_suppliant`` configs look like below. Assuming the config is saved
in a file named ``wpa.conf`` and the interface is named ``wlan0``, WiFi can be
brought up with ``wpa_supplicant -i wlan0 -c ./wpa.conf``:

::

   # Access Point mode example configuration
   fast_reauth=1
   update_config=1

   ap_scan=2
   network={
           ssid="Avenger96 AP"
           mode=2
           frequency=2412
           key_mgmt=WPA-PSK
           proto=RSN
           pairwise=CCMP
           psk="PlaintextPasswordsAreGreat"
   }

::

   # Connection to an open network with broadcasted SSID
   network={
           ssid="0xDEADBEEF"
           key_mgmt=NONE
   }

Bluetooth
---------

Bluetooth be controlled with ``bluetoothctl``, which is a standard Linux tool.
Please refer to the tool manual for the details. Devices scanning can be
enabled as follows:

::

   root@stm32mp1-av96:~# bluetoothctl
   Agent registered
   [CHG] Controller 00:9D:6B:AA:77:68 Pairable: yes
   [bluetooth]# power on
   Changing power on succeeded
   [CHG] Controller 00:9D:6B:AA:77:68 Powered: yes
   [bluetooth]# discoverable on
   Changing discoverable on succeeded
   [CHG] Controller 00:9D:6B:AA:77:68 Discoverable: yes
   [bluetooth]# scan on
   Discovery started
   [CHG] Controller 00:9D:6B:AA:77:68 Discovering: yes
   [NEW] Device E2:A0:50:99:C9:61 Hue Lamp
   [NEW] Device 57:2D:D5:48:8C:D0 57-2D-D5-48-8C-D0
   [NEW] Device E4:04:39:65:9C:2A TomTom GPS Watch
   [NEW] Device C0:28:8D:49:67:7E C0-28-8D-49-67-7E

Pairing and establishing connection is possible with ``pair`` and ``connect``
commands.
