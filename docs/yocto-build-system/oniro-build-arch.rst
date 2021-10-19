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

.. image:: assets/oniro-arch.png
