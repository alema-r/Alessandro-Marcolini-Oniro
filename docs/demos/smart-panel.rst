.. SPDX-FileCopyrightText: Huawei Inc.
..
.. SPDX-License-Identifier: CC-BY-4.0

Smart Panel Demo
################

.. contents:: 
   :depth: 3

Overview
********

The Smart Panel Demo provides support for building a PoC for a home automation
system with components showing the capabilities of the build infrastructure in
leveraging different kernels for building an end to end solution.

The setup is composed of an Avenger96 board acting as a gateway and running
HomeAssistant. The gateway also provides UI as a browser connected to the
localhost HomeAssistant server. The gateway is connected over Bluetooth to two
Nitrogen boards exposing sensors and/or emulating device (e.g. light bulbs).

How to build
************

The Linux Gateway
-----------------

1. Refer to :ref:`Building OHOS image for Avenger96 <SupportedBoardAvenger96>` to learn how to set up a build environment, build and flash a base OpenHarmony Linux image.
2. Build demo image by invoking following bitbake command

.. code-block:: console

   $ MACHINE=stm32mp1-av96 DISTRO=openharmony-linux-demo-dashboard bitbake demo-dashboard-gateway-image

