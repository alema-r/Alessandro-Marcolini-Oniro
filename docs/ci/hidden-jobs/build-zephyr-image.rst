.. SPDX-FileCopyrightText: Huawei Inc.
..
.. SPDX-License-Identifier: CC-BY-4.0

===================
.build-zephyr-image
===================

The ``.build-zephyr-image`` job extends the :doc:`build-image` job to collect only
the `*.bin` and `*.elf` files and remove all the other files that would
normally be collected by the artifact system. It is recommended for Zephyr
builds which produce bin and elf images.

Usage Guide
===========

This job is configured exactly the same as :doc:`build-image` and
:doc:`build-recipe`.
