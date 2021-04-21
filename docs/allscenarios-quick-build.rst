.. SPDX-FileCopyrightText: Huawei Inc.
..
.. SPDX-License-Identifier: CC-BY-4.0

.. _AllScenariOSQuickBuild:

All Scenario OS - Quick Build
#############################

This section will guide you to building your first All Scenario OS image targeting
a supported reference hardware. It will also provide the steps for flashing and
booting such an image.

The steps below will focus on a Qemu-based target. If you want to get a feeling
of All Scenario OS on a real hardware, checkout the :ref:`Avenger96 support page
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
    pylint3 xterm 

See `official Yocto documentation <https://www.yoctoproject.org/docs/latest/ref-manual/ref-manual.html#required-packages-for-the-build-host>`_
for host package requirements on all supported Linux distributions.

Clone build system repositories
*******************************

Install Google git repo tool. For example, on **Ubuntu 20.04**, you can do this
by:

.. code-block:: console

    $ sudo add-apt-repository ppa:openharmony/tools
    $ sudo apt-get update
    $ sudo apt-get install git-repo

Initialize a repo workspace and clone all required repositories:

.. code-block:: console

    $ mkdir ohos; cd ohos
    $ repo init -u https://git.ostc-eu.org/OSTC/OHOS/manifest.git -b develop
    $ repo sync --no-clone-bundle

Build the allscenarios-image-base image
***************************************

The following steps will build a ``allscenarios-image-base``. The process will
build all its components, including the toolchain, from source.

First of all change directory into the one where the build repositories were
cloned using the repo tool. See above.

Initialize the build directory and run a build:

.. code-block:: console

    $ TEMPLATECONF=../sources/meta-ohos/flavours/linux . ./sources/poky/oe-init-build-env build-ohos-linux
    $ MACHINE=qemux86-64 bitbake allscenarios-image-base

Booting a Qemu X86-64 target with a All Scenario OS image
*********************************************************

Once the build is done, you can run a Qemu X86-64 instance as it follows:

Once the image is done, you can run the Qemu usin the provided script wrapper:

.. code-block:: console

      $ MACHINE=qemux86-64 runqemu

If the host has a VT-capable CPU, you can pass the ``kvm`` argument for better
performance. Check ``runqemu``'s help message for all available arguments.
