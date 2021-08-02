.. SPDX-FileCopyrightText: Huawei Inc.
..
.. SPDX-License-Identifier: CC-BY-4.0

All Scenarios OS Build Architecture
###################################

``meta-ohos`` architecture is documented using `c4 model <https://c4model.com/>`_.

.. contents:: 
    :depth: 2

Overview
********

All Scenarios OS build infrastructure is designed to run atop variety of OS kernels
ranging from RTOSes to Linux.

``meta-ohos`` is an *umbrella* of meta layers containing build's meta-data
required for compiling All Scenarios OS images. The architecture supports plugging
various kernels.

.. image:: assets/meta-ohos-arch.png
