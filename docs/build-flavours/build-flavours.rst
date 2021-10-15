.. SPDX-FileCopyrightText: Huawei Inc.
..
.. SPDX-License-Identifier: CC-BY-4.0

.. include:: ../definitions.rst

Overview of Build Flavours
##########################

|main_project_name| can be hosted on top of variety of kernels. Currently supported
kernels are Linux, Zephyr and FreeRTOS (experimental). The build system
requires build configuration that is specific to each kernel and |main_project_name|
provides all this configuration as build templates. See `Yocto documentation <https://www.yoctoproject.org/docs/current/mega-manual/mega-manual.html#creating-a-custom-template-configuration-directory>`_
for more info about the underlying mechanism.

In essence, a flavour is a build configuration that was used at build
initialization time for a specific kernel by passing the associated
``TEMPLATECONF`` configuration.

All the available ``flavours`` are available as subdirectories of the
``flavours`` directory in the root of the ``oniro`` repository.

Generically, when configuring a new build, one should pass the flavour as
``TEMPLATECONF`` to the ``oe-init-build-env`` script:

.. code-block:: console

    $ TEMPLATECONF=../oniro/flavours/<FLAVOUR_NAME> . ./oe-core/oe-init-build-env <BUILD_NAME>

Notes:

* The command assumes that the working directory is the root of the repo
  workspace when issuing the above command.
* The variables marked in ``<>`` are to be replaced accordingly.
* Mind the ``../`` prefix for ``TEMPLATECONF``. This is because the path
  provided needs to be relative to the build directory.

Once the build was initilized, you are dropped in a build environment where you have access to the ``OE`` tools:

.. code-block:: console
    
    $ bitbake <TARGET/IMAGE-NAME>
