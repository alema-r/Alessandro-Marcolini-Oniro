.. SPDX-FileCopyrightText: Huawei Inc.
..
.. SPDX-License-Identifier: CC-BY-4.0

Smart Panel Blueprint
#####################

.. contents:: 
   :depth: 3

Overview
********

The Smart Panel Blueprint provides support for building a PoC for a home
automation system with components showing the capabilities of the build
infrastructure in leveraging different kernels for building an end to end
solution.

The setup is composed of an Avenger96 board acting as a gateway and running
HomeAssistant. The gateway also provides UI as a browser connected to the
localhost HomeAssistant server. The gateway is connected over Bluetooth to two
Nitrogen boards exposing sensors and/or emulating device (e.g. light bulbs).

.. image:: assets/smart-home-blueprint-arch.png

How to build
************

The Linux Gateway
-----------------

1. Refer to :ref:`Building OHOS image for Avenger96 <SupportedBoardAvenger96>` to learn how to set up a build environment, build and flash a base AllScenariOS Linux image.
2. Build blueprint image by invoking following bitbake command

.. code-block:: console

   $ MACHINE=stm32mp1-av96 DISTRO=openharmony-linux-blueprint-dashboard bitbake blueprint-dashboard-gateway-image


IOT Endpoints
-------------
1. Connect Sensors Mezzanine adapter to each Nitrogen board. Connect I/O devices
   according to SmartHome Blueprint app `README <https://git.ostc-eu.org/OSTC/OHOS/components/smart_home_demo_zephyr/-/blob/develop/README.md#connections>`_
2. Refer to :ref:`Working with the board <SupportedBoardNitrogen>` to learn how to set up a build environment, build and flash a sample Zephyr application.
3. Connect target board to the PC using USB connector.
4. Build and flash blueprint image by invoking one of the following bitbake commands (for each IOT endpoint type respectively)

.. note:: In order to connect multiple and flash selected boards, please refer to
          `meta-zephyr README <http://git.yoctoproject.org/cgit/cgit.cgi/meta-zephyr/plain/README.txt>`_
          on how to use PYOCD_FLASH_IDS environment variable.

**For sensors board:**

* Connected I/O: LCD, DHT11, AK9753

.. code-block:: console

   $ MACHINE=96b-nitrogen DISTRO=openharmony-zephyr bitbake zephyr-blueprint-smarthome-sensors -c flash_usb

**For bulb/LED board:**

* Connected I/O: LED

.. code-block:: console

   $ MACHINE=96b-nitrogen DISTRO=openharmony-zephyr bitbake zephyr-blueprint-smarthome-led -c flash_usb

