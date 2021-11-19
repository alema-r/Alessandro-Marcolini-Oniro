# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

INHERIT += "oniro-sanity"

# Default versions of toolchain components
# (can be overridden in kernel specific files if necessary)
BINUVERSION ?= "2.37%"
GCCVERSION ?= "11.%"
GLIBCVERSION ?= "2.33%"

# This is the ugliest fix possible but it fixes the build while keeping
# meta-freertos around. meta-freertos is pulling in a dependency on
# meta-poky-bsp without a reason that we can put a finger on and given that we
# have moved to oe-core/bitbake, bringing back poky just for this, makes little
# sense. We will follow up with upstream and do either of the following:
# * fix this properly upstream
# * drop freertos support
# * bring back poky once we clarify this dependency with the maintainer
LAYERDEPENDS_meta-freertos_remove = "yoctobsp"
