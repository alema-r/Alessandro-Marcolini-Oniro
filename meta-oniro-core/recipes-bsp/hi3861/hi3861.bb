# BIG FAT WARNING
# This recipe is plain old WRONG
# It's just there as a workaround to get things
# building with a properly built toolchain.
# THIS NEEDS TO GO AWAY

SRC_URI += " \
	git://gitee.com/openharmony/device_hisilicon_hispark_pegasus.git;protocol=http \
	file://pegasus-make-wifiiot.patch \
" 

SRCREV = "master"
LICENSE = "MulanPSL-2.0"
LIC_FILES_CHKSUM = "file://NOTICE;md5=d8d6d3af7fa6ad0de5ba69a3520963f8"
S="${WORKDIR}/git"
DEPENDS = "libgcc"
# No clang support until the build system is fixed
TOOLCHAIN = "gcc"

inherit siteinfo
inherit siteconfig

do_compile () {
	# Use the correct toolchain
	TRIPLET=$(echo ${TARGET_PREFIX} |sed -e 's,-$,,')
	if [ "$TRIPLET" != "riscv32-unknown-elf" ]; then
		sed -i -e "s,riscv32-unknown-elf,$TRIPLET,g" sdk_liteos/build/make_scripts/usr.mk sdk_liteos/build/scripts/scons_env_cfg.py sdk_liteos/build/scripts/scons_utils.py sdk_liteos/config.gni sdk_liteos/factory.mk sdk_liteos/non_factory.mk
	fi

	# Passing -nostartfiles to ld is broken (and no longer accepted by current
	# toolchains). -nostartfiles goes to cc, not ld.
	sed -i -e 's, -nostartfiles,,' sdk_liteos/boot/loaderboot/module_config.mk sdk_liteos/boot/flashboot/module_config.mk sdk_liteos/build/make_scripts/config.mk 
	sed -i -e 's|, "-nostartfiles"||' sdk_liteos/boot/loaderboot/SConscript sdk_liteos/boot/flashboot/SConscript
	sed -i -e 's|'-nostartfiles', ||' sdk_liteos/build/scripts/common_env.py

	# Locate libgcc correctly
	GCCVER="$(${TARGET_PREFIX}gcc --version |head -n1 |cut -d' ' -f3)"
	sed -i -e "s,7\.3\.0,${GCCVER}," sdk_liteos/build/make_scripts/usr.mk sdk_liteos/build/scripts/scons_env_cfg.py
	LIBGCC_PATH="${RECIPE_SYSROOT}/usr/lib/${TRIPLET}/${GCCVER}"
	sed -i -e "s|-lgcc|-L${LIBGCC_PATH} -lgcc|" sdk_liteos/build/make_scripts/config.mk

	cd sdk_liteos
	make all
}
