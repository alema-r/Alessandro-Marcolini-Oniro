.. SPDX-FileCopyrightText: Huawei Inc.
..
.. SPDX-License-Identifier: CC-BY-4.0

##################
DoorLock Blueprint
##################

.. contents::


********
Overview
********

The DoorLock Blueprint provides support for building a PoC smart door lock to demonstrate:

* Operating several types of locks
* Keypad input to operate the lock locally (TBD)
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

Control Variant 1: Number keypad (TBD)
--------------------------------------
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
 Add the DC power barrel to the breadboard. Pin 1 (the one on the opposite side of the plug) goes to hole H60, pin 2 (under the plug) to H63, pin 3 (on the side) to J61. Make sure the power supply is set up for center positive.¨
#.
 Prepare the DRV8871 breakout board. This requires some soldering.
 Cut the header strip to 4 pins (usually the board comes with a 6 pin strip).

 Solder the 4 pins (short side up, inserted from the bottom side) to the 4 holes in the breakout board labeled "IN2", "IN1", "UM" and "GND".

 Insert the 2 terminal blocks from the top and solder them to the board from the bottom.

 More detailed instructions, including some pictures, can be found at https://cdn-learn.adafruit.com/downloads/pdf/adafruit-drv8871-brushed-dc-motor-driver-breakout.pdf
#.
 Connect the DRV8871 breakout board. The terminal blocks go to the right, the pins you soldered on go to holes E25, E26, E27 and E28.
#.
 Connect the lock's power supply to the DRV8871 board. Hole F60 to C27, and F63 to C28.
#.
 Connect the DRV8871's IN1 port to the Arduino's digital pin 2: Hole B26 to I11.
#.
 Connect the DRV8871's IN2 port to the Arduino's digital pin 3: Hole B25 to I10.
#.
 Connect the lock-style solenoid to the DRV8871's MOTOR terminal block (the upper one).
#.
 Note that the Arduino board is not connected to the barrel power plug. You need to power the lock with the barrel power plug and the Arduino board with its USB port, at the same time.

Lock Variant 2: Using a rotating motor
--------------------------------------
#.
 Connect the L9110H driver to the breadboard. The side with the notch faces the Arduino board, pins go into holes E22 to E25 and F22 to F25.
#.
 Connect the power supply to the L9110H: The + terminal (Hole +23) to hole D23 and hole +24 to D24. The - terminal (hole -22) to hole G22 and hole -25 to hole G25.
#.
 Connect the L9110H's control ports to the Arduino's digital outputs 6 and 7 (hole H23 to J6, H24 to J7).
#.
 Connect the L9110H's output pins to the lock motor. The easiest way is to solder a breadboard wire to the motor's wires. Connect the black wire to hole A22, and the red wire to hole A25.

Control Variant 1: Number keypad (TBD)
--------------------------------------
#.
 Connect the 7 pins of the keypad to the Arduino's digital pins 2, 3, 4, 5, 8, 9 and 10. From left to right:

 * Pin 1 to hole H4
 * Pin 2 to hole H11
 * Pin 3 to hole H5
 * Pin 4 to hole H8
 * Pin 5 to hole H3
 * Pin 6 to hole H9
 * Pin 7 to hole H10

************
The Software
************

Get sources
===========
#.
 Get All Scenarios OS sources as described in the :ref:`documentation <AllScenariOSQuickBuild>`.
#.
 If you already have sources cloned, update them to the most recent revision

.. code-block:: bash

 user@pc:~/ohos$ repo sync -d
