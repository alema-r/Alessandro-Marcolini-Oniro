<!--
SPDX-FileCopyrightText: Huawei Inc.

SPDX-License-Identifier: Apache-2.0
-->

This README file contains information about the contents of the meta-ohos-core layer.

Please see the corresponding sections below for details.

Table of Contents
=================

  I. Dependencies
 II. Adding the meta-ohos-core layer to your build
III. Misc

I. Dependencies
===============

This layer depends on:

- URI: git://git.yoctoproject.org/poky
  - branch: master
  - revision: HEAD

- URI: git://git.openembedded.org/meta-openembedded
  - layers: meta-oe
  - branch: master
  - revision: HEAD

II. Adding the meta-ohos-core layer to your build
=================================================

Run 'bitbake-layers add-layer /path/to/meta-ohos-core'

III. Misc
=========

This layer provides recipes for the core elements of the Oniro Project distribution.
