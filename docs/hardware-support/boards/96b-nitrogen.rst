.. SPDX-FileCopyrightText: Huawei Inc.
..
.. SPDX-License-Identifier: CC-BY-4.0

.. _SupportedBoardNitrogen:

96Boards Nitrogen
#################

.. contents::
   :depth: 3

Overview
********

Nitrogen, a compliant IoT Edition board provides economical and compact BLE
solutions for various IoT projects. This board includes the below features:

* Nordic nRF52832 microcontroller
* 64 KB of RAM
* 512 KB on-board flash storage.

Nitrogen hardware supports the Nordic Semiconductor nRF52832 ARM Cortex-M4F
CPU.

Hardware
********

* For detailed specifications, see `Nitrogen product page on the 96Boards website <https://www.96boards.org/product/nitrogen/>`_.
* For hardware user manual, see `Seeed wiki <https://wiki.seeedstudio.com/BLE_Nitrogen/>`_.
* For hardware schematics, see `Seeed Document <https://github.com/SeeedDocument/BLE-Nitrogen/tree/master/res>`_.

For more details on 96Boards Nitrogen, see `Nitrogen product page <https://www.96boards.org/product/nitrogen/>`_.

Working with the board
**********************

Supported image
===============

* zephyr-philosophers

Building an application
=======================

OpenHarmony OS Zephyr flavour is based on Zephyr kernel.

* Source the environment with proper template settings, flavour being zephyr and target machine being 96b-nitrogen:

.. code-block:: console

   $ TEMPLATECONF=../sources/meta-ohos/flavours/zephyr . ./sources/poky/oe-init-build-env build-ohos-zephyr

* You will find yourself in the newly created build directory. Call bitbake to build the image. The supported image name is zephyr-philosophers.

.. code-block:: console

   $ MACHINE=96b-nitrogen bitbake zephyr-philosophers

MACHINE variable can be set up in conf/local.conf file under build directory or via command line.


Flashing an application
=======================

Installing pyOCD
----------------

pyOCD is an open source Python package for programming and debugging Arm Cortex-M microcontrollers using multiple supported types of USB debug probes. It is fully cross-platform, with support for Linux.

* The latest stable version of pyOCD can be installed via `pip <https://pip.pypa.io/en/stable/>`_ as follows:

.. code-block:: console

   $ pip install --pre -U pyOCD

* To install the latest pre-release version from the HEAD of the master branch, do the following:

.. code-block:: console

   $ pip install --pre -U git+https://github.com/mbedmicro/pyOCD.git

* To install directly from the source by cloning the git repository, do the following:

.. code-block:: console

   $ python setup.py install

* Verify that the board is detected by pyOCD by executing the command:

.. code-block:: console

   $ pyOCD-flashtool -l

.. note::

   When *ValueError: The device has no langid* error is displayed due to lack of permission, perform the instructions as suggested in https://github.com/pyocd/pyOCD/tree/master/udev.

How to flash
------------

* To flash the image, execute the command used to build the image with -c flash_usb appended.
  For example, to flash the already built zephyr-philosophers image, do:

.. code-block:: console

   $ MACHINE=96b-nitrogen bitbake zephyr-philosophers -c flash_usb
