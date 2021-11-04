.. SPDX-FileCopyrightText: Huawei Inc.
..
.. SPDX-License-Identifier: CC-BY-4.0

.. include:: ../definitions.rst

##################
DoorLock Blueprint
##################

.. contents::
   :depth: 4

.. attention::
   The `Door Lock` blueprint is still a work in progress (WIP). For more
   information, see the :ref:`resources <ResourcesDL>` section.

********
Overview
********

The DoorLock Blueprint provides support for building a PoC smart door lock to demonstrate:

* Operating several types of locks
* Keypad input to operate the lock locally
* Secure wireless communication to operate the lock locally (TBD)
* Secure communication with the lock remotely (TBD)
* Secure OTA (TBD)

************
The Hardware
************

Needed components
=================

Common to all variants
----------------------
* A breadboard (e.g. http://adafru.it/239)
* Some breadboarding wires (e.g. http://adafru.it/153)
* Arduino Nano 33 BLE Sense board with headers (e.g. https://store.arduino.cc/arduino-nano-33-ble-sense-with-headers)

Lock Variant 1: Using a lock-style solenoid
-------------------------------------------
* A lock-style solenoid (e.g. http://adafru.it/1512)
* DC barrel jack (e.g. http://adafru.it/373)
* Power supply matching the DC barrel jack (e.g. http://adafru.it/1448)
* Possibly plug adapter for the power supply (e.g. http://adafru.it/990)
* DRV8871 motor driver breakout (e.g. http://adafru.it/3190)
* Solder iron, some solder

Lock Variant 2: Using a rotating motor
--------------------------------------
* L9110 H-Bridge (e.g. http://adafru.it/4489)
* Rotating door lock motor (e.g. http://adafru.it/3881)
* Solder iron, some solder

Control Variant 1: Number keypad
--------------------------------
* Number keypad (e.g. http://adafru.it/419)

Control Variant 2: Touch sensors (TBD)
--------------------------------------
* Touch sensors (e.g. http://adafru.it/4830)
* STEMMA QT/QWIIC connector (e.g. http://adafru.it/4209)

Control Variant 3: Fingerprint sensor (TBD)
-------------------------------------------
* Fingerprint sensor (e.g. http://adafru.it/751)

Wiring up the breadboard
========================
There are multiple ways to wire the breadboard. Locations of various components don't matter as long as the connections between the components are correct. We'll give an example that will get you going even if you don't know anything about electronics.
When talking about holes on the breadboard, we're assuming the breadboard is labeled like http://adafru.it/239 - A to J is left to right, 1 to 63 is top to bottom. Some breadboards are labeled differently.

Common to all variants
----------------------
#.
 Insert the Arduino Nano 33 BLE Sense to the breadboard, USB port up, pin 1 (top left) in hole C1.
#.
 Connect the Arduino's power output to the breadboard: +3.3V (pin 2, hole B2) to the left hand + strip (hole +1 on the left), GND (pin 14, hole B14) to the left hand - strip (hole -1 on the left). It is customary (but not required) to use a red wire for + and a black wire for -.
#.
 Connect the left hand + strip to the right hand + strip (hole +61 on the left to +61 on the right, -61 on the left to -61 on the right). Again it is customary to use a red wire for + and a black wire for -.

Lock Variant 1: Using a lock-style solenoid
-------------------------------------------
#.
 Add the DC power barrel to the breadboard. Pin 1 (the one on the opposite side of the plug) goes to hole H60, pin 2 (under the plug) to H63, pin 3 (on the side) to J61. Make sure the power supply is set up for center positive.
#.
 Prepare the DRV8871 breakout board. This requires some soldering.
 Cut the header strip to 4 pins (usually the board comes with a 6 pin strip).

 Solder the 4 pins (short side up, inserted from the bottom side) to the 4 holes in the breakout board labeled "IN2", "IN1", "UM" and "GND".

 Insert the 2 terminal blocks from the top and solder them to the board from the bottom.

 More detailed instructions, including some pictures, can be found at https://cdn-learn.adafruit.com/downloads/pdf/adafruit-drv8871-brushed-dc-motor-driver-breakout.pdf
#.
 Connect the DRV8871 breakout board. The terminal blocks go to the right, the pins you soldered on go to holes E25, E26, E27 and E28.
#.
 Connect the DC barrel jack to the DRV8871 board. Pin 1 (hole F60) to the VM pin on the DRV8871 board (hole C27), and pin 2 (hole F63) to the GND pin on the DRV8871 board (hole C28).
#.
 Connect the DRV8871's IN1 port to the Arduino's digital pin D6: Hole B26 to I7.
#.
 Connect the DRV8871's IN2 port to the Arduino's digital pin D7: Hole B25 to I6.
#.
 Connect the lock-style solenoid to the DRV8871's MOTOR terminal block (the one facing the Arduino). The wire facing the Arduino board goes to the blue wire on the solenoid, the wire closer to the power barrel goes to the red wire on the solenoid.
#.
 Note that the Arduino board is not connected to the barrel power plug. You need to power the lock with the barrel power plug and the Arduino board with its USB port, at the same time.

Lock Variant 2: Using a rotating motor
--------------------------------------
#.
 Connect the L9110H driver to the breadboard. The side with the notch faces the Arduino board, pins go into holes E22 to E25 and F22 to F25.
#.
 Connect the power supply to the L9110H: The VCC pins (pins 2 and 3 on the L9110H, holes D23 and D24) must be connected to the + terminal (e.g. hole D23 to +23 and D24 to +24). The GND pins (pins 5 and 8 on the L9110H, holes G22 and G25) must be connected to the - terminal (e.g. hole G22 to -22 and G25 to -25).
#.
 Connect the L9110H's control ports (IA and IB, pins 6 and 7) to the Arduino's digital outputs D6 and D7 (hole H23 to J6, H24 to J7).
#.
 Connect the L9110H's output pins (OA and OB, pins 1 and 4) to the lock motor. The easiest way is to solder a breadboard wire to the motor's wires. Connect the black wire to hole A22, and the red wire to hole A25.

Control Variant 1: Number keypad
--------------------------------
#.
 Connect the 7 pins of the keypad to the Arduino's digital pins 2, 3, 4, 5, 8, 9 and 10. From left to right:

 * Pin 1 to D9 (hole H4)
 * Pin 2 to D2 (hole H11)
 * Pin 3 to D8 (hole H5)
 * Pin 4 to D5 (hole H8)
 * Pin 5 to D10 (hole H3)
 * Pin 6 to D4 (hole H9)
 * Pin 7 to D3 (hole H10)

************
The Software
************

Get sources
===========
#.
 Get |main_project_name| sources as described in the :ref:`documentation <OniroQuickBuild>`.
#.
 If you already have sources cloned, update them to the most recent revision

.. code-block:: bash

 user@pc:~/oniro$ repo sync -d

Resources
=========

.. _ResourcesDL:

- See `Door lock's requirement <https://git.ostc-eu.org/OSTC/requirements/-/issues/12>`_

*******************
Using the door lock
*******************

Initial setup
=============
After initial flashing, you get to select the pin that will unlock the lock.
The red LED on the Arduino board flashes quickly while waiting for your new pin.

Setting a pin
=============
You can use any combination of keys, including pressing multiple keys simultaneously.
Enter the pin you would like to use, and finish by holding the "*" key and pressing "#".
For example, if you want to use pin 1 23 4 (where 3 has to be pressed while 2 is still being held down), press and release 1, press and hold 2, press 3, release 2 and 3, press and release 4, press and hold * , press #, release * and #.

Driving the lock
================
Simply enter your pin using the matrix keypad to unlock the lock.

Changing the pin
================
Within 10 seconds of entering your pin to unlock the lock, hold the "*" key and press "#".
At this point, the red LED on the board will flash quickly, and you can enter a new pin
as described in "Setting a pin".

Forgotten pin
=============
There is no "master key". If you've forgotten your pin, flash the separate `factory reset app <https://booting.oniroproject.org/distro/blueprints/doorlock/doorlock-factoryreset>`_, boot it, then reflash the doorlock app.
