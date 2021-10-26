.. SPDX-FileCopyrightText: Huawei Inc.
..
.. SPDX-License-Identifier: CC-BY-4.0

.. include:: ../definitions.rst

Transparent Gateway Blueprint
#############################

.. contents::
   :depth: 4

.. attention::
   The `Transparent Gateway` blueprint is a still work in progress (WIP). For
   more information, see the :ref:`resources <ResourcesTG>` section.

Overview
********

The Transparent Gateway Blueprint provides support for building a PoC gateway that can be the communication hub in a smart home. Some of the features it demonstrates are:

#. Ability to participate in an OpenThread network
#. Automatic IPv6-to-IPv4 translation between devices and rest of the world
#. WiFi AP functionality
#. Secure OTA


Network Subnets and Configuration
*********************************

Depending on the used board and hardware configuration, the available network interfaces, and their names, can vary. For the sake of this blueprint, we assume the following interfaces being available::

   Ethernet interface eth0: assumed to be uplink with DHCP enabled
   WiFi interface wlan0: WiFi access point interface serving the WiFi subnet
   OpenThread interface wpan0: OpenThread Border Router interface serving the mesh network

In terms of IP subnets, we are using the private 172.16.47.0/24 range on the WiFi subnet. The AP itself has 172.16.47.1/24 assigned. Clients are being served DHCP leases in the range 172.16.47.100 - 172.16.47.150. The default DNS servers are 9.9.9.9 as primary and 8.8.8.8 as secondary. For IPv6, we rely on address auto-configuration for the time being.

On the OpenThread mesh network subnet, no IPv4 is available, and again we rely on address auto-configuration for the time being.

Forwarding for IPv4 and IPv6 is enabled on all interfaces with sysctl.

WiFi Access Point Configuration
*******************************

In our default WiFi access point configuration, we create an AP on channel 6 in the 2.4 GHz band with WPA2 pre-shared key configuration::

   SSID: "|main_project_name| WiFi"
   Passphrase: "12345678".

For more details, the used `hostapd configuration file <https://booting.oniroproject.org/distro/oniro/-/blob/dunfell/meta-oniro-blueprints/recipes-connectivity/hostapd/files/hostapd.conf>`_ is the best reference.

OpenThread Border Router Configuration
**************************************

In our default OpenThread Border Router configuration, we create an OpenThread mesh network on channel 26 in the 2.4 GHz band with panid 0x1357::

   Networkname "ASOS Thread"
   OpenThread masterkey: 00112233445566778899aabbccddeeff

For more details, the used `OpenThread configuration script <https://booting.oniroproject.org/distro/oniro/-/blob/dunfell/meta-oniro-blueprints/recipes-connectivity/openthread/ot-br-posix/otbr-configuration>`_ is the best reference.

Get sources
***********

#. Get |main_project_name| sources as described in the :ref:`documentation <OniroQuickBuild>`.
#. If you already have sources cloned, update them to the most recent revision.

   .. code-block:: bash

      $ repo sync -d

#. To build the image for this blueprint, you need to set the corresponding distro in the conf/local.conf file or on the command-line as seen below.

   .. code-block:: bash

      $ DISTRO="oniro-linux-blueprint-gateway" MACHINE=raspberrypi4-64 bitbake blueprint-gateway-image

Resources
*********

.. _ResourcesTG:

- See `Transparent gateway's requirement <https://git.ostc-eu.org/OSTC/requirements/-/issues/13>`_
