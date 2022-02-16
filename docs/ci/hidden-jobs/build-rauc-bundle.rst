.. SPDX-FileCopyrightText: Huawei Inc.
..
.. SPDX-License-Identifier: CC-BY-4.0

==================
.build-rauc-bundle
==================

The ``.build-rauc-bundle`` job extends the :doc:`build-image` job to collect
only the `*.raucb.*` file and remove all the other files that would normally be
collected by the artifact system. It is recommended for Linux builds which
produce RAUC update bundles.

Usage Guide
===========

This job is configured exactly the same as :doc:`build-image` and
:doc:`build-recipe`.
