# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: MIT

# This class allows building GN-based projects.
# GN toolchain definition file for Yocto toolchain is generated using
# write_toolchain_file function originating from the meta-browser layer. This
# Yocto toolchain definition relies on the toolchain template of a given
# project. GN_TOOLCHAIN_TMPL_FILE variable defines the location of toolchain
# template in a GN label syntax. Generated Yocto toolchain has to be set as
# default toolchain by patching project's BUILDCONFIG.gn file. Also special
# yocto_flags config containing all Yocto toolchain flags is generated. This
# config have to be added to default configs of all targets using set_defaults
# function. For more information please refer do_check_yocto_toolchain_is_used
# task below. Lastly, recipe has to provide do_install task.

inherit gn_base

# Location of the GN toolchain template file, used in write_toolchain_file(). It
# can be overridden in the recipe as needed. The name of the GN toolchain
# template does not matter and so by default gcc_toolchain template is used even
# for clang compiler
GN_TOOLCHAIN_TMPL_FILE ??= "//build/toolchain/gcc_toolchain.gni"

# Project's GN toolchains labels that should be used by the generated Yocto
# toolchain definition - can be overridden in the recipe
GN_TARGET_TOOLCHAIN_TMPL_LABEL ??= "gcc_toolchain"
GN_HOST_TOOLCHAIN_TMPL_LABEL ??= "gcc_toolchain"

python do_write_gn_toolchain_file () {
    root_gn_dir = d.expand("${S}")
    # Do not modify the below part: for simplicity hardcoded GN references:
    # //build/toolchain/yocto:yocto_{target,native,flags} are used in a couple
    # of places including this class implementation and patchwork for projects
    # using this class
    toolchain_dir = os.path.join(root_gn_dir, "build", "toolchain", "yocto")
    write_toolchain_files(d, toolchain_dir)
}

addtask write_gn_toolchain_file after do_patch before do_configure

do_check_yocto_toolchain_is_used() {
    cd "${S}"
    DEFAULT_TARGET_TOOLCHAIN=$(gn desc ${B} "//build/toolchain/yocto:yocto_flags" | \
        grep "toolchain: //build/toolchain/yocto/target:yocto_target") || true
    LIST_OF_GN_TARGETS_USING_YOCTO_FLAGS=$(gn refs -q ${B} \
        "//build/toolchain/yocto:yocto_flags") || true
    if test -z "$DEFAULT_TARGET_TOOLCHAIN" || \
        test -z "$LIST_OF_GN_TARGETS_USING_YOCTO_FLAGS"
    then
        bbfatal "\
No reference to yocto_target or yocto_flags! \
You have to patch project's default toolchain and targets defaults! Those are \
found in the BUILDCONFIG.gn file. Toolchain is set by the set_default_toolchain \
function call and targets defaults by a series of calls to set_defaults \
function one per target type, like executable, shared_library, etc. Typical \
approach is to change default toolchain to (sample patch): \n\
\n\
--- a/build/config/BUILDCONFIG.gn\n\
+++ b/build/config/BUILDCONFIG.gn\n\
- set_default_toolchain(_default_toolchain)\n\
+ set_default_toolchain(\"//build/toolchain/yocto:yocto_target\")\n\
\n\
and append flags to all defaults' config (sample patch): \n\
\n\
--- a/build/config/BUILDCONFIG.gn\n\
+++ b/build/config/BUILDCONFIG.gn\n\
set_defaults(\"shared_library\") {\n\
-  configs = default_configs\n\
+  configs =\n\
+    default_configs +\n\
+    [ \"//build/toolchain/yocto:yocto_flags\" ]\n\
}\n\
\n\
        "
    fi
}

addtask do_check_yocto_toolchain_is_used after do_configure before do_compile

def is_clang(cc: str) -> bool:
    """ Returns True when the argument (cc) string contains the word `clang`;
    False otherwise"""
    cc_basename: str = cc.split()[0]
    return "clang" in cc_basename

def gn_bool(arg: bool) -> str:
    """ Returns GN-compliant lowercase boolean string from argument (arg) """
    return str(arg).lower()

def gn_list(space_separated_str: str) -> str:
    """ Returns GN-formatted list string:
        ["item1", "item2"]
        string extracted from a space-separated string argument
        (space_separated_str) """
    return str(space_separated_str.split()).replace("'", "\"")

# Following functions originally come from the meta-browser layer:
# https://github.com/OSSystems/meta-browser

# GN host architecture helpers.
#
# BUILD_ARCH's value corresponds to what uname returns as the machine name.
# The mapping in gn_host_arch_name() tries to match several possible values
# returned by the Linux kernel in uname(2) into the corresponding values GN
# understands.
def gn_host_arch_name(d):
    """Returns a GN architecture name corresponding to the build host's machine
    architecture."""
    import re
    arch_translations = {
        r'aarch64.*': 'arm64',
        r'arm.*': 'arm',
        r'i[3456]86$': 'x86',
        r'x86_64$': 'x64',
        r'riscv32$': 'riscv32',
        r'riscv64$': 'riscv64',
    }
    build_arch = d.getVar("BUILD_ARCH")
    for arch_regexp, gn_arch_name in arch_translations.items():
        if re.match(arch_regexp, build_arch):
            return gn_arch_name
    bb.fatal('Unsuported BUILD_ARCH value: "%s"' % build_arch)

def gn_toolchain_file_header(d):
    """Reurns GN toolchain file header as a multi-line string"""

    toolchain_tmpl_file = d.expand('${GN_TOOLCHAIN_TMPL_FILE}')
    return str(
        '# This file has been generated automatically.\n'
        '\n'
        f'import("{toolchain_tmpl_file}")\n'
        '\n'
    )

def gn_toolchain_flags(d):
    """Returns GN config containing Yocto flags as a multi-line string"""

    yocto_target_cflags = gn_list(d.expand('${TARGET_CPPFLAGS} ${TARGET_CFLAGS}'))
    yocto_target_cflags_cc = gn_list(d.expand('${TARGET_CXXFLAGS}'))
    yocto_target_ldflags = gn_list(d.expand('${TARGET_LDFLAGS}'))
    yocto_native_cflags = gn_list(d.expand('${BUILD_CPPFLAGS} ${BUILD_CFLAGS}'))
    yocto_native_cflags_cc = gn_list(d.expand('${BUILD_CXXFLAGS}'))
    yocto_native_ldflags = gn_list(d.expand('${BUILD_LDFLAGS}'))

    return str(
        'config("yocto_flags") {\n'
        '  if (current_toolchain == "//build/toolchain/yocto/target:yocto_target") {\n'
        f'    cflags = {yocto_target_cflags}\n'
        f'    cflags_cc = {yocto_target_cflags_cc}\n'
        f'    ldflags = {yocto_target_ldflags}\n'
        '  } else if (current_toolchain == "//build/toolchain/yocto/native:yocto_native") {\n'
        f'    cflags = {yocto_native_cflags}\n'
        f'    cflags_cc = {yocto_native_cflags_cc}\n'
        f'    ldflags = {yocto_native_ldflags}\n'
        '  }\n'
        '}\n'
        '\n'
    )

def gn_toolchain(toolchain_args):
    """Returns GN toolchain definition based on provided (toolchain_args)
    dictionary variable as a multi-line string"""

    # Using template to avoid having to escape curly braces in str.format
    from string import Template
    return Template(
        '${toolchain_tmpl_name}("${toolchain_name}") {\n'
        '  cc = "${cc}"\n'
        '  cxx = "${cxx}"\n'
        '  ar = "${ar}"\n'
        '  asm = "${asm}"\n'
        '  ld = cxx  # GN expects a compiler, not a linker.\n'
        '  toolchain_args = {\n'
        '    current_cpu = "${current_cpu}"\n'
        '    current_os = "linux"\n'
        '    is_clang = ${is_clang}\n'
        '  }\n'
        '  # Below tools are not used by the GN, still some projects rely\n'
        '  # on those being present in the toolchain definition.\n'
        '  # Marking all of those as "not_needed" to avoid GN errors\n'
        '  nm = "${nm}"\n'
        '  readelf = "${readelf}"\n'
        '  ranlib = "${ranlib}"\n'
        '  strip = "${strip}"\n'
        '  not_needed("*")\n'
        '}\n'
    ).substitute(toolchain_args)

def gn_toolchain_native(d):
    """Returns GN toolchain definition based on Yocto native toolchain"""

    native_toolchain_args = {
        'toolchain_tmpl_name': d.expand('${GN_HOST_TOOLCHAIN_TMPL_LABEL}'),
        'toolchain_name': 'yocto_native',
        'current_cpu': gn_host_arch_name(d),
        'cc': d.expand('${BUILD_CC}'),
        'cxx': d.expand('${BUILD_CXX}'),
        'ar': d.expand('${BUILD_AR}'),
        'asm': d.expand('${BUILD_AS}'),
        'nm': d.expand('${BUILD_NM}'),
        'readelf': d.expand('${BUILD_PREFIX}readelf'),
        'ranlib': d.expand('${BUILD_RANLIB}'),
        'strip': d.expand('${BUILD_STRIP}'),
        'is_clang': gn_bool(is_clang(d.expand('${BUILD_CC}')))
    }

    return gn_toolchain(native_toolchain_args)

def gn_toolchain_target(d):
    """Returns GN toolchain definition based on Yocto target toolchain"""

    target_toolchain_args = {
        'toolchain_tmpl_name': d.expand('${GN_TARGET_TOOLCHAIN_TMPL_LABEL}'),
        'toolchain_name': 'yocto_target',
        'current_cpu': gn_target_arch_name(d),
        'cc': d.expand('${CC}'),
        'cxx': d.expand('${CXX}'),
        'ar': d.expand('${AR}'),
        'asm': d.expand('${AS}'),
        'nm': d.expand('${NM}'),
        'readelf': d.expand('${TARGET_PREFIX}readelf'),
        'ranlib': d.expand('${RANLIB}'),
        'strip': d.expand('${STRIP}'),
        'is_clang': gn_bool(is_clang(d.expand('${CC}')))
    }

    return gn_toolchain(target_toolchain_args)

def write_toolchain_files(d, toolchain_dir):
    """Creates cflags BUILD.gn file, target and native GN toolchain files
    in respective subdirectories of the |toolchain_dir_path|."""

    bb.utils.mkdirhier(toolchain_dir)
    toolchain_flags_file = os.path.join(toolchain_dir, "BUILD.gn")
    with open(toolchain_flags_file, 'w') as toolchain_file:
        toolchain_file.write(gn_toolchain_flags(d))

    target_toolchain_dir = os.path.join(toolchain_dir, "target")
    bb.utils.mkdirhier(target_toolchain_dir)
    target_toolchain_file = os.path.join(target_toolchain_dir,  "BUILD.gn")
    with open(target_toolchain_file, 'w') as toolchain_file:
        toolchain_file.write(gn_toolchain_file_header(d))
        toolchain_file.write(gn_toolchain_target(d))

    native_toolchain_dir = os.path.join(toolchain_dir, "native")
    bb.utils.mkdirhier(native_toolchain_dir)
    native_toolchain_file = os.path.join(native_toolchain_dir, "BUILD.gn")
    with open(native_toolchain_file, 'w') as toolchain_file:
        toolchain_file.write(gn_toolchain_file_header(d))
        toolchain_file.write(gn_toolchain_native(d))
