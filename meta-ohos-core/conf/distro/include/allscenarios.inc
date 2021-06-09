# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

INHERIT += "ohos-sanity"

# With our layer setup we would add any bbappend found from our
# layers.conf. This means blueprint-specific appends would leak
# into the package in our main distribution. To avoid this we
# mask out the blueprint layers in the main distro and unmask
# them only the blueprint distributions.
BBMASK += "meta-ohos/meta-ohos-blueprints/"
