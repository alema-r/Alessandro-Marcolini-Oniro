.. SPDX-FileCopyrightText: Huawei Inc.
..
.. SPDX-License-Identifier: CC-BY-4.0

================
.build-wic-image
================

The ``.build-wic-image`` job extends the :doc:`build-image` job to collect only
the `*.wic.*` and `*.bmap` files and remove all the other files that would
normally be collected by the artifact system. It is recommended for Linux
builds which produce wic images, as the size of subset of collected artifacts
is considerably smaller than what `.build-image` provides.

Usage Guide
===========

This job is configured exactly the same as :doc:`build-image` and
:doc:`build-recipe`.
