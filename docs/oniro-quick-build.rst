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
of |main_project_name| on a real hardware, checkout one of the
:ref:`supported boards <SupportedBoards>`.

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
    $ repo init -u https://gitlab.eclipse.org/eclipse/oniro-core/oniro.git -b kirkstone
    $ repo sync --no-clone-bundle

.. _Building an Oniro image:

Building and Running an Oniro image
***********************************

The following steps will build a ``oniro-image-base``. The process will
build all its components, including the toolchain, from source.

First of all change directory into the one where the build repositories were
cloned using the repo tool. See above.

.. Note::

   Depending on the configuration type, a single |main_project_name| build
   could use around 100GB of disk space for downloads, temporary files, and
   build artifacts combined.

Initialize the build directory:

.. code-block:: console

    $ TEMPLATECONF=../oniro/flavours/linux . ./oe-core/oe-init-build-env build-oniro-linux

Now that the build is initialized, you have the choice of building a standard
Oniro image or an OpenHarmony compatible one.

A. Standard Oniro image
-----------------------

As the build directory is now initialized, you can proceed to running the build
process:

.. code-block:: console

    $ MACHINE=qemux86-64 bitbake oniro-image-base

Once the image is built, you can run a Qemu instance using the provided
script wrapper as follows:

.. code-block:: console

      $ MACHINE=qemux86-64 runqemu oniro-image-base wic ovmf slirp

If the host has a VT-capable CPU, you can pass the ``kvm`` argument for better
performance. Check ``runqemu``'s help message for all available arguments.

.. _Building with OpenHarmony:

B. Building with OpenHarmony compatibility
------------------------------------------

To enable OpenHarmony compatibility features, you need to tweak the
`local.conf` file before running the build process. You will find this
configuration in `build-oniro-linux/conf/local.conf` where you'll need to add
the following:

.. code-block:: sh

    TOOLCHAIN="clang"
    RUNTIME="llvm"
    DISTRO_FEATURES:append = " openharmony"
    IMAGE_INSTALL:append = " openharmony-standard"

This will enable OpenHarmony features and add OpenHarmony Standard System
features to the `oniro-image-base` image.

As the build directory is now initialized, you can proceed to run the build
process:

.. code-block:: console

    $ MACHINE=qemuarm bitbake oniro-image-base

Once the image is built, you can run a Qemu instance using the provided
script wrapper as follows:

.. code-block:: console

      $ MACHINE=qemuarm runqemu oniro-image-base serialstdio nographic slirp

Runtime Login
*************

Once the target has booted, a login shell will prompt for a user/password
combination. By default, the OS uses the following:

- user name: **oniro**
- password: **oniro**
