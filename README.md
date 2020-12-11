meta-ohos-common
================
This README file contains information on the contents of the meta-ohos-common layer.

Please see the corresponding sections below for details.

Dependencies
============

This layer depends on:

  URI: git://git.yoctoproject.org/poky.git
  branch: master

  meta-ohos/meta-ohos-thirdparty

Table of Contents
=================

  I. Adding the meta-common layer to your build
 II. Misc

I. Adding the meta-common layer to your build
=================================================

Run 'bitbake-layers add-layer meta-ohos-common'

II. Misc
========
This layer provides commonly used OHOS libraries and headers.

## Layer layout
 - recipes-frameworks - recipes for various OHOS frameworks libraries
 - recipes-interfaces - recipes providing OHOS interfaces headers
 - recipes-utils      - recipes for various OHOS utilities
