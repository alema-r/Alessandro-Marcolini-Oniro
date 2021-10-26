.. SPDX-FileCopyrightText: Huawei Inc.
..
.. SPDX-License-Identifier: CC-BY-4.0

==================
.bitbake-workspace
==================

The ``.bitbake-workspace`` job extends the :doc:`workspace` job to configure
and initialize BitBake for the desired build operations. The job does not build
anything by itself, that functionality is available in the ``.build-recipe``
job.

Job Variables
=============

The ``.bitbake-workspace`` job defines several variables as a way to customize
the way BitBake is configured.

CI_ONIRO_BUILD_FLAVOUR
----------------------

The name of the *flavour* of Oniro OS, which effectively picks the kernel
type. This is used to select the initial BitBake configuration template.
Templates are stored in the **oniro** repository.

Available values are ``linux``, ``zephyr`` and ``freertos``. There is no
default value. This variable must be set by a derivative job, it is usually set
by the three ``.build-linux``, ``.build-zephyr`` and ``.build-freertos`` jobs.
Specific build jobs, in turn, extend those.

CI_ONIRO_BUILD_CACHE
--------------------

The set of build caches to use.

Currently there are only two sets ``private`` or ``pub``. The names directly
correspond to file system path where the cache is stored.

By default all builds are assumed to be tainted, and use the private cache. If
a given build configuration is not legally problematic, in the sense of pulling
in code or blobs that are non-redistributable, this attribute can be set to
``pub``.

Public build cache is exposed as `<https://cache.ostc-eu.org/>`_ and can be
used by third parties to speed up local builds.

The default value is ``private``.

CI_ONIRO_DEVTOOL_RECIPE_NAME
----------------------------

Name of the BitBake recipe to upgrade.

This may be set by jobs which extend or re-define the ``.bitbake-workspace``
job. It must be used in tandem with ``CI_ONIRO_DEVTOOL_LAYER_PATH``. The side
effects are discussed below.

CI_ONIRO_DEVTOOL_LAYER_PATH
---------------------------

Path of the layer containing the recipe to upgrade.

This may be set by jobs which extend or re-define the ``.bitbake-workspace``
job. It must be used in tandem with ``CI_ONIRO_DEVTOOL_RECIPE_NAME``.

Before the build is attempted, the recipe is updated to ignore any existing
patches and point to the code corresponding to the repository and commit being
tested.

.. code-block:: bash

    devtool upgrade \
        --no-patch \
        --srcrev "$CI_COMMIT_SHA" \
        --srcbranch "$CI_COMMIT_REF_NAME" \
        "$CI_ONIRO_DEVTOOL_RECIPE_NAME"
    devtool finish \
        --remove-work \
        --force \
        "$CI_ONIRO_DEVTOOL_RECIPE_NAME" \
        "$(basename "$CI_ONIRO_DEVTOOL_LAYER_PATH")"

This functionality is useful for testing incoming changes to repositories that
contain source code that is already packaged one of the layers.

Configuring BitBake
===================

The ``local.conf`` file can define numerous variables that influence the
BitBake build process. This job offers a declarative method of doing that. Job
variables with have the prefix ``CI_ONIRO_BB_LOCAL_CONF_`` are converted to
``attr = "value"`` and those with prefix
``CI_ONIRO_BB_LOCAL_CONF_plus_equals_`` are converted to ``attr += "value"``.

This method is friendly to job inheritance and re-definition. Derivative jobs
can add or re-define variables without having to duplicate any imperative logic
or maintaining synchronized settings across distinct jobs.

This mechanism is used to set the following BitBake variables

CONNECTIVITY_CHECK_URIS
-----------------------

BitBake contains a connectivity check system. That system relies on access to
``https://example.com/``. OSTC cloud provider has a DNS configuration problem,
where that specific domain is not resolved correctly. For a compatible
workaround the connectivity check URL is set to ``https://example.net/``.

This is implemented using the ``CI_ONIRO_BB_LOCAL_CONF_`` system.

DL_DIR
------

BitBake downloads source archives and git repositories in the through the
``fetch`` task of many recipes. Those are all stored in the local file system
and can be shared between separate builds for a great speed up, as the files
are obtained from multiple third party servers and their connectivity varies.

To optimize the build process, the download directory is set to point at the
shared NFS volume that persists between job execution, and is more efficient
than the artifact system, that copies all the data regardless of the need to
actually use that data in practice.

The default location is changed to
``/var/shared/$CI_ONIRO_BUILD_CACHE/bitbake/downloads``, but this should
be treated as an implementation detail. The location may change in the future.
The download cache is not automatically purged yet. In the future it may be
purged periodically, if space becomes an issue.

Note that the location relies on the value of ``$CI_ONIRO_BUILD_CACHE``
discussed above.

SSTATE_CACHE
------------

BitBake relies on an elaborate cache system, that can be used to avoid
duplicating work at the level of a specific recipe. The dependencies and
side-effects of each recipe are recorded in the cache, and are reused whenever
possible.

Having access to a persistent cache has a dramatic effect on the performance of
the CI system as, in the fast-path, it can avoid virtually all compilation
tasks and simply assemble the desired system image out of intermediate files
present in the cache.

The default location is changed to
``/var/shared/$CI_ONIRO_BUILD_CACHE/bitbake/sstate-cache``, but this
should be treated as an implementation detail. The location may change in the
future.  The sstate cache is not automatically purged yet. It can be purged
periodically with the only caveat, that initial builds will be much slower.

Cache Considerations
====================

The ``.bitbake-workspace`` job configures BitBake to use a persistent directory
that is shared between CI jobs, for the location of the ``download`` directory
as well as the ``sstate-cache`` directory.

The job is using GitLab runner tags to schedule jobs in the environment where
that shared storage is available. When a new dependency is added or when the
layers and recipes are changed or updated, the download is automatically
populated with the necessary source archives. Similarly ``sstate-cache`` is
populated by all the build jobs present throughout the CI system.

Due to legal restrictions, the caches are split into two pairs, public and private.
The public cache is automatically published in https://cache.ostc-eu.org/bitbake/
The private cache, which is used by default, is available on the same volume but it is
not shared anywhere.

In case the cache is fed with a software package that is, in retrospective
somehow problematic, for example, by not being freely redistributable, the cache
can be purged at will.

For details on how cache selection and BitBake configuration looks like, please
refer to the pipeline source code.
