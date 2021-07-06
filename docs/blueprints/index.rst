.. SPDX-FileCopyrightText: Huawei Inc.
..
.. SPDX-License-Identifier: CC-BY-4.0

All Scenarios OS Blueprints
###########################

Overview
********

All Scenarios OS integrates its various components into a representative use-case called a `Blueprint`. A blueprint shows off the OS capabilities and best practices in building software-based products.

To this end, blueprints are a way to distill real-world products into a ``minimum viable product`` to demonstrate how partners and users may adopt All Scenarios OS securely in their own products.

What is a Blueprint?
--------------------
#. It shows off a key feature or two of the product it is trying to emulate.
#. It focuses on reproducing a representative user interaction for a use case.

   .. note::
      Examples include:
      * A secure network link, e.g., a TLS connection to avoid transmitting plain-text data.
      * Wireless communication, e.g., via Bluetooth, OpenThread or NFC.
      * Touch input, e.g., via a keypad or touchscreen.
      * Display, e.g., showing useful messages on a display.
      * Autonomous communication between two devices over a wireless link.

#. It is implemented on the closest appropriate reference HW platform from All Scenarios OS.

What a Blueprint Isn't?
-----------------------
#. It doesn't try to implement every feature of a contemporary product category in the market.
#. It isn't optimized for cost, size, or physical looks.
#. It doesn't try to replicate the physical form-factor of a contemporary product category. Do not expect to see everything hidden away in a pretty enclosure. Expect to see boards and wires connecting to peripherals.

Blueprints
**********

This section details the available blueprints provided as part of the All Scenarios OS environment.

.. toctree::
   :maxdepth: 1

   smart-panel
   doorlock
   touchpanel
   transparent-gateway
   vending-machine
