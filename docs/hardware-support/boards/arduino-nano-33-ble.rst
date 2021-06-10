.. SPDX-FileCopyrightText: Huawei Inc.
..
.. SPDX-License-Identifier: CC-BY-4.0

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

All Scenarios OS Zephyr flavour is based on the Zephyr kernel.

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

How to flash
------------

All Scenarios OS currently supports only the pyOCD and dfu flashing method.
Hence bossac tool is used for flashing Arduino Nano 33 BLE. 

.. note::
   This board requires the Arduino variant of bossac. You will not be able to
   flash with the bossac included with the zephyr-sdk or using shumatech’s
   mainline build.
   You can get this variant of bossac in one of two ways:

   1. Building the binary from the Arduino source tree.

   2. Downloading the Arduino IDE:

     * Install the board support package within the IDE.
     * Change your IDE preferences to provide verbose logging.
     * Build and flash a sample application and read the logs to figure out
       where Arduino stored bossac.

1. Connect the board via USB and find the port connected to the the board via
   dmesg. It would be ``/dev/ttyACMx``.

2. Enable the permissions for board connected port:

  .. code-block:: console
	
	$ sudo usermod -a -G dialout `whoami`
	$ sudo chmod a+rw /dev/ttyACM0

3. Pass the following arguments to bossac which is identified from the preceding step:

  .. code-block:: console
   
   $ bossac -p /dev/ttyACM0 -R -e -w -v -b <path to bin folder>/zephyr-philosophers.bin

4. Due to limitation, once flashed USB CDC does not work as expected and thus 
   board gets disconnected via USB. Connect the board via USB-TTL cable for the
   serial console as shown:

  .. image:: assets/serial-connect.jpg
    :width: 600

5. Once connected, locate the board connected port via dmesg. It would
   be ``/dev/ttyUSBx``.

6. Run your favorite terminal program to listen for output. 

  .. code-block:: console
	
	$ minicom -D /dev/ttyUSB0

  Configure the connection as follows:

  * Baud Rate: 115200
  * Data: 8 bits
  * Parity: None
  * Stop bits: 1

7. Firmware output can be viewed in minicom with:

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
   
