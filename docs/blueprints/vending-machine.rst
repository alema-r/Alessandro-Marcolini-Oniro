.. SPDX-FileCopyrightText: Huawei Inc.
..
.. SPDX-License-Identifier: CC-BY-4.0

.. include:: ../definitions.rst

Vending Machine Blueprint
#########################
   
.. image:: assets/vending-machine-logo.jpg
   :alt: Vending Machine Blueprint
   :height: 100px

.. contents::
   :depth: 4

Overview
********

The Vending Machine Blueprint provides support for building a PoC smart vending
machine with components showing the capabilities of the |main_project_name|
build infrastructure to easily build an end-to-end solution using multiple
operating systems cooperating inside a product.

Hardware Setup - Bill of Materials
**********************************

The Vending Machine blueprint provides full support for two complete setups.
One based on Raspberry Pi 4B, an ARMv8 target, and one based on SECO B68, an X86
board. Regardless of the target board chosen, you will need additional
components, connectors, and power supply. Besides the target board, the other
main differences in terms of hardware are the power supply and the HDMI
adapter. The latter is needed because the boards have different video input
connectors (miniDP vs micro-HDMI) while the display comes with a standard HDMI
input connector and cable. Please find below the full bill of materials for the
two supported setups:

Raspberry Pi 4B-based setup BoM
-------------------------------

- 1 x `Pimoroni 5x5 RGB Matrix Breakout
  <https://shop.pimoroni.com/products/5x5-rgb-matrix-breakout>`_ 
- 1 x `7inch HDMI LCD Display, Waveshare, Rev3.1 or Rev2.1
  <https://www.waveshare.com/wiki/7inch_HDMI_LCD_(C)>`_ (comes with USB and
  HDMI cable)
- multi-coloured DuPont wires
- 1 x microSD card (>=8GB size)
- 1 x `Raspberry Pi 4B board
  <https://www.raspberrypi.com/products/raspberry-pi-4-model-b/>`_ (>=2GB RAM)
- 1 x HDMI to micro-HDMI adapter
- 1 x `Raspberry Pi USB-C Power supply
  <https://www.raspberrypi.com/products/type-c-power-supply/>`_

.. image:: assets/vending-machine-rpi-bom.jpg
   :alt: Vending Machine - Raspberry Pi 4B BoM

SECO B68-based setup BoM
------------------------

- 1 x `Pimoroni 5x5 RGB Matrix Breakout
  <https://shop.pimoroni.com/products/5x5-rgb-matrix-breakout>`_
- 1 x `7inch HDMI LCD Display, Waveshare, Rev3.1 or Rev2.1
  <https://www.waveshare.com/wiki/7inch_HDMI_LCD_(C)>`_ (comes with USB and
  HDMI cable)
- multi-coloured DuPont wires
- 1 x microSD card (>=8GB size)
- 1 x `SECO B68 board <https://edge.seco.com/en/sbc-b68-enuc.html>`_
- 1 x HDMI to miniDP adapter
- 1 x 19V power supply - depending on the board variant, you might need a
  barrel or an industrial screw connector

.. image:: assets/vending-machine-b68-bom.jpg
   :alt: Vending Machine - SECO B68 BoM

Building the Blueprint Hardware Setup
*************************************

Once we have all the required parts for this blueprint, we can proceed to build
it. Due to differences in connectors and power, there will be a set of
differences between the two setups but in general, the steps are:

- connect the display's HDMI input to the board through the appropriate HDMI
  adapter
- connect the display's USB cable to the USB connector on the board
- connect the RGB Matrix to the right I2C pins on the board using dupont wires

.. note::

   Note that the RGB Matrix does not come with a soldered pin header so some
   soldering work will be required.

   .. image:: assets/vending-machine-matrix.jpg
      :alt: Vending Machine - RGB Matrix soldered and connected

Raspberry Pi 4B-based Hardware Setup
------------------------------------

.. image:: assets/vending-machine-rpi-hw-setup.jpg
   :alt: Vending Machine - Raspberry Pi 4B hardware setup

When building a Raspberry Pi 4B-based hardware setup you will need to:

- connect the display's HDMI input through a micro-HDMI adapter to the
  connector labeled `HDMI0` on the Raspberry Pi 4B board (see the image above)
- connect the RGB LED Matrix to the Raspberry Pi 4B I2C interface:

.. image:: assets/vending-machine-rpi-i2c.png
   :alt: Vending Machine - Raspberry Pi 4B I2C connection

SECO B68-based Hardware Setup
-----------------------------------

.. image:: assets/vending-machine-b68-hw-setup.jpg
   :alt: Vending Machine - SECO B68 hardware setup

When building a SECO B68-based hardware setup you will need to:

- connect the display's HDMI input through a miniDP adapter to the connector
  labeled `CN20` on the SECO B68 board (see the image above)
- connect the RGB LED Matrix to the SECO B68 I2C 4 port on the `CN19`
  connector:

.. image:: assets/vending-machine-b68-i2c.png
   :alt: Vending Machine - SECO B68 I2C connection

The `CN20` connector on the board can be seen here:

.. image:: assets/vending-machine-b68-i2c-board.jpg
   :alt: Vending Machine - SECO B68 I2C connection

Fetch Blueprint Sources and Build
*********************************

Fetch the Build Metadata
------------------------

The final piece remaining after building the hardware setup is fetching the
software sources and building the OS.

For fetching the build metadata, follow the :ref:`documentation
<RepoWorkspace>`. This will bring everything you need on your host to start
building an image for this blueprint.

Build the Oniro Image for the Vending Machine Blueprint
-------------------------------------------------------

Once you have a workspace initialised as per the instructions above, you are
ready to build the OS. Firstly, you will be initialising a build:

.. code-block:: bash

   $ TEMPLATECONF=../oniro/flavours/linux . ./oe-core/oe-init-build-env build-oniro-vending-machine

This will setup a new build environment in the `build-oniro-vending-machine`
directory (or reuse it if it already exists).

Once the build environment is set we can start building the image. The building
command will be slightly different depending on the target board.

For the Raspberry Pi 4B-based setup:

.. code-block:: bash

   $ DISTRO=oniro-linux-blueprint-vending-machine MACHINE=raspberrypi4-64 bitbake blueprint-vending-machine-image

For the SECO b68-based setup:

.. code-block:: bash

   $ DISTRO=oniro-linux-blueprint-vending-machine MACHINE=seco-intel-b68 bitbake blueprint-vending-machine-image

Once the build is finished, the images are available for being written on a
microSD card.

Flashing the Blueprint Image
----------------------------

First of all, make sure you have plugged in an microSD card via an microSD card
reader attached to your host. Once that is done, note its associated device
node. You can find that using `udev`, `dmesg` or various other tools. Once the
device not associated to the microSD card is known, proceed to flash the built
image.

.. warning::

      The commands below assume that the device node associated with the
      microSD card is provided via the `DEVICE` environment variable. Make sure
      you have the correct one set before running the commands below to avoid
      risking data loss.

For the Raspberry Pi 4B-based setup:

.. code-block:: bash

   $ sudo bmaptool copy ./tmp/deploy/images/seco-intel-b68/blueprint-vending-machine-image-seco-intel-b68.wic "$DEVICE"

For the SECO b68-based setup:

.. code-block:: bash

   $ sudo bmaptool copy ./tmp/deploy/images/raspberrypi4-64/blueprint-vending-machine-image-raspberrypi4-64.wic.bz2 "$DEVICE"

Running the Vending Machine Blueprint Image
*******************************************

At this point, we have all the pieces we need to run the entire blueprint.
Plug in the microSD card on which you flashed the OS into the board's microSD
card slot. That brings us to the last step: booting the board by the attaching
power source. On a Raspberry Pi 4B, that would be a USB-C power supply while on
SECO B68, it would be a 19V power supply connected to the power barrel or the
industrial screw connector (depending on the board's variant).

Once the boot process completes, the vending machine UI application will take
over the screen and be ready to interact with the RGB LED matrix for item
selection and delivery simulation.

.. image:: assets/vending-machine-b68-boot.jpg
   :alt: Vending Machine - SECO B68 up and running

Architecture and Interfaces
***************************

.. toctree::
   :maxdepth: 3

   vending-machine-dev.rst

Resources
*********

.. _ResourcesVM:

- See `Vending machine's requirement <https://git.ostc-eu.org/OSTC/requirements/-/issues/80>`_
