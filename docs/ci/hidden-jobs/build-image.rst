.. SPDX-FileCopyrightText: Huawei Inc.
..
.. SPDX-License-Identifier: CC-BY-4.0

============
.build-image
============

The ``.build-image`` job extends the :doc:`build-recipe` job to build a single
recipe, which should describe an image, and to collect the resulting image and
licenses as job artifacts.

Usage Guide
===========

This job is configured exactly the same as :doc:`build-recipe`.

Implementation Details
======================

The job handles differences between the name of the temporary build directory
between various All Scenarios OS flavours. Internally BitBake is interrogated for
the value of ``TMPDIR`` and the image is copied back to a subdirectory
of ``$CI_PROJECT_DIR`` for delivery to the GitLab runner.
