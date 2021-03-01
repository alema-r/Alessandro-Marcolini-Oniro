<!--
SPDX-FileCopyrightText: Huawei Inc.

SPDX-License-Identifier: Apache-2.0
-->

This README file contains information on the contents of the meta-ohos-foundation layer.

Please see the corresponding sections below for details.

Table of Contents
=================

  I. Adding the meta-foundation layer to your build
 II. Misc


I. Adding the meta-foundation layer to your build
=================================================

Run 'bitbake-layers add-layer meta-ohos-foundation'

II. Misc
========

This layer provides recipes for the core elements (foundation) of
the OHOS.

## Layer layout:
 - recipes
   - samgr - Distritbuted Services Manager 
   - safwk - Main foundation application (initialization routines)
   - frameworks - various OHOS frameworks libraries
   - interfaces - OHOS interfaces headers
   - utils      - various OHOS utilities
   - recipes-libs
     - cjson - Ultralightweight JSON parser in ANSI C
     - libsec - library implementing Annex K of C11, Bounds-checking interfaces
