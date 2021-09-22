.. SPDX-FileCopyrightText: Huawei Inc.
..
.. SPDX-License-Identifier: CC-BY-4.0

.. include:: ../../../definitions.rst

Input/Output Peripherals used in blueprints
###########################################

This section lists some of the peripherals used for input and output used as part of |main_project_name| Blueprints.

.. toctree::
   :maxdepth: 3

Touch-based input
*****************
Smart devices accept a variety of input, typically through different types of touch interfaces. Here are a few that are used to produce |main_project_name| Blueprints.

Capacitive Touch Sensor
-----------------------
A capacitive touch sensor is used in various use cases with many different touch finishes, including glass, acrylic, polyester films, etc. It supports multi-touch use cases but needs direct skin contact to work.

Raw Capacitive Touch Sensor
^^^^^^^^^^^^^^^^^^^^^^^^^^^
 - `Raw touch sensor<https://www.adafruit.com/product/4830>`

Glass Smart Switches
^^^^^^^^^^^^^^^^^^^^
For switches connected to a wifi network.

 - `Sonoff <https://www.sonoff.in/product/sonoff-t3eu-tx-smart-wifi-black-wall-touch-switch-with-smart-home-edge-3-gang-433-rf-remote-control-works-with-alexa-ifttt/>`

Resistive Touch Sensor
----------------------
If a pressure-based touch experience is required (to prevent stray touches), or if it is a low-cost, single-touch application, a resistive touch sensor may be used. These sensors also work with gloves and styluses and are generally more rugged and more resistant to water, dust, and debris.

Matrix Keypad
-------------
Usecases such as PIN entry for payment or alarm system require a numeric keypad. Matrix 3x4 or 4x4 keypads are useful in such cases, with a generic gpio keypad driver able to read the input.

 - `Adafruit <https://www.adafruit.com/product/419>`
 - `Wider selection at Adafruit <https://learn.adafruit.com/matrix-keypad/featured_products>`

Finger-print sensor
-------------------
For authentication use cases, a fingerprint module might be required.

- `Adafruit fingerprint sensor <https://www.adafruit.com/product/751>`

Combined display and Touch panel
--------------------------------
For applications needing a custom UI, a touch panel might be used. These can be categorized into two:

Touch Panel (Lite)
^^^^^^^^^^^^^^^^^^

 .. note::
    Needs experimental verification with our boards

 - `Adafruit 2.8" PiTFT for Arduino <https://www.adafruit.com/product/1947>`
 - `Generic SPI display <https://www.buydisplay.com/2-8-inch-tft-touch-shield-for-arduino-w-capacitive-touch-screen-module>`
 - `Monochrome 2.4" OLED Display with touch <https://www.winstar.com.tw/products/oled-module/oled-touch-display/weo012864j-ctp.html>`


Touch Panel (Rich)
^^^^^^^^^^^^^^^^^^

 .. note::
    Needs experimental verification with our boards

 - `Adafruit 2.8" PiTFT for RPi <https://www.adafruit.com/product/1983>`
 - `Generic SPI display <https://www.buydisplay.com/2-8-inch-tft-touch-shield-for-arduino-w-capacitive-touch-screen-module>`
 - `Adafruit 7" touchscreen display for RPi with DSI interface <https://www.adafruit.com/product/2718>`

Display output
**************
Several devices have a display component to reflect the device's status, provide feedback to user input or show other useful information. Depending on the application, you might choose just a display or a display with a touch panel that allows input.

Display-only
------------
A simple SPI display might be used for applications only needing a display with no touch input.

 .. note::
    Needs experimental verification with our boards

 - `Adafruit 2.0" 320x240 Color IPS TFT Display <https://www.adafruit.com/product/4311>`

Combined display and Touch panel
--------------------------------
See `Combined display and Touch panel above`
