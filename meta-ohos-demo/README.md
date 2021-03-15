# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

# Introduction

## Description

This layer, `meta-ohos-demo`, contains build metadata for OpenHarmony demos.

## Layer dependencies

The `meta-ohos-demo` layer depends on the following layers:

* meta-ohos
  * URI: https://git.ostc-eu.org/OSTC/OHOS/meta-ohos.git
* meta-homeassistant
  * URI: https://github.com/meta-homeassistant/meta-homeassistant.git

Note that the dependencies of the above layers should also be taken into
consideration.

# Demos

## Dashboard demo

The dashboard demo is composed out of a set of devices that create a home automation demo environment.

### The Gateway device

* Kernel type: linux
* Hardware (MACHINE): stm32mp1-av96
* Distribution configuration (DISTRO): openharmony-linux-demo-dashboard
* Relevant/supported images:
  * demo-dashboard-gateway-image

Build steps:

1. initialise a Linux flavour build environment as defined in the [meta-ohos documentation](https://git.ostc-eu.org/OSTC/OHOS/meta-ohos#ohos-linux-flavour)
2. set MACHINE and DISTRO either in your `local.conf` file or pass them as environemnt variables bellow
3. build image
  * eg. `bitbake demo-dashboard-gateway-image`

# Contributing

## Merge requests

All contributions are to be handled as merge requests in the [meta-ohos Gitlab project](https://git.ostc-eu.org/OSTC/OHOS/meta-ohos).

## Maintainers

* Andrei Gherzan <andrei.gherzan@huawei.com>

# License

The license of this layer is [Apache-2.0](https://git.ostc-eu.org/OSTC/OHOS/meta-ohos/-/blob/stable/LICENSES/Apache-2.0.txt).
