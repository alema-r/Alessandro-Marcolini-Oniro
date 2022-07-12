.. SPDX-FileCopyrightText: Huawei Inc.
..
.. SPDX-License-Identifier: CC-BY-4.0

===================
.build-zephyr-image
===================

The ``.build-zephyr-image`` job extends the :doc:`build-image` job to collect, by default
the `*.bin` and `*.elf` files and remove all the other files that would
normally be collected by the artifact system. The file types of the artifacts can be overridden.
This sets by default ``CI_ONIRO_BUILD_FLAVOUR`` to ``zephyr`` and ``CI_ONIRO_RECIPE_NAME``
to ``zephyr-philosophers``, the latter can be overridden to change the recipe builded by bitbake.
It is used by all Zephyr builds.

Usage Guide
===========

This job is configured exactly the same as :doc:`build-image` and
:doc:`build-recipe`.
