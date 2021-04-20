.. SPDX-FileCopyrightText: Huawei Inc.
..
.. SPDX-License-Identifier: CC-BY-4.0

Poky/Yocto Project
##################

AllScenariOS aims to use standard opensource tools to create a build environment
that is both familiar to users in the domain but also flexible enough for the
requirements of the project. With this in mind, the project build
infrastructure is based on the OpenEmbedded build system, more specifically
`Poky, the Yocto Project open source reference embedded distribution <https://www.yoctoproject.org/software-overview/>`_.

.. contents:: 
    :depth: 3

Build System Concepts
*********************

The build system uses build instruction files that in the language of the
system are called ``recipes`` and ``layers``.

Layers are one of the fundamental models of the build system. It enables both
collaboration and customization by defining scoped meta-data. These layers
become a collection of build instruction files that have a defined scope. For
example, there are BSP (board support package) layers that enable board support
in the build system.

See `terms  for reference <https://www.yoctoproject.org/software-overview/>`_
for more information.

AllScenariOS Build Layers
-------------------------

AllScenariOS bases its build setup on Poky, the Yocto Project open source
reference embedded distribution. The main hub of layers, is `meta-ohos <https://git.ostc-eu.org/OSTC/meta-ohos/>`_,
a collection of layers with different scopes for defining the project's
requirements and capabilities.

For example, ``meta-ohos-core`` provides build recipes for defining the core
policies of the build infrastructure (`distribution` configuration, images,
core packages customization, etc.).

Another example is ``meta-ohos-staging``, a layer that provides temporary fixes
and support for changes that are aimed upstream but have this place until
upstream catches up.

For more details of each provided layer of ``meta-ohos``, see the relevant
``README.md`` file at the root of the layer,

Besides the ``meta-ohos`` collection of layers, the project is also the home
to a set of other build system layers. Explore them all in our project `GitLab <https://git.ostc-eu.org/OSTC/OHOS>`_
instance.

Additional Documentation
************************

`Yocto Project <https://www.yoctoproject.org>`_ provides extensive
documentation on various aspects of the build system. For the general usage of the build system, it's components, architecture and capabilities consult the following resources:

- `Yocto Project Documentation Home <https://docs.yoctoproject.org/>`_
- `Yocto Project Quick Build <https://www.yoctoproject.org/docs/current/brief-yoctoprojectqs/brief-yoctoprojectqs.html>`_
- `Yocto Project Reference Manual <https://www.yoctoproject.org/docs/latest/ref-manual/ref-manual.html>`_
