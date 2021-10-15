.. SPDX-FileCopyrightText: Huawei Inc.
..
.. SPDX-License-Identifier: CC-BY-4.0

===================
.build-linux-matrix
===================

The ``.build-linux-matrix`` job extends the :doc:`build-linux` job and
otherwise behaves exactly the same but builds each of the
``CI_ONIRO_BITBAKE_TARGET`` as a separate job. This creates a wider pipeline
which unlocks additional parallelism.

Usage Guide
===========

Note that due to the way parallel matrix jobs are defined, to change the set of
bitbake recipes to build you must re-define the ``parallel/matrix`` element
entirely. Changing ``CI_ONIRO_BITBAKE_TARGETS`` is not effective.
