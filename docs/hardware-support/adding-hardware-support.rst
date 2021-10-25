.. SPDX-FileCopyrightText: Huawei Inc.
..
.. SPDX-License-Identifier: CC-BY-4.0

.. include:: ../definitions.rst

Adding New Hardware Support in |main_project_name|
##################################################

This section details the addition of new hardware to the supported set in
|main_project_name|. It is intended as a checklist for adding new boards to
|main_project_name| build system.

Before starting get familiar with |main_project_name| Contribution Process.

.. contents:: 
   :depth: 2

Select |main_project_name| Flavour
**********************************

|main_project_name| uses a notion of kernel specific flavours:

- Linux flavour
- Zephyr flavour
- FreeRTOS flavour (experimental)

Flavours have predefined `IMAGES` and `MACHINES`.

A single board can be included in more than one flavour only when it has **well
maintained support** in targeted kernels.

Add Required meta-layers
************************

Oniro flavours configuration templates (stored in `distro/oniro/flavours <https://booting.oniroproject.org/distro/oniro/-/tree/dunfell/flavours>`_
directory) consist of the following files:

.. list-table:: Configuration Files
   :header-rows: 1

   * - File Name
     - Description
   * - ``bblayers.conf.sample``
     - set of meta-layers for the specific flavour (it can be unified across
       multiple layers where there are no layers incompatibilities)
   * - ``conf-notes.txt``
     - text snippet to be used as part of build logs
   * - ``local.conf.sample``
     - default flavour build configuration

|main_project_name| build system uses ***repo*** tool for cloning required meta-layers
into appropriate build directory structure (see :ref:`Setting up a repo workspace <RepoWorkspace>`).
To include a new layer, it has to be added in two places of the `oniro repository <https://booting.oniroproject.org/distro/oniro/-/tree/dunfell/manifests>`_:

- The manifest file
- The flavours ``bblayers.conf.sample`` file

Test Image Backward Compatibility Of Newly Added Layers
*******************************************************

New BSP layers cannot interfere / break already supported ``IMAGES`` / ``MACHINES``.

Document and Advertise the New ``MACHINE`` Support
**************************************************

Newly added ``MACHINE`` shall be documented in: :ref:`Hardware Support <RepoWorkspace>`.
Use an existing board documentation as template and populate it accordingly for
your newly added machine.

The same machine needs to also be advertised in two places:

- Flavour's ``local.conf.sample`` as a commented out ``MACHINE`` variable value
  (tweak this step accordingly for default machine change)
- Flavour's ``conf-notes.txt`` to surface the support in build logs

Create Merge Requests
*********************

Create the Merge Request against the ***dunfell*** branch according to the
Contributing Process for repositories:

- `distro/oniro <https://booting.oniroproject.org/distro/oniro>`_
