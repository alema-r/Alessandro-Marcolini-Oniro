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
    $ repo init -u https://git.ostc-eu.org/OSTC/OHOS/manifest.git -b stable
    $ repo sync

You can checkout latest development source code by using `develop` branch instead of `stable` with the above `repo init` command.

`repo sync` command should result in the following directory structure:

    ./ohos/
    └── sources
        ├── meta-freertos
        ├── meta-ohos
        ├── meta-openembedded
        ├── meta-zephyr
        ├── <various yocto layers>
        └── poky

### OHOS flavours

OpenHarmony can be hosted on top of variety of kernels.
Currently supported kernels (a.k.a. OHOS flavours) are Linux, Zephyr
and FreeRTOS (experimental).

To build OHOS flavour issue following commands:

    $ TEMPLATECONF=../sources/meta-ohos/flavours/<lower_case_flavour> . ./sources/poky/oe-init-build-env build-<flavour>-<target_machine>
    $ bitbake <image-name>

MACHINE variable can be set up in conf/local.conf file under build directory
or via command line, e.g.:

    $ MACHINE=<target_machine> bitbake <image-name>

#### OHOS Linux flavour

OHOS Linux flavour is based on _poky_ distribution.

Supported images:
- core-image-base

Supported machines:
- qemux86-64 (default)
- qemux86
- qemuarm
- qemuarm64
- seco-intel-b68 (SECO intel B68)
- stm32mp1-av96 (96Boards Avenger96)

Example:

    $ TEMPLATECONF=../sources/meta-ohos/flavours/linux . ./sources/poky/oe-init-build-env build-ohos-linux-qemux86-64
    $ bitbake core-image-base

You can test the image built for the qemux86-64 target by issuing:

    $ runqemu qemux86-64 qemuparams="-nographic"

After successful bootup, you should see following:

    Poky (Yocto Project Reference Distro) 3.1.4 qemux86-64 /dev/ttyS0

    qemux86-64 login:
    
Default login is _root_ without a password.
After login you should see prompt:

    root@qemux86-64:~#

To exit qemu, you can either shut down the system:

    root@qemux86:~# poweroff -f

or close qemu using key combination:

    Ctrl-a followed by 'x'

#### OHOS Zephyr flavour

OHOS Zephyr flavour is based on _zephyr_ distribution and supports following images / machines:

Supported images:
- zephyr-philosophers

Supported machines:
- qemu-cortex-m3
- qemu-x86 (default)

Example:

    $ TEMPLATECONF=../sources/meta-ohos/flavours/zephyr . ./sources/poky/oe-init-build-env build-ohos-zephyr-qemu-x86
    $ bitbake zephyr-philosophers

You can test the image built for the qemu-x86 target by issuing:

    $ DEPLOY_DIR_IMAGE=tmp-newlib/deploy/images/qemu-x86 runqemu qemu-x86

After successful bootup, you should see following:

    Booting from ROM..*** Booting Zephyr OS build zephyr-v2.4.0  ***
    Philosopher 0 [P: 3]  THINKING [  300 ms ]
    Philosopher 1 [P: 2]   EATING  [  575 ms ]
    Philosopher 2 [P: 1]        STARVING
    Philosopher 3 [P: 0]   EATING  [  525 ms ]
    Philosopher 4 [C: -1]  THINKING [  475 ms ]

To exit qemu, use key combination:

    Ctrl-a followed by 'x'

#### OHOS FreeRTOS flavour

OHOS FreeRTOS flavour is based on _freertos_ distribution and supports following images / machines:

Supported images:
- freertos-demo

Supported machines:
- qemuarmv5

Example:

    $ TEMPLATECONF=../sources/meta-ohos/flavours/freertos . ./sources/poky/oe-init-build-env build-ohos-freertos-qemuarmv5
    $ bitbake freertos-demo

You can test the image built for the qemuarmv5 target by issuing:

    $ runqemu qemuarmv5

After successful bootup, you should see following:

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

To exit qemu, use key combination:

    Ctrl-a followed by 'x'

# meta-ohos architecture

All decisions for architecturally significant requirements are documented using 
[adr-tools](https://github.com/npryce/adr-tools) and stored in [docs/adr](./docs/adr) subdirectory.
Decisions are discussed within MR of a given decision md file.

**meta-ohos** architecture is documented using [c4 model](https://c4model.com/).

## meta-ohos overview

**OpenHarmony** is a distributed OS that is designed to run atop variety of OS kernels 
ranging from RTOSs to Linux.

**meta-ohos** is a _umbrella_ meta layer containing all layers required to build
**OpenHarmony** Image basing on existing kernel meta-layers.

```plantuml
!includeurl https://raw.githubusercontent.com/RicardoNiepel/C4-PlantUML/release/1-0/C4_Context.puml

Person(dev, "Developer", "Anyone willing to build the OHOS based image")
System(meta_ohos, "meta-ohos", "Open Harmony OS umbrella bitbake meta-layer")
Boundary(poky, "poky") {
    System_Ext(bitbake, "bitbake", "build process orchestrator")
    System_Ext(linux_yocto, "linux-yocto", "Yocto Project LTS linux kernel")
    System_Ext(meta_external_toolchain, "meta-external-toolchain", "Yocto Project LTS GNU GCC and LLVM toolchains")
}
System_Ext(meta_zephyr, "meta-zephyr", "ZephyrOS meta-layer")
System_Ext(meta_freertos, "meta-freertos", "FreeRTOS meta-layer")
System_Ext(meta_freertos, "meta-freertos", "FreeRTOS meta-layer")
System_Ext(linux_yocto, "linux-yocto", "Yocto Project LTS linux kernel")
System_Ext(meta_external_toolchain, "meta-external-toolchain", "Yocto Project LTS GNU GCC and LLVM toolchains")

Rel(dev, bitbake, "selects configuration, initiates the build, deploys image to the target")
Rel(bitbake, meta_ohos, "incorporates meta-layers relevant for selected kernel / configuration")
Rel(bitbake, linux_yocto, "incorporates Yocto Project LTS linux kernel reference")
Rel(bitbake, meta_external_toolchain, "incorporates Yocto Project LTS toolchains")
Rel(bitbake, meta_zephyr, "incorporates for OHOS on Zephyr build")
Rel(bitbake, meta_freertos, "incorporates for OHOS on FreeRTOS build")
```

