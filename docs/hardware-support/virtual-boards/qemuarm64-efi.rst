.. SPDX-FileCopyrightText: Huawei Inc.
..
.. SPDX-License-Identifier: CC-BY-4.0

.. include:: ../../definitions.rst

Qemu ARM 64bit
##############

.. contents:: 
   :depth: 4

Overview
********

|main_project_name| supports running the software stack into an virtual
environment using Qemu.

Building an Oniro image
=======================

To clone the source code, perform the procedure in: :ref:`Setting up a repo
workspace <RepoWorkspace>`.

Building a Linux image
======================

Build Steps
-----------

1. Source the environment with proper template settings, flavour being *linux*
   and target machine being *qemuarm64-efi*. Pay attention to how relative
   paths are constructed. The value of *TEMPLATECONF* is relative to the
   location of the build directory *./build-oniro-linux*, that is going to be
   created after this step:

.. code-block:: console

   $ TEMPLATECONF=../oniro/flavours/linux . ./oe-core/oe-init-build-env build-oniro-linux

2. You will find yourself in the newly created build directory. Call *bitbake*
   to build the image. For example, if you are using *oniro-image-base*
   run the following command:

.. code-block:: console

   $ MACHINE=qemuarm64-efi bitbake oniro-image-base

Once the image is done, you can run the Qemu using the provided script wrapper:

.. code-block:: console

   $ MACHINE=qemuarm64-efi runqemu oniro-image-base nographic slirp
