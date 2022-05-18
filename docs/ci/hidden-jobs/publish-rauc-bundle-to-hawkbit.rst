.. SPDX-FileCopyrightText: Huawei Inc.
..
.. SPDX-License-Identifier: CC-BY-4.0

===============================
.publish-rauc-bundle-to-hawkbit
===============================

The `.publish-rauc-bundle-to-hawkbit` job uses `hawkbitctl` to publish a
pre-build bundle to a HawkBit instance. The bundle is uploaded as an *artifact*
to a new *software module*, which is then used to create a new *distribution
set*.

Usage Guide
===========

The `.publish-rauc-bundle-to-hawkbit` job should be paired with the
:doc:`build-rauc-bundle` job using the `needs: ...` and `dependencies: ...`
keywords, so that the bundle file is transferred as an artifact between the two
jobs, and no other artifacts are needlessly copied.

The following variables should be set in the job definition:

 - `CI_ONIRO_HAWKBIT_SWMOD_NAME` is the name of the software module to create.
 - `CI_ONIRO_HAWKBIT_DS_NAME` is the name of the distribution set to create.
 - `CI_ONIRO_RAUC_BUNDLE` is the name of the Yocto recipe responsible for the bundle.
 - `MACHINE` is the Yocto machine variable.

Note that the job relies on the presence of two artifact files: the bundle
itself, which contains a timestamp-based version string, and the symbolic link
pointing to it. The job that builds the bundle handles this automatically but
custom jobs may need additional logic to preserve both files.

The job will run automatically for tags, scheduled pipelines and for merge
requests. Branch pipelines are excluded to avoid publishing extensive number of
artifacts.
