.. SPDX-FileCopyrightText: Huawei Inc.
..
.. SPDX-License-Identifier: CC-BY-4.0

Linux Kernel Build Flavour
##########################

AllScenariOS Linux build flavour is based on *openharmony-linux* distribution (distro configuration).

Supported images:

* openharmony-image-base
* openharmony-image-base-tests
* openharmony-image-extra
* openharmony-image-extra-tests

Supported machines (default in **bold**):

* **qemux86-64**
* qemux86
* qemuarm
* qemuarm64
* seco-intel-b68 (SECO SBC-B68)
* stm32mp1-av96 (96Boards Avenger96)
* seco-imx8mm-c61 (SECO SBC-C61)

Build steps example:

.. code-block:: console

    $ TEMPLATECONF=../sources/meta-ohos/flavours/linux . ./sources/poky/oe-init-build-env build-ohos-linux
    $ bitbake openharmony-image-base

You can test the image built for the qemux86-64 target by issuing:

.. code-block:: console

    $ runqemu qemux86-64

After successful bootup, you will be dropped into a login shell:

.. code-block:: console

    qemux86-64 login:
    
Default login is *root* without a password.

After login you will see the shell prompt:

.. code-block:: console

    root@qemux86-64:~#

To exit qemu, you can either shut down the system:

.. code-block:: console

    root@qemux86:~# poweroff -f

or close qemu using a key combination: *Ctrl-a followed by 'x'*.
