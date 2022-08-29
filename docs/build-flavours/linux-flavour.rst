.. SPDX-FileCopyrightText: Huawei Inc.
..
.. SPDX-License-Identifier: CC-BY-4.0

.. include:: ../definitions.rst

.. _Linux_Kernel:

Linux Kernel Build Flavour
##########################

|main_project_name| Linux build flavour is based on *oniro-linux* distribution (distro configuration).

Supported images:

* oniro-image-base
* oniro-image-base-tests
* oniro-image-extra
* oniro-image-extra-tests

Supported machines (default in **bold**):

* **qemux86-64**
* qemux86
* qemuarm64-efi
* qemuarm-efi
* seco-intel-b68 (SECO SBC-B68)
* seco-imx8mm-c61-2gb (SECO SBC-C61 2GB DRAM)
* seco-imx8mm-c61-4gb (SECO SBC-C61 4GB DRAM)
* seco-px30-d23 (SECO SBC-D23)

Build steps example:

.. code-block:: console

    $ TEMPLATECONF=../oniro/flavours/linux . ./oe-core/oe-init-build-env build-oniro-linux
    $ bitbake oniro-image-base

You can test the image built for the qemux86-64 target by issuing:

.. code-block:: console

    $ runqemu qemux86-64 oniro-image-base wic ovmf slirp

.. _linux-flavour-usage:

Usage
*****

After successful bootup, you will be dropped into a login shell:

.. code-block:: console

    oniro-linux-qemux86-64 login:
    
The default login name is **oniro** with **oniro** as a password.

After login you will see the shell prompt:

.. code-block:: console

    oniro@oniro-linux-qemux86-64:~$

To exit qemu, you can either shut down the system:

.. code-block:: console

    oniro@oniro-linux-qemux86-64:~$ sudo poweroff    
    Password: oniro 

or close qemu using a key combination: *Ctrl-a followed by 'x'*.
