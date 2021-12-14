.. SPDX-FileCopyrightText: Huawei Inc.
..
.. SPDX-License-Identifier: CC-BY-4.0

.. _SupportedBoardSecoC61:

SBC-C61 SECO
############

.. contents::
   :depth: 3

Overview
********

SBC-C61 is an SBC built upon the NXP i.MX 8M mini Application Processors
characterised by HEVC/VP9 decoding in 1080p60. As for the memory, it features a
LPDDR4 RAM. The range of connectivity options is particularly broad, with
optional Wi-Fi and BT LE 4.2 and optionally soldered on-board LTE Cat 4 Modem
with microSIM slot or eSIM. Interestingly, it also features a Cortex-M4, that
is real-time operating system capable for serving real-time applications that
process data as it comes in without buffer delays.

Hardware
********

For more detailed specifications of SBC-C61 SECO board, see `SBC-C61 Specification <https://www.seco.com/en/products/sbc-c61>`__.

Working with the Board
**********************

Building an Oniro image
=======================

To clone the source code, perform the procedure in: :ref:`Setting up a repo workspace <RepoWorkspace>`.

Linux image
-----------

1. Source the environment with proper template settings, flavour being ``linux`` and target machine being ``seco-imx8mm-c61``.

.. code-block:: console

   $ TEMPLATECONF=../oniro/flavours/linux . ./oe-core/oe-init-build-env build-oniro-linux

2. You will find yourself in the newly created build directory. Call ``bitbake`` to build the image. The supported image is ``oniro-image-base``.

.. code-block:: console

   $ MACHINE=seco-imx8mm-c61 bitbake oniro-image-base

To generate images for eMMC, refer to the following flashing procedure.

Flashing an Oniro image
***********************

Linux image
===========

MMC Storage
-----------

**Prerequisites**

* USB To UART adapter
* USB to OTG adapter
* Download and install `mfgtools <https://github.com/NXPmicro/mfgtools>`__
* Linux Host

To flash Oniro image using USB to OTG adapter, perform the
following steps:

#. Short circuit pin 1 and 2 of CN52 pin header to enter the Serial Download mode.
#. Connect USB to OTG adapter to your host PC
#. Navigate to the inside build output directory:

   .. code-block:: console

      $ cd tmp/deploy/images/seco-imx8mm-c61/

#. Unzip build output using Gzip software:

   .. code-block:: console

      $ gzip -d oniro-image-base-seco-imx8mm-c61.wic.gz

#. To write uboot and image(p1:kernel, p2:dtb, rootfs) into c61 mmc via mfgtools:

   .. code-block:: console

      $ sudo uuu -b emmc_all imx-boot-seco-imx8mm-c61-emmc.bin-flash_evk oniro-image-base-seco-imx8mm-c61.wic

#. Power ON SBC-C61
#. Remove **CN52 short circuit**
#. Press the reset button

Testing the Board
*****************

Ethernet
========

You can use standard tools like ``ip``, ``ifconfig`` to configure the connection.

::

   root@seco-imx8mm-c61:~# ifconfig
   eth0      Link encap:Ethernet  HWaddr 1A:20:58:83:70:F0
             UP BROADCAST MULTICAST  MTU:1500  Metric:1
             RX packets:0 errors:0 dropped:0 overruns:0 frame:0
             TX packets:0 errors:0 dropped:0 overruns:0 carrier:0
             collisions:0 txqueuelen:1000
             RX bytes:0 (0.0 B)  TX bytes:0 (0.0 B)

For any fault in the hardware device, see :ref:`How to handle faulty hardware device <FallbackSupport>`.

USB Host
========

::

   root@seco-imx8mm-c61:~# lsusb
   Bus 001 Device 003: ID 058f:6387 Alcor Micro Corp. Flash Drive
   Bus 001 Device 002: ID 0424:2514 Standard Microsystems Corp. USB 2.0 Hub
   Bus 001 Device 001: ID 1d6b:0002 Linux Foundation 2.0 root hub

eMMC
====

::

   root@seco-imx8mm-c61:~# fdisk -l /dev/mmcblk0
   Disk /dev/mmcblk0: 59 GB, 63585648640 bytes, 124190720 sectors
   1940480 cylinders, 4 heads, 16 sectors/track
   Units: sectors of 1 * 512 = 512 bytes

   Device       Boot StartCHS    EndCHS        StartLBA     EndLBA    Sectors  Size Id Type
   /dev/mmcblk0p1 *  64,0,1      893,3,4           8192     114403     106212 51.8M  c Win95 FAT32 (LBA)
   /dev/mmcblk0p2    896,0,1     1023,3,32       114688     558903     444216  216M 83 Linux

Loaded Modules
==============

::

   root@seco-imx8mm-c61:~# lsmod
   Module                  Size  Used by
   nfc                    90112  0
   bluetooth             409600  8
   ecdh_generic           16384  1 bluetooth
   ecc                    32768  1 ecdh_generic
   rfkill                 36864  3 nfc,bluetooth
   ipv6                  442368  26
   caam_jr               196608  0
   caamhash_desc          16384  1 caam_jr
   caamalg_desc           36864  1 caam_jr
   crypto_engine          16384  1 caam_jr
   rng_core               24576  1 caam_jr
   authenc                16384  1 caam_jr
   libdes                 24576  1 caam_jr
   snd_soc_simple_card    20480  0
   fsl_imx8_ddr_perf      20480  0
   crct10dif_ce           20480  1
   snd_soc_simple_card_utils    24576  1 snd_soc_simple_card
   rtc_snvs               16384  1
   snvs_pwrkey            16384  0
   caam                   40960  1 caam_jr
   clk_bd718x7            16384  0
   error                  24576  4 caamalg_desc,caamhash_desc,caam,caam_jr
   imx8mm_thermal         16384  0
   snd_soc_fsl_sai        20480  0
   imx_cpufreq_dt         16384  0
