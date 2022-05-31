.. SPDX-FileCopyrightText: Huawei Inc.
..
.. SPDX-License-Identifier: CC-BY-4.0

============
.oe-selftest
============

The ``.oe-selftest`` job extends the :doc:`bitbake-workspace` job to configure
OpenEmbedded Self test environment. The job does not run any tests by itself,
actual tests should be run in the downstream test jobs.

Job Variables
=============

Oe-selftest is based on Python unitest. Tests are organized by module, class
and method. The ``.oe-selftest`` job defines two variables as a way to
customize what tests to run.

CI_ONIRO_OE_SELFTESTS
---------------------

The specific tests to run. The default value is empty. The order the tests are
running is alphabetical.


CI_ONIRO_OE_SEFLTEST_SKIPS
--------------------------

The specific tests to skip. The default value is empty.
