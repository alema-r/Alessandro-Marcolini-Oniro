.. SPDX-FileCopyrightText: Huawei Inc.
..
.. SPDX-License-Identifier: CC-BY-4.0

=============
.build-recipe
=============

The ``.build-recipe`` job extends the :doc:`bitbake-workspace` job to build a
single BitBake recipe. The recipe is built and all the results are discarded.

Variables
=========

CI_ONIRO_RECIPE_NAME
--------------------

The name of the recipe to build.

There is no default value, this must be set by the extending job.

Usage Guide
===========

To use this job in your pipeline include the generic build definition file,
``build-generic.yaml`` and define a job with the minimal configuration, as
illustrated below:

.. code-block:: yaml

  build-something:
    extends: .build-recipe
    variables:
      CI_ONIRO_BUILD_FLAVOUR: "..."
      CI_ONIRO_RECIPE_NAME: "..."
      MACHINE: "..."

Pick the desired ``CI_ONIRO_BUILD_FLAVOUR``, ``CI_ONIRO_RECIPE_NAME`` and
``MACHINE``.  For discussion of ``CI_ONIRO_BUILD_FLAVOUR`` refer to the
:doc:`bitbake-workspace` job.  ``CI_ONIRO_RECIPE_NAME`` is any BitBake recipe
available in the selected flavour. ``MACHINE`` is desired machine name.
Supported values are documented alongside each flavour in the *meta-ohos*
repository.
