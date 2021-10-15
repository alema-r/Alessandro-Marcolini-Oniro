.. SPDX-FileCopyrightText: Huawei Inc.
..
.. SPDX-License-Identifier: CC-BY-4.0

=============
.build-zephyr
=============

The ``.build-zephyr`` job extends the :doc:`bitbake-workspace` job. It sets
``CI_ONIRO_BUILD_FLAVOUR`` to ``zephyr`` and builds the bitbake targets (e.g.
images) as defined by ``CI_ONIRO_BITBAKE_TARGETS`` (defaults included).

Usage Guide
===========

This job is not intended for direct use. Instead it serves as a base for all
the Zephyr-specific :doc:`../shared-jobs`.
