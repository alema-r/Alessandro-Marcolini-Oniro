.. SPDX-FileCopyrightText: Huawei Inc.
..
.. SPDX-License-Identifier: CC-BY-4.0

.. _RepoWorkspace:

Repo Workspace
##############

OpenHarmony uses `repo <https://android.googlesource.com/tools/repo>`_ to
provide full workspace setup that includes all the repositories needed for
building OpenHarmony and developing on top.

.. contents:: 
    :depth: 2

The Repo Tool
*************

OpenHarmony provides a patched version of the repo tool published as a
`launchpad PPA <https://launchpad.net/~openharmony/+archive/ubuntu/tools>`_.
The patches are also available in the tool's `source repository <https://git.ostc-eu.org/OSTC/packaging/git-repo>`_.

Install this tool by following the next steps:

.. code-block:: console

   $ sudo add-apt-repository ppa:openharmony/tools
   $ sudo apt-get update
   $ sudo apt-get install git-repo

The Manifests
*************

The `manifest repository <https://git.ostc-eu.org/OSTC/OHOS/manifest>`_
provides the manifests available for configuring a workspace. The project provides two kinds of manifests:

* develop manifests (where some projects are following a branch)
* default manifests (where all projects are pinned to specific revisions)

Setting up the Workspace
************************

Once the repo tool is installed, you can initialize and populate the workspace.
This will bring in all the needed sources for building Openharmony:

.. code-block:: console

   $ mkdir ohos; cd ohos
   $ repo init -u https://git.ostc-eu.org/OSTC/OHOS/manifest.git -b stable
   $ repo sync --no-clone-bundle

You can checkout latest development source code by using *develop* branch
instead of *stable* as part of the above repo init command.

Workspace structure
*******************

A fully set workspace, will provide a structure similar to:

.. code-block:: none

   ./ohos/
   └── sources
       ├── meta-freertos
       ├── meta-ohos
       ├── meta-openembedded
       ├── meta-zephyr
       ├── <various yocto layers>
       └── poky

The *sources* directory will include ``poky`` and  all the build system layers
that are part of the build process. Any new layers will be included under this
directory.

It is recommended to use the root of the workspace for the build directories
using ``build`` as directory name prefix.