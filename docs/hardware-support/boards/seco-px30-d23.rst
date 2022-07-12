.. SPDX-FileCopyrightText: Huawei Inc.
..
.. SPDX-License-Identifier: CC-BY-4.0

.. _SupportedBoardSecoD23:

JUNO (SBC-D23) SECO
###################

.. contents::
   :depth: 3

Overview
********

JUNO (SBC-D23) is a Single Board Computer based on embedded Rockchip PX30 Processor, featuring Quad-Core ARM® Cortex®-A35 processor.
The processor integrates a Mali-G31 GPU with High performance dedicated 2D processor, supporting OpenGL® ES 1.1 / 2.0 / 3.2,
Vulkan 1.0, OpenCL 2.0 and Open VG 1.1. Embedded VPU is able to support video decoding of the most common coding standard (MPEG-4,
H.265/HEVC, H.264, VP8, VC-1). The board is completed with up to 4GB LPDDR4-3200 32-bit bus memory directly soldered on board and
one eMMC 5.1 Flash Drive with up to 64GB of capacity. LVDS Single Channel interface and HDMI are supported. The RMII interface and
Micrel KSZ8091 Ethernet Transceiver allow the implementation of a Fast Ethernet interface. The networking capabilities can be extended
by WiFi+BT M.2 module and external modem module. The audio functionalities are managed by the AudioCodec embedded in the RK-809 PMIC.
The JUNO board is completed by a series of connectors with various interfaces (UART, SPI, I2C) managed by the microcontroller STM32F302VCT6.

Hardware
********
* For product specification of JUNO (SBC-D23) SECO board, see `Juno page <https://edge.seco.com/it/juno.html>`_.
* For more detailed specifications, see `User Manual <https://www.seco.com/Manuals/SBC-D23_Manual.pdf>`__.

Working with the Board
**********************

Building an Oniro image
=======================

To clone the source code, perform the procedure in: :ref:`Setting up a repo workspace <RepoWorkspace>`.

Linux image
-----------

1. Source the environment with proper template settings, flavour being ``linux`` and target machine being ``seco-px30-d23``.

.. code-block:: console

   $ TEMPLATECONF=../oniro/flavours/linux . ./oe-core/oe-init-build-env build-oniro-linux

2. You will find yourself in the newly created build directory. Call ``bitbake`` to build the image. The supported image is ``oniro-image-base``.

.. code-block:: console

   $ MACHINE=seco-px30-d23 bitbake oniro-image-base

You can set-up MACHINE variable in ``conf/local.conf`` file under the build
directory, or via the command line.

Flashing an Oniro image
***********************

Linux image
===========

MMC Storage
-----------

**Prerequisites**

* USB to TTL Serial cable
* USB Host Type-A to USB OTG interface on USB recovery connector cable (programming cable)
* Linux Host machine

For detailed information on connectors, refer to the User Manual mentioned above.

After the image is built, you are ready to burn the generated image into the eMMC.
Build artifacts and update utilities are located in path ./tmp/deploy/images/seco-px30-d23/, relative to the build directory.

To flash Oniro image perform the following steps:

1. Perform the next two steps to view the boot console output

* Connect the host machine USB port type-A with Debug UART connector (CN 27) on the board over USB to TTL Serial cable.

* Open in the terminal your preferred serial port communication program (minicom or gtkterm, for example) and configure
  the serial port (port: dev/ttyUSB0, baud rate: 115200, data bits: 8, stop bit: 1, parity: none, flow control: none).

2. Put your device into Rockusb mode.

For more details about this mode, see `Wiki Rockusb <http://opensource.rock-chips.com/wiki_Rockusb>`__.

.. note::
   When there is no bootable firmware on the board, the SoC will run Rockusb driver automatically from its on-chip ROM boot code.
   In this case, skip the next two steps.

3. Power on the board and stop boot process within boot delay (5 seconds), using combination of the keys ``Ctrl+C``

4. In the U-Boot shell run command:

.. code-block:: console

   => mmc erase 0x4000 0x2000

This command will erase u-boot from eMMC and in this way the board will enter into Rockusb mode on the next power on.

5. Connect the host machine USB port type-A with USB recovery connector (CN7) on the board over the programming cable.

6. Power OFF/ON the board.

7. Check if on your host machine the board has appeared as USB device:

.. code-block:: console

   $ lsusb | grep Rockchip

You will see the command output like this :

.. code-block:: console

   Bus 001 Device 010: ID 2207:330d Fuzhou Rockchip Electronics Company

8. Burn image into eMMC

For example, if you are building oniro-image-base run the following commands:

.. code-block:: console

   $ cd tmp/deploy/images/seco-px30-d23
   $ chmod u+x flash_d23.sh
   $ sudo ./flash_d23.sh -a oniro-image-base-seco-px30-d23-emmc.wic

After the image has been successfully burned into the emmc, unplug the programming cable from the host machine.

9. Power OFF/ON the board.

Testing the Board
*****************

Ethernet
========

You can use standard tools like ``ip``, ``ifconfig`` to configure and ``ping`` to test the connection.

::

   root@seco-px30-d23:~# ifconfig
   eth0   Link encap:Ethernet  HWaddr C6:73:F6:CB:81:BE
          inet addr:192.168.1.200  Bcast:192.168.1.255  Mask:255.255.255.0
          inet6 addr: fe80::56a3:8c7:961e:fd8/64 Scope:Link
          UP BROADCAST RUNNING MULTICAST  MTU:1500  Metric:1
          RX packets:205 errors:0 dropped:55 overruns:0 frame:0
          TX packets:71 errors:0 dropped:0 overruns:0 carrier:0
          collisions:0 txqueuelen:1000
          RX bytes:13783 (13.4 KiB)  TX bytes:8885 (8.6 KiB)
          Interrupt:33

   root@seco-px30-d23:~# ping www.google.com
   PING www.google.com (172.217.168.68): 56 data bytes
   64 bytes from 172.217.168.68: seq=0 ttl=113 time=6.983 ms
   64 bytes from 172.217.168.68: seq=1 ttl=113 time=6.757 ms
   64 bytes from 172.217.168.68: seq=2 ttl=113 time=7.445 ms

   --- www.google.com ping statistics ---
   3 packets transmitted, 3 packets received, 0% packet loss
   round-trip min/avg/max = 6.757/7.061/7.445 ms

USB Host
========

::

   root@seco-px30-d23:~# lsusb
   Bus 002 Device 001: ID 1d6b:0001 Linux Foundation 1.1 root hub
   Bus 001 Device 002: ID 0424:2514 Microchip Technology, Inc. (formerly SMSC) USB 2.0 Hub
   Bus 001 Device 001: ID 1d6b:0002 Linux Foundation 2.0 root hub
   Bus 003 Device 003: ID 0781:5591 SanDisk Corp. Ultra Flair
   Bus 003 Device 001: ID 1d6b:0002 Linux Foundation 2.0 root hub

eMMC
====

::

   root@seco-px30-d23:~# dmesg | grep mmc0
   [    3.816535] mmc_host mmc0: card is non-removable.
   [    3.829634] mmc_host mmc0: Bus speed (slot 0) = 400000Hz (slot req 400000Hz, actual 400000HZ div = 0)
   [    3.966802] mmc_host mmc0: Bus speed (slot 0) = 50000000Hz (slot req 52000000Hz, actual 50000000HZ div = 0)
   [    3.984843] mmc0: new high speed MMC card at address 0001
   [    3.995157] mmcblk0: mmc0:0001 DG4016 14.7 GiB
   [    4.003237] mmcblk0boot0: mmc0:0001 DG4016 partition 1 4.00 MiB
   [    4.012302] mmcblk0boot1: mmc0:0001 DG4016 partition 2 4.00 MiB
   [    4.027241] mmcblk0rpmb: mmc0:0001 DG4016 partition 3 4.00 MiB, chardev (241:0)

