.. SPDX-FileCopyrightText: Huawei Inc.
..
.. SPDX-License-Identifier: CC-BY-4.0

Qemu ARM64
##########

.. contents:: 
   :depth: 4

Overview
********

OpenHarmony supports running the software stack into an virtual invironment using Qemu.

Building OHOS image
===================

To clone the source code, perform the procedure in: :ref:`Setting up a repo workspace <RepoWorkspace>`.

Building a Linux image
======================

Supported images
----------------

.. list-table:: Supported images
  :widths: auto
  :header-rows: 1

  * - Image  Name
    - Description
  * - openharmony-image-base
    - OpenHarmony image including the base OS software stack
  * - openharmony-image-extra
    - OpenHarmony Wayland image including the base OS software stack

Build steps
-----------

1. Source the environment with proper template settings, flavour being *linux*
   and target machine being *qemuarm64*. Pay attention to how relative paths are
   constructed. The value of *TEMPLATECONF* is relative to the location of the
   build directory *./build-ohos-linux*, that is going to be created after
   this step:

.. code-block:: console

   $ TEMPLATECONF=../sources/meta-ohos/flavours/linux . ./sources/poky/oe-init-build-env build-ohos-linux

2. You will find yourself in the newly created build directory. Call *bitbake*
   to build the image. For example, if you are using *openharmony-image-base*
   run the following command:

.. code-block:: console

   $ MACHINE=qemuarm64 bitbake openharmony-image-base

Once the image is done, you can run the Qemu usin the provided script wrapper:

.. code-block:: console

   $ MACHINE=qemuarm64 runqemu
