.. SPDX-FileCopyrightText: Huawei Inc.
..
.. SPDX-License-Identifier: CC-BY-4.0

Transparent Gateway Blueprint
#############################

.. contents::
   :depth: 4

Overview
********

The Transparent Gateway Blueprint provides support for building a PoC gateway that can be the communication hub in a smart home. Some of the features it demonstrates are:
#. Ability to participate in an OpenThread network
#. Automatic IPv6-to-IPv4 translation between devices and rest of the world
#. Wifi AP functionality
#. Secure OTA

Get sources
***********
#. 
   Get All Scenarios OS sources as described in the :ref:`documentation <AllScenariOSQuickBuild>`.

#. 
   If you already have sources cloned, update them to the most recent revision.

   .. code-block:: bash

      user@pc:~/ohos$ repo sync -d
