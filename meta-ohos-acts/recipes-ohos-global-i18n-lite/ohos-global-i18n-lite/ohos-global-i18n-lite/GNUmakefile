# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

include z.mk

Project.Name=ohos-global-i18n-lite
Project.Version=1

$(eval $(call ZMK.Import,Configure))

libglobal_i18n.so: CPPFLAGS += -iquote $(ZMK.OutOfTreeSourcePath)interfaces/kits/i18n/include
libglobal_i18n.so: CPPFLAGS += -iquote $(ZMK.OutOfTreeSourcePath)frameworks/i18n/include
libglobal_i18n.so: LDLIBS += -lsec
libglobal_i18n.so.Sources = $(wildcard $(ZMK.OutOfTreeSourcePath)frameworks/i18n/src/*.cpp)
$(eval $(call ZMK.Expand,Library.So,libglobal_i18n.so))

# Public header files
global-i18n-lite-headers-public.InstallDir = $(includedir)
global-i18n-lite-headers-public.Headers = $(wildcard $(ZMK.OutOfTreeSourcePath)interfaces/kits/i18n/include/*.h)
$(eval $(call ZMK.Expand,HeaderGroup,global-i18n-lite-headers-public))

# XXX: OHOS locale/i18n resource data base file that is expected to be present
# in the /storage/data directory on the target. There are others expectations
# regarding this particular location, e.g. that it is mounted on a jffs2 fs.
# To avoid unnecessary rootfs structure constraints placing it in
# /usr/share/ohos-global-i18n-lite location for the time being to be put in an
# appropriate place by the ACTS global_lite test suite spread task.
frameworks/i18n/i18n.dat.InstallDir = $(datadir)/$(Project.Name)
# FIXME: This installs a binary blob that's required by the shard library.
# Issue on gitee: https://gitee.com/openharmony/global_i18n_lite/issues/I3HZXS
$(eval $(call ZMK.Expand,InstallUninstall,frameworks/i18n/i18n.dat))