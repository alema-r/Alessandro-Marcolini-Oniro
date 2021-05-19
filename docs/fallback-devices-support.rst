.. SPDX-FileCopyrightText: Huawei Inc.
..
.. SPDX-License-Identifier: CC-BY-4.0

.. _FallbackSupport:

How to handle faulty hardware device?
#####################################

In a situation where you have enabled a new board and one of the devices is 
faulty or has some issues (e.g. driver, hardware, firmware, and so on), to 
continue with the setup, it is important to have an alternative option for the 
faulty device.

You can replace the respective faulty device with an external devices listed 
in the following table. The devices listed are hardware components with 
opensource drivers and have no usage restrictions.

 .. list-table:: Supported External Hardware Device 
   :header-rows: 1

   * - Device
     - Chip
     - Firmware status
     - Dongle name
     - Remarks
   * - Ethernet
     - RTL8153
     - `linux-firmware <https://git.kernel.org/pub/scm/linux/kernel/git/firmware/linux-firmware.git/tree/WHENCE#n3284>`__ 
     - Many available
     - SFP variants available
   * - WiFi
     - RTl8192cu
     - `linux-firmware <https://git.kernel.org/pub/scm/linux/kernel/git/firmware/linux-firmware.git/tree/WHENCE#n2972>`__, `license <https://git.kernel.org/pub/scm/linux/kernel/git/firmware/linux-firmware.git/tree/LICENCE.rtlwifi_firmware.txt>`__
     - OURLiNK
     - Works out-of-the-box
   * - WiFi
     - Ralink RT5372
     - `linux-firmware <https://git.kernel.org/pub/scm/linux/kernel/git/firmware/linux-firmware.git/tree/WHENCE#n1673>`__, `license <https://git.kernel.org/pub/scm/linux/kernel/git/firmware/linux-firmware.git/tree/LICENCE.ralink-firmware.txt>`__
     - PAU05 - Panda Wireless
     - Works out-of-the-box
   * - Bluetooth
     - BCM20702A0
     - `linux-firmwar <https://git.kernel.org/pub/scm/linux/kernel/git/firmware/linux-firmware.git/tree/WHENCE#n13>`__, `license <https://git.kernel.org/pub/scm/linux/kernel/git/firmware/linux-firmware.git/tree/LICENCE.cypress>`__
     - PLUGABLE
     - Works out-of-the-box
   * - Bluetooth
     - BCM20702A0
     - `linux-firmwar <https://git.kernel.org/pub/scm/linux/kernel/git/firmware/linux-firmware.git/tree/WHENCE#n13>`__, `license <https://git.kernel.org/pub/scm/linux/kernel/git/firmware/linux-firmware.git/tree/LICENCE.cypress>`__
     - Kinivo
     - Works out-of-the-box
   * - IEEE 802.15.4
     - AT86RF231
     - Flashed on device
     - ATUSB
     - `Open hardware and firmware <http://downloads.qi-hardware.com/people/werner/wpan/web/>`_
