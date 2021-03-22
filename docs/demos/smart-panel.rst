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

The setup is composed of an Avenger96 board acting as a gateway an running
HomeAssistant. The gateway also provides UI as a browser connected to the
localhost HomeAssistant server. The gateway is connected over Bluetooth to two
Nitrogen boards exposing sensors and/or emulating device (eg. light bulbs).

How to build
************

The Linux Gateway
-----------------

Set the ``DISTRO`` to ``openharmony-linux-demo-dashboard`` and build the
``openharmony-linux-demo-dashboard`` image.
