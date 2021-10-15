.. SPDX-FileCopyrightText: Huawei Inc.
..
.. SPDX-License-Identifier: CC-BY-4.0

===============
.aggregate-docs
===============

The ``.aggregate-docs`` job triggers a build of the aggregated documentation of
All Scenarios OS.

All Scenarios OS documentation is maintained in an unusual way. Individual
repositories contain dedicated documentation that can be built with
:doc`build-docs` job. A special *centralized* documentation project aggregates
documentation from multiple git repositories, as checked out by *git-repo* based
on the *manifest* file, to build a standalone document.

This job encapsulates knowledge on how to trigger the aggregated build upon
changes to documentation specific to a given project.

Usage Guide
===========


.. warning::
   If a project is pinned in the *git-repo manifest* then using
   this job makes no sense, as the resulting aggregate will not
   change without a prior modification of the manifest.

To use this job in your pipeline include the generic build definition file,
``build-generic.yaml`` and define the ``aggregate-docs`` job in addition to the
:doc:`build-docs` job.

.. code-block:: yaml

    # Build documentation present in the repository.
    build-docs:
      extends: .build-docs

    # Trigger aggregation of All Scenarios OS documentation.
    aggregate-docs:
      extends: .aggregate-docs
      needs: [build-docs]
