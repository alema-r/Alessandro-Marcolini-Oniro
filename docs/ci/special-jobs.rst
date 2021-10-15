.. SPDX-FileCopyrightText: Huawei Inc.
..
.. SPDX-License-Identifier: CC-BY-4.0

Special Jobs
------------

linux-glibc-qemu-x86_64
.......................

This job extends ``linux-qemu-x86_64`` and differs in the following way.

This job performs a build with the libc switched to ``glibc``. It only runs on a
schedule that is defined in the GitLab project settings for the `manifest`
repository. In practice it runs daily to check if the Linux favour could be
switched back to ``glibc``, from the default ``musl`` that is used right now.
