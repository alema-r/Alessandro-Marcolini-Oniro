.. SPDX-FileCopyrightText: Huawei Inc.
..
.. SPDX-License-Identifier: CC-BY-4.0

.. include:: definitions.rst

.. _OniroQuickBuild:

|main_project_name| - Quick Build
#################################

This section will guide you to building your first |main_project_name| image targeting
a supported reference hardware. It will also provide the steps for flashing and
booting such an image.

The steps below will focus on a Qemu-based target. If you want to get a feeling
of |main_project_name| on a real hardware, checkout the :ref:`Avenger96 support page
<SupportedBoardAvenger96>`.

.. contents:: 
    :depth: 2

Prerequisites
*************

Install all the required host packages. Here is an example for **Ubuntu**:

.. code-block:: console

    $ sudo apt-get install gawk wget git diffstat unzip texinfo gcc-multilib \
      build-essential chrpath socat cpio python3 python3-pip python3-pexpect \
      xz-utils debianutils iputils-ping python3-git python3-jinja2 libegl1-mesa libsdl1.2-dev \
      pylint3 xterm file zstd

See `official Yocto documentation <https://www.yoctoproject.org/docs/latest/ref-manual/ref-manual.html#required-packages-for-the-build-host>`_
for host package requirements on all supported Linux distributions.

Clone Build System Repositories
*******************************

Install Google git repo tool. For example, on **Ubuntu 20.04**, you can do this
by:

.. code-block:: console

    $ sudo add-apt-repository ppa:ostc/ppa
    $ sudo apt-get update
    $ sudo apt-get install git-repo

Initialize a repo workspace and clone all required repositories:

.. code-block:: console

    $ mkdir oniroproject; cd oniroproject
    $ repo init -u https://booting.oniroproject.org/distro/oniro
    $ repo sync --no-clone-bundle

Building an Oniro image
***********************

The following steps will build a ``oniro-image-base``. The process will
build all its components, including the toolchain, from source.

First of all change directory into the one where the build repositories were
cloned using the repo tool. See above.

.. Note::

   Depending on the configuration type, a single |main_project_name| build
   could use around 100GB of disk space for downloads, temporary files, and
   build artifacts combined.

Initialize the build directory and run a build:

.. code-block:: console

    $ TEMPLATECONF=../oniro/flavours/linux . ./oe-core/oe-init-build-env build-oniro-linux
    $ MACHINE=qemux86-64 bitbake oniro-image-base

Booting a Qemu X86-64 Target with a |main_project_name| image
*************************************************************

Once the image is built, you can run a Qemu X86-64 instance using the provided script wrapper as follows:

.. code-block:: console

      $ MACHINE=qemux86-64 runqemu oniro-image-base wic

If the host has a VT-capable CPU, you can pass the ``kvm`` argument for better
performance. Check ``runqemu``'s help message for all available arguments.
