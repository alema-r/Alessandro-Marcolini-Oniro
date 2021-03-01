<!--
SPDX-FileCopyrightText: Huawei Inc.

SPDX-License-Identifier: Apache-2.0
-->

This README file contains information on the contents of the meta-ohos-staging layer.

Table of Contents
=================

  I. Adding the meta-ohos-staging layer to your build
 II. Misc


I. Adding the meta-ohos-staging layer to your build
===================================================

This layer only depends on poky or oe-core. To add it to your layers:

Run 'bitbake-layers add-layer meta-ohos-staging'

II. Misc
========

This layer provides recipes intended to upstream into other layer, but are not
fully ready yet. As a start we will have some connectivity related recipes for
OpenThread as well as MQTT.
