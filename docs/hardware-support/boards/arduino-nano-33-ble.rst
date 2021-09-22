.. SPDX-FileCopyrightText: Huawei Inc.
..
.. SPDX-License-Identifier: CC-BY-4.0

.. include:: ../../definitions.rst

.. _SupportedBoardArduinoNano33BLE:

Arduino Nano 33 BLE
###################

.. contents::
   :depth: 3

Overview
********

The Arduino Nano 33 BLE Sense is Arduino’s 3.3V AI-enabled board in the smallest
available form factor: 45x18mm! 

The Arduino Nano 33 BLE Sense is an entirely new board on a well-known form
factor. It comes with a series of embedded sensors:

* 9 axis inertial sensor: This makes this board ideal for wearable devices.
* Humidity and temperature sensor: To get highly accurate measurements of
  the environmental conditions.
* Barometric sensor: Make a simple weather station.
* Microphone: To capture and analyze sound in real-time.
* Gesture, proximity, light color, and light intensity sensor: Estimate 
  the room’s luminosity, but also whether someone is moving close to the board.

Arduino Nano 33 BLE hardware supports the Nordic Semiconductor nRF52840 ARM
Cortex-M4 CPU running at 64 MHz.

Hardware
********

* For detailed specifications, see Arduino Nano 33 BLE product page on the `Arduino website <https://store.arduino.cc/usa/nano-33-ble-sense>`_.
* For product specification and Datasheet, see `Arduino page <https://content.arduino.cc/assets/Nano_BLE_MCU-nRF52840_PS_v1.1.pdf>`_.
* For hardware schematics, see `Arduino <https://content.arduino.cc/assets/NANO33BLE_V2.0_sch.pdf>`_.

Working with the board
**********************

Building an application
=======================

|main_project_name| Zephyr flavour is based on the Zephyr kernel.

1. Source the environment with proper template settings, the flavour being *zephyr* 
   and target machine being *arduino-nano-33-ble*:

  .. code-block:: console

   $ TEMPLATECONF=../sources/meta-ohos/flavours/zephyr . ./sources/poky/oe-init-build-env build-ohos-zephyr

2. You will find yourself in the newly created build directory. Call *bitbake* 
   to build the image. The supported image name is *zephyr-philosophers*.

  .. code-block:: console

   $ MACHINE=arduino-nano-33-ble bitbake zephyr-philosophers

You can set-up MACHINE variable in ``conf/local.conf`` file under the build
directory, or via the command line.

Alternatively you might want to build the Arduino's blinking LED
sample application, *blinky*. In order to do so issue the following:

  .. code-block:: console

   $ MACHINE=arduino-nano-33-ble bitbake zephyr-blinky

3. After the build completes, the ``zephyr-philosophers.bin`` and the ``zephyr-blinky.bin``
   file can be found in ``build-ohos-zephyr/tmp-newlib/deploy/images/arduino-nano-33-ble/``.

Flashing an application
=======================
Install bossac tool required to flash Arduino Nano 33 BLE.

.. note::
   You will not be able to flash with the bossac included with the zephyr-sdk or using shumatech’s
   mainline build.

Installing bossac
-----------------

You can install the Arduino variant of bossac in one of two ways:

**Option 1:** Build the binary from the Arduino source tree.

**Option 2:** Download the Arduino IDE:
     1. Install the board support package within the IDE.
     2. Change your IDE preferences to provide verbose logging.
     3. Build and flash a sample application and read the logs to figure out
        where Arduino stored bossac.
	   
After successful installation, add the bossac installed path to the PATH
variable. It is important for the bitbake to find the correct bossac installed path
to flash.

.. code-block:: console
 
   $ export PATH=<bossac_path>:$PATH

How to flash
------------

1. Enable the permissions for board connected port:

  .. code-block:: console
	
	$ sudo usermod -a -G dialout `whoami`
	$ sudo chmod a+rw /dev/ttyACM0

2. To flash the image, execute the command used to build the image with 
   -c flash_usb appended. For example, to flash the already built 
   zephyr-philosophers image, execute:

  .. code-block:: console
   
   $ MACHINE=arduino-nano-33-ble bitbake zephyr-philosophers -c flash_usb

3. Run your favorite terminal program to listen for output. 

  .. code-block:: console
	
	$ minicom -D /dev/ttyACM0

  Configure the connection as follows:

  * Baud Rate: 115200
  * Data: 8 bits
  * Parity: None
  * Stop bits: 1

.. note::
   If no output is displayed, set the permissions again as mentioned in
   Step-1 of of this section.

4. Firmware output can be viewed in minicom with:

  ::

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