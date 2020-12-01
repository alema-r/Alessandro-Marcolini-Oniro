meta-ohos
=========

**meta-ohos** is a set of bitbake layers for building OpenHarmony images 
using the bitbake infrastructure.

[[_TOC_]]

# Getting started

## Prerequisites

To start working with **meta-ohos** first install git repo:

    $ sudo add-apt-repository ppa:zyga/oh-tools
    $ sudo apt-get update
    $ sudo apt-get install git-repo

## Building OpenHarmony image

Once git repo has been installed we can use it to clone the necessary repositories:

    $ mkdir ohos; cd ohos
    $ repo init -u https://git.ostc-eu.org/incubate/meta-ohos/manifest.git
    $ repo sync
    $ cd poky
    poky$ . oe-init-build-env

Working directory will automatically change to ./poky/build. Add layers for
other kernels. Layers for poky were added automatically by sourcing
`oe-init-build-env`.

    build$ bitbake-layers add-layer ../meta-openembedded/meta-oe
    build$ bitbake-layers add-layer ../meta-openembedded/meta-python
    build$ bitbake-layers add-layer ../meta-zephyr
    build$ bitbake-layers add-layer ../meta-freertos

Build distro of your choice:

For Poky-tiny you can choose any of the following machines:
qemux86, qemux86-64, qemuarm, qemuarmv5

    build$ DISTRO=poky-tiny MACHINE=qemux86 bitbake core-image-minimal


For Zephyr you can choose any of the following machines:
acrn, arduino-101-ble, arduino-101, arduino-101-sss, qemu-cortex-m3, qemu-nios2, qemu-x86

    build$ DISTRO=zephyr MACHINE=qemu-x86 bitbake zephyr-philosophers

For FreeRTOS you can choose any of the following machines: qemuarmv5.

    build$ DISTRO=freertos MACHINE=qemuarmv5 bitbake freertos-demo

For Zephyr, `zephyr-philosophers` is the one of sample applications available
in meta-zephyr layer by Yocto project. It's easy to build other samples using
recipes available in `meta-zephyr/recipes-kernel/zephyr-kernel/` directory.

## Running OpenHarmony image

When the build is finished, you can run the image by issuing:

    # For poky
    build$ runqemu qemux86 ramfs qemuparams="-nographic"
    # For Zephyr
    build$ DEPLOY_DIR_IMAGE=tmp-newlib/deploy/images/qemu-x86 runqemu qemu-x86
    # For FreeRTOS
    build$ runqemu qemuarmv5

After successful bootup, you should see following:

    # For Zephyr
    Booting from ROM..*** Booting Zephyr OS build zephyr-v2.4.0  ***
    Philosopher 0 [P: 3]  THINKING [  300 ms ]
    Philosopher 1 [P: 2]   EATING  [  575 ms ]
    Philosopher 2 [P: 1]        STARVING
    Philosopher 3 [P: 0]   EATING  [  525 ms ]
    Philosopher 4 [C: -1]  THINKING [  475 ms ]

    # For poky
    qemux86 login:
    # Default login is _root_ without a password.
    # After login you should see prompt:
    root@qemux86:~#

    # For FreeRTOS
    ###### - FreeRTOS sample application -######
    
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

To exit qemu, you can either shut down the system:

    root@qemux86:~# poweroff -f

or close qemu using key combination:

    Ctrl-a followed by 'x'


# meta-ohos architecture

All decisions for architecturally significant requirements are documented using 
[adr-tools](https://github.com/npryce/adr-tools) and stored in [adr](./adr) subdirectory.
Decisions are discussed within MR of a given decision md file.

**meta-ohos** architecture is documented using [c4 model](https://c4model.com/).

## meta-ohos overview

![meta-ohos system context diagram](./diagrams/meta-ohos_system_context_diagram.svg)

