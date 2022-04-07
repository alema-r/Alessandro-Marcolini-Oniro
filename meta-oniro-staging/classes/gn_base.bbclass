# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: MIT

# This class allows building GN-based projects.

DEPENDS += "gn-native ninja-native"

# General GN options, like --dotfile
GN_OPTIONS ??= ""

# GN_ARGS can be added in the recipe
GN_ARGS ?= ' \
            target_cpu="${@gn_target_arch_name(d)}" \
'

# NINJA_ARGS can be added in the recipe
NINJA_ARGS ?= ""

B = "${WORKDIR}/out"

do_configure[cleandirs] = "${B}"
gn_base_do_configure() {
    cd ${S}
    gn gen ${GN_OPTIONS} --args='${GN_ARGS}' ${B}
}

gn_base_do_compile() {
    ninja ${NINJA_ARGS} -C ${B}
}

gn_base_do_install() {
    bbfatal " \
        Missing do_install task definition! \
        GN projects don't usually follow any particular convention with regards \
        to build artifacts, therefore do_install task has to be defined in the \
        project's recipe. \
    "
}

EXPORT_FUNCTIONS do_configure do_compile do_install

# GN fails with unclear and confusing error logs when build directory is the
# same as source directory. To avoid that build directory is set to
# "S{WORKDIR}/out". Nevertheless let's make sure that B != S as devtool default
# behaviour is to override B variable to be the same as S.
#
# NOTE: devtool adds bbappend file which makes recipe to inherit externalsrc
# class with EXTERNALSRC set to workspace/sources/<recipe-name> and by default
# EXTERNALSRC_BUILD set to the same value as EXTERNALSRC. To change this
# behavior --no-same-dir option has to be passed to devtool add command.
python do_check_B_is_not_S() {
    bpath = os.path.abspath(d.expand("${B}"))
    spath = os.path.abspath(d.expand("${S}"))
    if os.path.abspath(d.expand("${S}")) == os.path.abspath(d.expand("${B}")):
        bb.fatal('''
GN requires build and sources directories to be different. By default build
directory is set to ${WORKDIR}/out. If you're using devtool remember to use
--no-same-dir option, e.g.:
devtool add --no-same-dir <your-gn-project-name> <your-gn-project-git-url>
        ''')
}

addtask check_B_is_not_S after do_patch before do_configure

# GN target architecture helpers.
#
# Determining the target architecture is more difficult, as there are many
# different values we can use on the Yocto side (e.g. TUNE_ARCH, TARGET_ARCH,
# MACHINEOVERRIDES etc). What we do is define the mapping with regular,
# non-Python variables with overrides that are generic enough (i.e. "x86"
# instead of "i586") and then use gn_target_arch_name() to return the right
# value with some validation.
GN_TARGET_ARCH_NAME:aarch64 = "arm64"
GN_TARGET_ARCH_NAME:arm = "arm"
GN_TARGET_ARCH_NAME:x86 = "x86"
GN_TARGET_ARCH_NAME:x86-64 = "x64"
GN_TARGET_ARCH_NAME:riscv32 = "riscv32"
GN_TARGET_ARCH_NAME:riscv64 = "riscv64"

def gn_target_arch_name(d):
    """Returns a GN architecture name corresponding to the target machine's
    architecture."""
    name = d.getVar("GN_TARGET_ARCH_NAME")
    if not name:
        bb.fatal('Unsupported target architecture. A valid override for the '
                 'GN_TARGET_ARCH_NAME variable could not be found.')
    return name
