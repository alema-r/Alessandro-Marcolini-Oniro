.. SPDX-FileCopyrightText: Huawei Inc.
..
.. SPDX-License-Identifier: CC-BY-4.0

.. include:: definitions.rst

.. _RepoWorkspace:

Repo Workspace
##############

|main_project_name| uses `repo <https://android.googlesource.com/tools/repo>`_ to
provide full workspace setup that includes all the repositories needed for
building |main_project_name| and developing on top.

.. contents:: 
    :depth: 2

The Repo Tool
*************

In order to setup a sources workspace of |main_project_name|, the ``git-repo`` tool is
required on the host.

|main_project_name| provides a patched version of the repo tool for ``Ubuntu 20.04``
at `launchpad PPA <https://launchpad.net/~openharmony/+archive/ubuntu/tools>`_,
for ``rpm``/``dnf`` based distributions at
`copr <https://copr.fedorainfracloud.org/coprs/openharmony/tools/>`_, and for
openSUSE, Arch and a few other distributions at `OBS <https://build.opensuse.org/project/show/home:openharmony:tools>`_.

The patches are also available in the tool's `source repository <https://git.ostc-eu.org/OSTC/packaging/git-repo>`_.
One can install this tool by following the next steps:

On Ubuntu:
----------

.. code-block:: console

   $ sudo add-apt-repository ppa:openharmony/tools
   $ sudo apt-get update
   $ sudo apt-get install git-repo

On distributions that use ``dnf``:
----------------------------------

.. code-block:: console

   $ sudo dnf copr enable oniroproject/tools
   $ sudo dnf --refresh install repo

On openSUSE or SUSE Enterprise Linux:
-------------------------------------

.. code-block:: console

   $ sudo zypper addrepo http://download.opensuse.org/repositories/home:/openharmony:/tools/openSUSE_Tumbleweed/home:openharmony:tools.repo
   $ sudo zypper in repo

In the `zypper addrepo` line, replace ``openSUSE_Tumbleweed`` with the distribution you're using - a list of distributions
for which the package is available `here <http://download.opensuse.org/repositories/home:/openharmony:/tools/>`_.

On Arch Linux:
--------------
Add our OBS repository to ``/etc/pacman.conf``:

.. code-block:: console

   [openharmony-tools]
   Server = http://download.opensuse.org/repositories/home:/openharmony:/tools/Arch/$arch/

Optionally, install the repository's `signing key <https://download.opensuse.org/repositories/home:/openharmony:/tools/Arch/x86_64/home_openharmony_tools_Arch.key>`_.

Then install the ``repo`` package with pacman.

On OpenMandriva:
----------------
OpenMandriva has already added |main_project_name| version of ``repo`` to its official
repositories. If you're on OpenMandriva, simply ``dnf install repo``.


The Manifests
*************

The `manifest repository <https://git.ostc-eu.org/OSTC/OHOS/manifest>`_
provides the manifest for configuring a workspace. The project provides
a single ``default.xml`` manifest file in each of the active branches.

Depending on the specific branch of the manifest repository, that manifest may
either allow some projects to follow changes by selecting git branches or pin
all projects to a specific git commit revision.

Setting up the Workspace
************************

Once the repo tool is installed, you can initialize and populate the workspace.
This will bring in all the needed sources for building |main_project_name|:

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
