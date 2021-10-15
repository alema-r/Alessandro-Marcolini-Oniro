.. SPDX-FileCopyrightText: Huawei Inc.
..
.. SPDX-License-Identifier: CC-BY-4.0

============
.build-linux
============

The ``.build-linux`` job extends the :doc:`bitbake-workspace` job. It sets
``CI_ONIRO_BUILD_FLAVOUR`` to ``linux`` and builds the bitbake targets (e.g.
images) as defined by ``CI_ONIRO_BITBAKE_TARGETS`` (defaults included).

The images are built one after another, allowing CI to fail quickly in case of
any problems with the more fundamental image. The set of built images may
change over time, as additional reference images are defined.

Usage Guide
===========

This job is not intended for direct use. Instead it serves as a base for all
the Linux-specific :doc:`../shared-jobs`. It may be re-defined in a pipeline to
alter ``rules`` or ``variables`` in a way that fits a particular purpose.

If used directly, it is recommended pick the desired ``MACHINE`` and to
override the entire script section and refer to the base ``.bitbake-workspace``
job as illustrated below. The set of build operations can then be tailored to
the purpose of the desired job.

.. code-block:: yaml

  build-something-linux-specific:
    extends: .build-linux
    variables:
      MACHINE: "..."
    script:
      - !reference [.bitbake-workspace, script]
      - true # put your code here

.. note::

   ``CI_ONIRO_BITBAKE_TARGETS`` can be overwritten if defaults are not desired.
