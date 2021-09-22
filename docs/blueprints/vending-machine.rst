.. SPDX-FileCopyrightText: Huawei Inc.
..
.. SPDX-License-Identifier: CC-BY-4.0

.. include:: ../definitions.rst

Vending Machine Blueprint
#########################

.. contents::
   :depth: 4

Overview
********

The Vending Machine Blueprint provides support for building a PoC smart vending machine
with components showing the capabilities of the |main_project_name| build infrastructure to easily
build an end-to-end solution using multiple operating systems cooperating inside a product.

Get sources
***********
#. 
   Get |main_project_name| sources as described in the :ref:`documentation <AllScenariOSQuickBuild>`.

#. 
   If you already have sources cloned, update them to the most recent revision

   .. code-block:: bash

      user@pc:~/ohos$ repo sync -d

Architecture and Interfaces
***************************

.. toctree::
   :maxdepth: 3

   vending-machine-dev.rst
