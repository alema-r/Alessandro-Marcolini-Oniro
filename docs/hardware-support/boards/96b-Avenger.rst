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

Building an Oniro image
=======================

To clone the source code, perform the procedure in: :ref:`Setting up a repo workspace <RepoWorkspace>`.

Linux image
-----------

1. Source the environment with proper template settings, flavour being *linux*
   and target machine being *stm32mp1-av96*. Pay attention to how relative
   paths are constructed. The value of *TEMPLATECONF* is relative to the
   location of the build directory *./build-linux*, that is going
   to be created after this step:

.. code-block:: console

   $ TEMPLATECONF=../oniro/flavours/linux . ./oe-core/oe-init-build-env build-oniro-linux

2. You will find yourself in the newly created build directory. Call *bitbake*
   to build the image. For example, if you are using *oniro-image-base*
   run the following command:

.. code-block:: console

   $ MACHINE=stm32mp1-av96 bitbake oniro-image-base

To generate images for eMMC on SD card, refer to the :ref:`Flashing an Oniro image <Flashing_oniro>`.

Zephyr image
------------

1. Source the environment with proper template settings, flavour being *zephyr*
   and target machine being *96b-avenger96*:

.. code-block:: console

   $ TEMPLATECONF=../oniro/flavours/zephyr . ./oe-core/oe-init-build-env build-oniro-zephyr

2. You will find yourself in the newly created build directory. Call *bitbake*
   to build the image. The image name is the name of the Zephyr application.

.. code-block:: console

   $ MACHINE=96b-avenger96 bitbake zephyr-philosophers

3. The output file will be located in the build directory
   *./tmp-newlib/deploy/images/96b-avenger96/*.

.. _Flashing_oniro:

Flashing an Oniro image
***********************

For Linux, `bmaptool <https://github.com/intel/bmap-tools>` is recommended to
create an SD card image. The images we provide also create wic files (disk
images) that you can use directly. You can also use the `STM32 Cube Programmer <https://wiki.dh-electronics.com/index.php/Avenger96_Image_Programming>`__.

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

1. After the image is built, you are ready to burn the generated image onto the
   SD card. We recommend using `bmaptool <https://github.com/intel/bmap-tools>`
   and the instructions below will use it. For example, if you are building
   oniro-image-base run the following command replacing (or defining)
   ``$DEVNODE`` accordingly:

.. code-block:: console

   $ cd tmp/deploy/images/stm32mp1-av96
   $ bmaptool copy oniro-image-base-stm32mp1-av96.wic.bz2 $DEVNODE

2. Put the card to the board and turn it on.

STM32 Cube Programmer
---------------------

After you build the image, follow the instructions in `Avenger96 Image Programming <https://wiki.dh-electronics.com/index.php/Avenger96_Image_Programming>`_,
pointing the program to the
*./tmp/deploy/images/stm32mp1-av96/flashlayout_oniro-image-base/trusted/FlashLayout_emmc_stm32mp157a-av96-trusted.tsv*
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

For any fault in the hardware device, see :ref:`How to handle faulty hardware device <FallbackSupport>`.

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

For any fault in the hardware device, see :ref:`How to handle faulty hardware device <FallbackSupport>`.

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

For any fault in the hardware device, see :ref:`How to handle faulty hardware device <FallbackSupport>`.
