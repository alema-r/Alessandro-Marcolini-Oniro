.. SPDX-FileCopyrightText: Huawei Inc.
..
.. SPDX-License-Identifier: CC-BY-4.0

.. include:: ../definitions.rst

|main_project_name| Build Architecture
######################################

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

One of the core aims of |main_project_name| is to provide a stable foundation
for product development. In doing so, the project tries its best to align all
the included components to their respective LTS (long-term support) versions.
The build system is one of the core components used in Oniro and its version
adheres to the same principle.

The current version of the projects uses ``kirkstone`` version of Yocto
project, the build system of choice in |main_project_name|. This ensures that
all the Yocto layers brought in are either on their respective
``kirkstone`` branch/version or, in the absence of that, on a
``kirkstone-compatible`` branch/version.

.. image:: assets/oniro-arch.png
