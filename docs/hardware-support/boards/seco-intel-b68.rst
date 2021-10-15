.. SPDX-FileCopyrightText: Huawei Inc.
..
.. SPDX-License-Identifier: CC-BY-4.0

.. include:: ../../definitions.rst

.. _SupportedBoardSecoB68:

SBC-B68-eNUC SECO
#################

.. contents::
   :depth: 3

Overview
********

The SBC-B68-eNUC is a flexible and expandable full industrial x86 embedded NUC™ SBC with the Intel® Atom X Series, Intel® Celeron® J / N
Series and Intel® Pentium® N Series (formerly code name Apollo Lake) Processors. Also available in industrial temperature version, the board
offers wide range of connectivity options through WLAN and WWAN M.2 slots as well as wide input voltage range. Featuring Quad Channel
soldered down LPDDR4-2400 memory, up to 8GB, thanks to its versatile expansion capabilities it is particularly suitable for embedded
applications like HMI, multimedia devices, industrial IoT and industrial automation.

Hardware
********

For more detailed specifications of SBC-B68-eNUC SECO board, see `SBC-B68-eNUC Specification <https://www.seco.com/en/products/sbc-b68-enuc>`__.


Working with the board
**********************

Building Oniro image
====================

To clone the source code, perform the procedure in: :ref:`Setting up a repo workspace <RepoWorkspace>`.

Linux image
-----------

1. Source the environment with proper template settings, flavour being *linux* and target machine being *seco-intel-b68*.

.. code-block:: console

   $ TEMPLATECONF=../oniro/flavours/linux . ./oe-core/oe-init-build-env build-oniro-linux

2. You will find yourself in the newly created build directory. Call *bitbake* to build the image. For example, if you are using *allscenarios-image-base* run the following command:

.. code-block:: console

   $ MACHINE=seco-intel-b68 bitbake allscenarios-image-base

To generate images for SSD Disk, refer to the following flashing OHOS image section.

Flashing OHOS image
*******************

.. _linux-image-2:

Linux image
===========

USB Storage
-----------

**Prerequisites**

* Mini DisplayPort to HDMI converter cable
* HDMI Monitor
* USB Storage
* Linux Host

To flash OHOS using USB storage, perform the following steps:

**Prepare OHOS bootable USB**

1. Connect USB storage to your host PC.

2. After the image is built, you are ready to burn the generated image onto the
   USB storage. We recommend using `bmaptool <https://github.com/intel/bmap-tools>`
   and the instructions below will use it. For example, if you are building
   allscenarios-image-base run the following command replacing (or defining)
   ``$DEVNODE`` accordingly:

.. code-block:: console

   $ cd tmp/deploy/images/seco-intel-b68
   $ bmaptool copy allscenarios-image-base-seco-intel-b68.wic.bz2 $DEVNODE

3. Put the card to the board and turn it on.

**Run OHOS**

#. Connect bootable USB to target

#. Connect mini DP++ to HDMI adapter to HDMI monitor

#. Power on B68 and press **Esc** to enter **BIOS** mode.

#. Go to Save and Exit submenu

#. Select the bootable USB device under **Boot Override** and press Enter.


Testing the board
*****************

Ethernet
========

Wired connection works out of the box. You can use standard tools like ``ip``, ``ifconfig`` to configure the connection.

For any fault in the hardware device, see :ref:`How to handle faulty hardware device <FallbackSupport>`.

USB Host
========

::

   root@seco-intel-b68:~# lsusb
   /:  Bus 02.Port 1: Dev 1, Class=root_hub, Driver=xhci_hcd/7p, 5000M
   /:  Bus 01.Port 1: Dev 1, Class=root_hub, Driver=xhci_hcd/8p, 480M

eMMC
====

::

   root@seco-intel-b68:~# fdisk -l /dev/mmcblk1
   Disk /dev/mmcblk1: 29 GB, 31268536320 bytes, 61071360 sectors
   954240 cylinders, 4 heads, 16 sectors/track
   Units: sectors of 1 * 512 = 512 bytes

PCI buses
=========

::

   root@seco-intel-b68:~# lspci
   00:00.0 Host bridge: Intel Corporation Celeron N3350/Pentium N4200/Atom E3900 Series Host Bridge (rev 0b)
   00:02.0 VGA compatible controller: Intel Corporation HD Graphics 500 (rev 0b)
   00:0e.0 Audio device: Intel Corporation Celeron N3350/Pentium N4200/Atom E3900 Series Audio Cluster (rev 0b)
   00:0f.0 Communication controller: Intel Corporation Celeron N3350/Pentium N4200/Atom E3900 Series Trusted Execution Engine (rev 0b)
   00:12.0 SATA controller: Intel Corporation Celeron N3350/Pentium N4200/Atom E3900 Series SATA AHCI Controller (rev 0b)
   00:13.0 PCI bridge: Intel Corporation Celeron N3350/Pentium N4200/Atom E3900 Series PCI Express Port A #3 (rev fb)
   00:13.3 PCI bridge: Intel Corporation Celeron N3350/Pentium N4200/Atom E3900 Series PCI Express Port A #4 (rev fb)
   00:15.0 USB controller: Intel Corporation Celeron N3350/Pentium N4200/Atom E3900 Series USB xHCI (rev 0b)
   00:16.0 Signal processing controller: Intel Corporation Celeron N3350/Pentium N4200/Atom E3900 Series I2C Controller #1 (rev 0b)
   00:16.3 Signal processing controller: Intel Corporation Celeron N3350/Pentium N4200/Atom E3900 Series I2C Controller #4 (rev 0b)
   00:17.0 Signal processing controller: Intel Corporation Celeron N3350/Pentium N4200/Atom E3900 Series I2C Controller #5 (rev 0b)
   00:17.1 Signal processing controller: Intel Corporation Celeron N3350/Pentium N4200/Atom E3900 Series I2C Controller #6 (rev 0b)
   00:18.0 Signal processing controller: Intel Corporation Celeron N3350/Pentium N4200/Atom E3900 Series HSUART Controller #1 (rev 0b)
   00:18.2 Signal processing controller: Intel Corporation Celeron N3350/Pentium N4200/Atom E3900 Series HSUART Controller #3 (rev 0b)
   00:1b.0 SD Host controller: Intel Corporation Celeron N3350/Pentium N4200/Atom E3900 Series SDXC/MMC Host Controller (rev 0b)
   00:1c.0 SD Host controller: Intel Corporation Celeron N3350/Pentium N4200/Atom E3900 Series eMMC Controller (rev 0b)
   00:1f.0 ISA bridge: Intel Corporation Celeron N3350/Pentium N4200/Atom E3900 Series Low Pin Count Interface (rev 0b)
   00:1f.1 SMBus: Intel Corporation Celeron N3350/Pentium N4200/Atom E3900 Series SMBus Controller (rev 0b)
   01:00.0 Ethernet controller: Intel Corporation I210 Gigabit Network Connection (rev 03)
   02:00.0 Ethernet controller: Intel Corporation I210 Gigabit Network Connection (rev 03)

Loaded Modules
==============

::

   root@seco-intel-b68:~# lsmod
   Module                  Size  Used by
   nfc                    73728  0
   bnep                   20480  2
   uio                    20480  0
   snd_hda_codec_hdmi     53248  1
   iwlwifi               299008  0
   cfg80211              688128  1 iwlwifi
   snd_hda_codec_cirrus    20480  1
   snd_hda_codec_generic    65536  1 snd_hda_codec_cirrus
   ledtrig_audio          16384  1 snd_hda_codec_generic
   intel_rapl_msr         16384  0
   snd_soc_skl           114688  0
   snd_soc_sst_ipc        16384  1 snd_soc_skl
   snd_soc_sst_dsp        24576  1 snd_soc_skl
   snd_hda_ext_core       20480  1 snd_soc_skl
   snd_soc_acpi_intel_match    36864  1 snd_soc_skl
   snd_soc_acpi           16384  2 snd_soc_acpi_intel_match,snd_soc_skl
   snd_soc_core          200704  1 snd_soc_skl
   intel_rapl_common      20480  1 intel_rapl_msr
   snd_compress           20480  1 snd_soc_core
   ac97_bus               16384  1 snd_soc_core
   intel_pmc_bxt          16384  0
   intel_telemetry_pltdrv    20480  0
   intel_telemetry_core    16384  1 intel_telemetry_pltdrv
   snd_hda_intel          32768  0
   x86_pkg_temp_thermal    16384  0
   snd_intel_dspcfg       16384  2 snd_hda_intel,snd_soc_skl
   snd_hda_codec          98304  4 snd_hda_codec_generic,snd_hda_codec_hdmi,snd_hda_intel,snd_hda_codec_cirrus
   coretemp               16384  0
   snd_hda_core           65536  7 snd_hda_codec_generic,snd_hda_codec_hdmi,snd_hda_intel,snd_hda_ext_core,snd_hda_codec,snd_hda_codec_cirrus,snd_soc_skl
   snd_pcm                86016  7 snd_hda_codec_hdmi,snd_hda_intel,snd_hda_codec,snd_compress,snd_soc_core,snd_soc_skl,snd_hda_core
   snd_timer              32768  1 snd_pcm
   i915                 1888256  5
   mei_me                 32768  0
   video                  40960  1 i915
   mei                    81920  1 mei_me

Video
=====

Output video tested with *DP++* to *HDMI* adapter.
