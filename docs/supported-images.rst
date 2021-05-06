.. SPDX-FileCopyrightText: Huawei Inc.
..
.. SPDX-License-Identifier: CC-BY-4.0

Supported Images
################

To create a custom Linux distribution to match the product requirements, 
All Scenarios OS includes a set of predefined images for developing a product image.

Linux Kernel
************

The Linux kernel is a free and open-source Unix-like operating system (OS) 
kernel that serves as the primary interface between the computer's hardware and its processes.

All Scenarios OS supports the following images listed in the table:

.. list-table:: Linux supported images
   :header-rows: 1

   * - Image Name
     - Description
   * - allscenarios-image-base
     - 
	   * All Scenarios OS image including the base OS software stack.
	   * This image also includes middleware and application packages to support a wide range of hardware which includes WiFi, Bluetooth, sound, and serial ports. 
   * - allscenarios-image-extra
     - 
	   * All Scenarios OS Wayland image including the base OS software stack. This is a Wayland protocol and Weston reference compositor-based image.
	   * It uses the Wayland protocol and implementation to exchange data with its clients.
	   * This image provides the Wayland protocol libraries and the reference Weston compositor and includes a Wayland-capable terminal program.

To build a Linux-based image for a supported machine, see :ref:`Linux Kernel Build Flavour <Linux_Kernel>`.

Zephyr Kernel
*************

The Zephyr OS is a well-known security-oriented real-time operating system (RTOS) 
that is intended for use on resource-constrained and embedded systems. 

For more detailed information on Zephyr OS Kernel, see `Zephyr documentation <https://docs.zephyrproject.org/latest/index.html#>`_.

All Scenarios OS supports the following images for the Zephyr OS kernel:

.. list-table:: Zephyr supported images
   :header-rows: 1

   * - Image Name
     - Description
   * - zephyr-philosophers
     - A sample Zephyr application implementing the Dining Philosophers problem.

To build a Zephyr-based image for a supported machine, see :ref:`Zephyr Kernel Build Flavour <Zephyr_Kernel>`.

FreeRTOS Kernel
***************

The FreeRTOS kernel is a real-time operating system (RTOS) that runs on a variety 
of platforms which is used to build microcontroller-based embedded applications. 

The standard RTOS kernel binary image ranges from 4000 to 9000 bytes. 
All Scenarios OS supports the following images for FreeRTOS Kernel:

.. list-table:: FreeRTOS supported images
   :header-rows: 1

   * - Image Name
     - Description
   * - freertos-demo
     - Machine configuration for running an ARMv5 system on QEMU.

To build a FreeRTOS-based image for a supported machine, see :ref:`FreeRTOS Kernel Build Flavour <FreeRTOS_Kernel>`.
