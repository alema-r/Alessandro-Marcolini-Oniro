.. SPDX-FileCopyrightText: Huawei Inc.
..
.. SPDX-License-Identifier: CC-BY-4.0

AllScenariOS Build Architecture
###############################

``meta-ohos`` architecture is documented using `c4 model <https://c4model.com/>`_.

.. contents:: 
    :depth: 2

Overview
********

AllScenariOS build infrastructure designed to run atop variety of OS kernels
ranging from RTOSs to Linux.

``meta-ohos`` is a *umbrella* of meta layers containing build meta-data
required for compiling AllScenariOS images. The architecture supports plugging
various kernels.

.. image:: assets/meta-ohos-arch.png
