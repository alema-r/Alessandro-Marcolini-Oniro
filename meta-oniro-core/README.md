# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

# Introduction

## Description

The `meta-oniro-core` is the core layer for Oniro Project build metadata.

## Layer dependencies

The `meta-oniro-core` layer depends on the following layers:

* openembedded-core
  * URI: https://git.openembedded.org/openembedded-core
* bitbake
  * URI: https://git.openembedded.org/bitbake/
* meta-intel
  * URI: https://git.yoctoproject.org/git/meta-intel
* meta-openembedded
  * URI: https://git.openembedded.org/meta-openembedded/
  * layers: meta-networking
* meta-raspberrypi
  * URI: https://git.yoctoproject.org/git/meta-raspberrypi
* meta-rauc
  * URI: https://github.com/rauc/meta-rauc
* meta-seco-imx
  * URI: https://git.seco.com/pub/i.mx/yocto/5.x/meta-seco-imx
* meta-st-stm32mp
  * URI: https://github.com/STMicroelectronics/meta-st-stm32mp

Note that the dependencies of the above layers should also be taken into
consideration.

# Contributing

## Merge requests

All contributions are to be handled as merge requests in the
[oniro Gitlab repository](https://booting.oniroproject.org/distro/oniro). For
more information on the contributing process, check the `CONRIBUTING.md` file.

## Maintainers

* Andrei Gherzan <andrei.gherzan@huawei.com>
* Eilís Ní Fhlannagáin <elizabeth.flanagan@huawei.com>
* Stefan Schmidt <stefan.schmidt@huawei.com>

# License

This layer is released under the licenses listed in the `LICENSES` root
directory.
