.. SPDX-FileCopyrightText: Huawei Inc.
..
.. SPDX-License-Identifier: CC-BY-4.0

.. include:: ../definitions.rst

|main_project_name| Build Architecture
######################################

Oniro build architecture is composed of multifaceted, independent, modular, and
reusable building blocks. In order to build OpenHarmony based images and its 
components, meta-openharmony component serves as one of the major building blocks.
In context of OpenHarmony, the OS essentially builds upon various components and 
respective functionalities, and in terms of functionality, the  meta-openharmony 
component principally helps to desolate the barrier of hardware boundaries, thus
inevitably transcending the classical OS dichotomy. 

Architecturally, the `meta-openharmony` is a bitbake layer that contains recipes
for building OpenHarmony images and components. The meta-openharmony includes a 
number of different repo manifest files, which you can use to fetch all repositories 
needed for building OpenHarmony. To have a successful build image, ensure that the 
undelying dependencies and the build procedure is followed in chronological order. 

``Oniro Project`` architecture is documented using `c4 model <https://c4model.com/>`_.

.. contents:: 
    :depth: 2

Overview
********

|main_project_name| build infrastructure is designed to run atop variety of OS kernels
ranging from RTOSes to Linux.

``oniro`` is an *umbrella* of meta layers containing build's meta-data
required for compiling |main_project_name| images. The architecture supports plugging
various kernels.

.. image:: assets/oniro-arch.png
