.. SPDX-FileCopyrightText: Huawei Inc.
..
.. SPDX-License-Identifier: CC-BY-4.0

.. _raspberrypi:

Raspberry Pi 4 Model B
######################

.. contents::
   :depth: 3

Overview
********

Raspberry Pi 4 Model B is powered with Broadcom BCM2711, quad-core Cortex-A72
(ARM v8) 64-bit SoC @ 1.5GHz. This product's key features include a
high-performance 64-bit quad-core processor, dual-display support at
resolutions up to 4K via a pair of micro-HDMI ports, hardware video decode at
up to 4Kp60, and the RAM size various from 2GB, 4GB, or 8GB, dual-band
2.4/5.0GHz wireless LAN, Bluetooth 5.0, Gigabit Ethernet, USB 3.0, and PoE
capability (via a separate PoE HAT add-on). The dual-band wireless LAN and
Bluetooth have modular compliance certification, allowing the board to be
designed into end products with significantly reduced compliance testing,
improving both cost and time to market.

Applications
************

* Embedded Design & Development
* Hobby & Education
* IoT (Internet of Things)
* Communications & Networking

Hardware
********

* For Raspberry Pi 4 Model B Schematics, see `RaspberryPi official website
  <https://www.raspberrypi.org/documentation/hardware/raspberrypi/schematics/rpi_SCH_4b_4p0_reduced.pdf>`__.

* For Raspberry Pi 4 Model B datasheet, see `RaspberryPi official website
  <https://www.raspberrypi.org/documentation/hardware/raspberrypi/bcm2711/rpi_DATA_2711_1p0.pdf>`__.

* For Raspberry Pi 4 boot EEPROM, see `RaspberryPi official website
  <https://www.raspberrypi.org/documentation/hardware/raspberrypi/booteeprom.md>`__.

For more details on the Raspberry Pi 4 board, see `Raspberry Pi hardware page
<https://www.raspberrypi.org/documentation/hardware/raspberrypi/>`__.

Working with the board
**********************

Building All Scenarios OS image
===============================

To clone the source code, perform the procedure in: :ref:`Setting up a repo
workspace <RepoWorkspace>`.

Linux image
-----------

1. Source the environment with proper template settings, the flavour being
   *linux* and target machine being *raspberrypi4-64*. Pay attention to how
   relative paths are constructed. The value of *TEMPLATECONF* is relative to
   the location of the build directory *./build-linux-raspberrypi4-64*, which
   is going to be created after this step:

.. code-block:: console

   $ TEMPLATECONF=../sources/meta-ohos/flavours/linux . \
      ./sources/poky/oe-init-build-env build-ohos-linux-raspberrypi4-64

2. You will find yourself in the newly created build directory. Call *bitbake*
   to build the image. For example, if you are using *allscenarios-image-base*
   run the following command:

.. code-block:: console

   $ MACHINE=raspberrypi4-64 bitbake allscenarios-image-base

3. After the build completes, the bootloader, kernel, and rootfs image files
   can be found in
   ``build-ohos-linux-raspberrypi4-64/tmp/deploy/images/$MACHINE/``.
   The key file which is needed to flash into the SD card is
   ``allscenarios-image-base-raspberrypi4-64.wic.bz2``.

Flashing All Scenarios OS Linux Image
*************************************

SD card
=======

The Raspberry Pi 4 board support multiple boot options. The below section
describes booting the board with an SD card option.

The following steps describes how to use *etcher* tool flash the image to the
SD Card.

Download the latest *etcher* tool from `balena-io etcher page
<https://github.com/balena-io/etcher/releases>`__.
Unzip and run the application.

   * Click "Flash from file" -> Navigate to
     ``build-ohos-linux-raspberrypi/tmp/deploy/images/$MACHINE/allscenarios-image-base-raspberrypi4-64.wic``

   * Click "Select target" -> Chose the SD Card device name (e.g. ``/dev/sdb``).

   * Click "Flash!"

"Flash Complete!" message on the *etcher* tool shows flashing completed
successfully. Now unplug the SD Card and put it into RPi board.

Testing the board
*****************

Serial Port
===========

By default, the new GPIO UART is disabled. To enable the serial UART, edit
the ``./sources/meta-ohos/flavours/linux/local.conf.sample`` file.

For more details, see `UART configuration
<https://www.raspberrypi.org/documentation/configuration/uart.md>`__.

Add the following line at the end of the file.

.. code-block:: console

   ENABLE_UART = "1"

For more details, see `Optional build configuration
<https://meta-raspberrypi.readthedocs.io/en/latest/extra-build-config.html?highlight=ENABLE_UART#enable-uart>`__.

HDMI
====

Two micro HDMI ports (HDMI-0 and HDMI-1) are enabled by default. Simply
plugging your HDMI-equipped monitor into the RPi4 using a standard HDMI
cable will automatically lead to the Pi using the best resolution
the monitor supports.

For more details, see `HDMI ports and configuration
<https://www.raspberrypi.org/documentation/configuration/hdmi-config.md>`__.

Bluetooth & BLE
===============
By default, BT and BLE are supported.

For any fault in the hardware device, see :ref:`How to handle faulty hardware device <FallbackSupport>`.

Ethernet & WiFi
===============

Drivers for both Ethernet and WiFi is available by default and hence no
specific configuration is needed to enable drivers for these interfaces.

Setting a static of dynamic IP for the interface is implementation and
deployment specific and any network configuration tool can be used to
configure IPv4 or IPv6 address to RPi.

For any fault in the hardware device, see :ref:`How to handle faulty hardware device <FallbackSupport>`.

Audio
=====

To enable the audio over 3.5mm jack, add the following line in
``./sources/meta-ohos/flavours/linux/local.conf.sample``

.. code-block:: console

   RPI_EXTRA_CONFIG = "dtparam=audio=on"

To enable the ``aplay`` support for audio playback, append the following lines:

.. code-block:: console

   IMAGE_INSTALL_append = " gstreamer1.0  gstreamer1.0-meta-base
   gstreamer1.0-plugins-base gstreamer1.0-plugins-good"
   IMAGE_INSTALL_append = " alsa-lib alsa-utils alsa-tools"

To test the audio out on the *3.5mm audio jack*, we need to download the wav
file and play with ``aplay``.

.. code-block:: console

   # wget https://file-examples-com.github.io/uploads/2017/11/file_example_WAV_1MG.wav
   # aplay file_example_WAV_1MG.wav

Connect the headset on *3.5mm audio jack* and you should be able to hear the
audio.

I2C
===

I2C is disabled by default. To enable I2C, edit the
``./sources/meta-ohos/flavours/linux/local.conf.sample`` file with below line:

.. code-block:: console

   ENABLE_I2C = "1"

The device tree does not create the I2C devices. For a quick test, install the
module.

.. code-block:: console

   root@raspberrypi4-64:~# modprobe i2c_dev
   [  611.019250] i2c /dev entries driver

   root@raspberrypi4-64:~# ls -ls /dev/i2c-1
       0 crw-------    1 root     root       89,   1 Mar 29 10:41 /dev/i2c-1

.. note::
   Need to be updated with more options.

GPIO
====

GPIO testing can be done using the sysfs Interface.

The following example shows how to test the GPIO-24 (which corresponds to
physical pin number 18 on the GPIO connector of the Raspberry Pi):

By default, sysfs driver is loaded, you will see the GPIO hardware exposed in
the file system under ``/sys/class/gpio``. It might look something like this:

.. code-block:: console

   root@raspberrypi4-64:/sys/class/gpio# ls /sys/class/gpio/
   export       gpiochip0    gpiochip504  unexport

We'll look at how to use this interface next. Note that the device names
starting with ``gpiochip`` are the GPIO controllers and we won't directly use
them.

To use a GPIO pin from the sysfs interface, perform the following steps:

1) Export the pin.

.. code-block:: console

   # echo 24 >/sys/class/gpio/export

2) Set the pin direction (input or output).

.. code-block:: console

   # echo out >/sys/class/gpio/gpio24/direction

3) If an output pin, set the level to low or high.

To validate the GPIO24 pin value, connect the LED light with the positive line
on pin #18 (GPIO24) and the negative line on pin #20 (Ground).

.. code-block:: console

   # echo 0 >/sys/class/gpio/gpio24/value  # to set it low - LED Turn OFF
   # echo 1 >/sys/class/gpio/gpio24/value  # to set it high - LED Turn ON

4) If an input pin, read the pin's level (low or high).

.. code-block:: console

   # cat /sys/class/gpio/gpio24/value  # 0 is low & 1 is high.

5) When done, unexport the pin.

.. code-block:: console

   # echo 24 >/sys/class/gpio/unexport
