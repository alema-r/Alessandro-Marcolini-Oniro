# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

# Introduction

## Description

The `meta-oniro-blueprints` layer contains build metadata for Oniro Project
blueprints.

## Layer dependencies

The `meta-oniro-blueprints` layer depends on the following layers:

* openembedded-core
  * URI: https://git.openembedded.org/openembedded-core
* bitbake
  * URI: https://git.openembedded.org/bitbake/
* meta-homeassistant
  * URI: https://github.com/meta-homeassistant/meta-homeassistant.git
* meta-openembedded
  * URI: https://git.openembedded.org/meta-openembedded/
  * layers: meta-oe
* meta-raspberrypi
  * URI: https://git.yoctoproject.org/git/meta-raspberrypi
* oniro
  * URI: https://booting.oniroproject.org/distro/oniro.git
  * layers: meta-oniro-core

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

This layer is release under the licenses listed in the `LICENSES` root directory.
