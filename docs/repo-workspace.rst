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

On Ubuntu/Debian:
-----------------

.. code-block:: console

   $ sudo apt-get update
   $ sudo apt-get install repo

For Debian "contrib" repo should be [enabled](https://wiki.debian.org/SourcesList#Example_sources.list)

The repo package has not been backported to Ubuntu 20.04. PPA repository should be used for this release instead:

On Ubuntu 20.04:
----------------

.. code-block:: console

   $ sudo add-apt-repository ppa:ostc/ppa
   $ sudo apt-get update
   $ sudo apt-get install git-repo

On Distributions that Use ``dnf``:
----------------------------------

.. code-block:: console

   $ sudo dnf copr enable oniroproject/tools
   $ sudo dnf --refresh install repo

On openSUSE or SUSE Enterprise Linux:
-------------------------------------

.. code-block:: console

   $ sudo zypper addrepo https://build.opensuse.org/project/show/home:oniroproject:tools
   $ sudo zypper in repo

In the `zypper addrepo` line, replace ``openSUSE_Tumbleweed`` with the distribution you're using - a list of distributions
for which the package is available `here <https://build.opensuse.org/project/show/home:oniroproject:tools>`_.

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

The ``repo manifest`` files are part of the main `oniro repository <https://gitlab.eclipse.org/eclipse/oniro-core/oniro.git>`_
and are to be used for configuring a workspace. The project provides a single
``default.xml`` manifest file in each of the active branches.

Depending on the specific branch of the above-mentioned repository, that
manifest may either allow some projects to follow changes by selecting git
branches or pin all projects to a specific git commit revision.

Setting-up the Workspace
************************

Once the repo tool is installed, you can initialize and populate the workspace.
This will bring in all the needed sources for building |main_project_name|:

.. code-block:: console

   $ mkdir oniroproject; cd oniroproject
   $ repo init -u https://gitlab.eclipse.org/eclipse/oniro-core/oniro.git -b dunfell
   $ repo sync --no-clone-bundle

Workspace Structure
*******************

A fully set workspace, will provide a structure similar to:

.. code-block:: none

   ./oniroproject/
   ├── bitbake
   ├── docs
   ├── ip-policy
   ├── meta-openembedded
   ├── meta-raspberrypi
   ├── meta-zephyr
   ├── <various bitbake layers>
   ├── oe-core
   ├── oniro
   └── README.md

All the bitbake layers are included at the root of the workspace. See for
example ``meta-openembedded`` above.

It is recommended to use the root of the workspace for the build directories
using ``build-`` as directory name prefix.
