.. SPDX-FileCopyrightText: Huawei Inc.
..
.. SPDX-License-Identifier: CC-BY-4.0

Qemu X86
########

.. contents:: 
   :depth: 4

Overview
********

All Scenarios OS supports running the software stack into an virtual environment using Qemu.

Building OHOS image
===================

To clone the source code, perform the procedure in: :ref:`Setting up a repo workspace <RepoWorkspace>`.

Building a Linux image
======================

Build steps
-----------

1. Source the environment with proper template settings, flavour being *linux*
   and target machine being *qemux86*. Pay attention to how relative paths are
   constructed. The value of *TEMPLATECONF* is relative to the location of the
   build directory *./build-ohos-linux*, that is going to be created after
   this step:

.. code-block:: console

   $ TEMPLATECONF=../sources/meta-ohos/flavours/linux . ./sources/poky/oe-init-build-env build-ohos-linux

2. You will find yourself in the newly created build directory. Call *bitbake*
   to build the image. For example, if you are using *allscenarios-image-base*
   run the following command:

.. code-block:: console

   $ MACHINE=qemux86 bitbake allscenarios-image-base

Once the image is done, you can run the Qemu using the provided script wrapper:

.. code-block:: console

   $ MACHINE=qemux86 runqemu
