.. SPDX-FileCopyrightText: Huawei Inc.
..
.. SPDX-License-Identifier: CC-BY-4.0

Disk Partition Table
####################

.. contents::
   :depth: 2

Overview
********

The OS defines the partitions included as part of the Linux-based distro as it
follows:

* boot

  * filesystem label: x-boot (partition name when relevant)

  * It provides boot artefacts required by the lower bootloader assumptions. It
    is device-specific both in terms of filesystem and content.

* sys-a

  * filesystem label: x-sys-a (partition name when relevant)

  * It provides the root filesystem hierarchy.

  * Filesystem type, configuration and structure are device-independent.

  * This partition is the only one provided with a redundant counterpart (see
    below).

* sys-b

  * filesystem label: x-sys-b (partition name when relevant)

  * It provides a redundant root filesystem hierarchy used as part of the
    system update strategies.

  * Filesystem type, configuration and structure are device-independent.

* dev-data

  * filesystem label: x-dev-data (partition name when relevant)

  * Device-specific data meant to be preserved over system reset (factory
    reset).

  * The runtime will completely treat this data read-only.

* sys-data

  * filesystem label: x-sys-data (partition name when relevant)

  * This partition holds the system state to deal with the root filesystem as a
    read-only asset.

  * It ties closely into the system update strategies.

  * Data is kept over system updates (subject to state transition hooks) but
    discarded over factory reset.

* app-data

  * filesystem label: x-app-data (partition name when relevant)

  * This partition provides application data storage.

  * Data is kept over system updates (subject to state transition hooks) but
    discarded over factory reset.

The build system tries to unify the partition as much as possible, leaving
upper layers (for example, the system update layers) with as few deviations to
deal with as feasible. This means that filesystem labels and partition names
are to be assumed by the OS components.

Partition Table
***************

The OS will support both MBR and GPT as partition table type. In this way, the
OS can achieve more extensive device support.

The OS assumes a GPT disk layout as it follows:

* 4MiB is left untouched at the start of the disk (to accommodate for
  hardware-specific requirements).

* All partitions are aligned to 4MiB.

* The filesystem labels and partition names are as described above.

On the MBR side, the disk layout is similar to GPT. The design mainly
workarounds the four physical partitions limitation:

* 4MiB is left untouched at the start of the disk (to accommodate for
  hardware-specific requirements).

* All partitions are aligned to 4MiB.

* The filesystem labels and partition names are as described above.

* The 4th partition is defined as extended and contains all the data partitions
  (dev-data, sys-data and app-data).
