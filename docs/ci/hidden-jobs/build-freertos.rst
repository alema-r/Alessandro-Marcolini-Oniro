.. SPDX-FileCopyrightText: Huawei Inc.
..
.. SPDX-License-Identifier: CC-BY-4.0

===============
.build-freertos
===============

The ``.build-freertos`` job extends the :doc:`bitbake-workspace` job. It sets
``CI_ONIRO_BUILD_FLAVOUR`` to ``freertos`` and builds the ``freertos-demo``
test image.

Usage Guide
===========

This job is not intended for direct use. Instead it serves as a base for all
the FreeRTOS-specific :doc:`../machines-and-flavours`.
