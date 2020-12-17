This README file contains information on the contents of the meta-ohos-foundation layer.

Please see the corresponding sections below for details.

Dependencies
============

  URI: https://git.ostc-eu.org/incubate/meta-ohos/meta-common.git  
  branch: develop  

  URI: https://git.ostc-eu.org/incubate/meta-ohos/meta-thirdparty.git  
  branch: develop


Table of Contents
=================

  I. Adding the meta-foundation layer to your build
 II. Misc


I. Adding the meta-foundation layer to your build
=================================================

Run 'bitbake-layers add-layer meta-foundation'

II. Misc
========

This layer provides recipes for the core elements (foundation) of
the OHOS.

## Layer layout:
 - recipes 
   - samgr - Distritbuted Services Manager 
   - safwk - Main foundation application (initialization routines) 
