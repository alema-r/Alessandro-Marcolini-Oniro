.. SPDX-FileCopyrightText: Huawei Inc.
..
.. SPDX-License-Identifier: CC-BY-4.0

.. include:: ../definitions.rst

.. _FreeRTOS_Kernel:

FreeRTOS Kernel Build Flavour
#############################

|main_project_name| FreeRTOS build flavour is based on *freertos* distribution (distro configuration).

Supported images:

* freertos-demo

Supported machines (default in **bold**):

* **qemuarmv5**

Build steps example:

.. code-block:: console

    $ TEMPLATECONF=../oniro/flavours/freertos . ./oe-core/oe-init-build-env build-oniro-freertos
    $ bitbake freertos-demo

You can test the image built for the qemuarmv5 target by issuing:

.. code-block:: console

    $ runqemu qemuarmv5

After successful bootup, the output of the application will be similar to:

.. code-block:: console

    ###### - FreeRTOS sample application - ######
    
    A text may be entered using a keyboard.
    It will be displayed when 'Enter' is pressed.
    
    Periodic task 10 secs
    Waiting For Notification - Blocked...
    Task1
    Task1
    You entered: "HelloFreeRTOS"
    Unblocked
    Notification Received
    Waiting For Notification - Blocked...

To exit qemu, use the following key combination: *Ctrl-a followed by 'x'*.
