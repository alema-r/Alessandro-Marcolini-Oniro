.. SPDX-FileCopyrightText: Huawei Inc.
..
.. SPDX-License-Identifier: CC-BY-4.0

DoorLock Blueprint
##################

.. contents::
   :depth: 4

Overview
********

The DoorLock Blueprint provides support for building a PoC smart door lock to demonstrate:
#. Keypad input to operate the lock locally
#. Secure wireless communication to operate the lock locally
#. Secure communication with the lock remotely
#. Secure OTA

Get sources
***********
#. 
   Get All Scenarios OS sources as described in the :ref:`documentation <AllScenariOSQuickBuild>`.

#. 
   If you already have sources cloned, update them to the most recent revision

   .. code-block:: bash

      user@pc:~/ohos$ repo sync -d
