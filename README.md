meta-ohos-thirdparty
====================

This README file contains information on the contents of the meta-ohos-thirdparty layer.

Please see the corresponding sections below for details.

Dependencies
============

This layer depends on:

  URI: git://git.yoctoproject.org/poky.git
  branch: master

Table of Contents
=================

  I. Adding the meta-thirdparty layer to your build
 II. Misc


I. Adding the meta-thirdparty layer to your build
=================================================

Run 'bitbake-layers add-layer meta-ohos/meta-ohos-thirdparty'

II. Misc
========
This layer provides recipes for various third-party libraries
used by OHOS.

## Layer layout:
 - recipes-libs
   - cjson - Ultralightweight JSON parser in ANSI C
   - libsec - library implementing Annex K of C11, Bounds-checking interfaces
