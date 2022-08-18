# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

# Deprecated collections can be either completely removed or replaced by
# another layer. The format of ONIRO_DEPRECATED_COLLECTIONS is a list of
# <DEPRECATED_COLLECTION>:<REPLACEMENT_COLLECTION>. When
# <REPLACEMENT_COLLECTION> is not provided, the layer was removed without a
# replacement.
ONIRO_DEPRECATED_COLLECTIONS = " \
    "

# Same format as for ONIRO_DEPRECATED_COLLECTIONS but for DISTRO configurations.
ONIRO_DEPRECATED_DISTROS = " \
    "

def unpack_deprecation(elem) -> 'Tuple[str, str]':
    elem_split = elem.split(sep=':', maxsplit=1)
    return (elem_split[0], elem_split[1] if len(elem_split) == 2 else '')

def oniro_is_valid_config() -> bool:
    """Handles Oniro Project build configuration sanity checks."""

    # By default assume no fatal errors
    success = True

    #
    # Layers deprecation
    #
    for deprecation in d.getVar('ONIRO_DEPRECATED_COLLECTIONS').split():
        (deprecated, new) = unpack_deprecation(deprecation)
        if deprecated in d.getVar('BBFILE_COLLECTIONS').split():
            if new:
                if new in d.getVar('BBFILE_COLLECTIONS').split():
                    bb.warn("bblayers.conf contains both deprecated ({0}) "
                             "and replaced ({1}) layers. Remove the "
                             "deprecated layer." \
                             .format(deprecated, new))
                    # Having both deprecated and replacement layers is
                    # unwanted.
                    success = False
                msg = "{0} is a deprecated layer. Adapt your bblayers.conf " \
                      "to use the {1} replacement.".format(deprecated, new)
            else:
                msg = "{0} is a deprecated layer. Adapt your bblayers.conf " \
                      "accordingly.".format(deprecated)
            bb.warn(msg)

    #
    # DISTRO deprecation
    #
    for deprecation in d.getVar('ONIRO_DEPRECATED_DISTROS').split():
        (deprecated, new) = unpack_deprecation(deprecation)
        if deprecated == d.getVar('DISTRO'):
            if new:
                msg = "{0} is a deprecated distro configuration. Use the {1} " \
                      "replacement.".format(deprecated, new)
            else:
                msg = "{0} is a deprecated distro configuration." \
                       .format(deprecated)
            bb.warn(msg)

    return success

python oniro_sanity_handler() {
    if d.getVar('ONIRO_SANITY_SKIP', True) == "1":
        bb.warn('Oniro-specific sanity checks were skipped.')
        return
    if not oniro_is_valid_config():
        bb.fatal("Oniro Project sanity checks included fatal errors. See above.")
}

addhandler oniro_sanity_handler
oniro_sanity_handler[eventmask] = "bb.event.BuildStarted"

#
# Build configuration checks and migrations
#

# Ensure our function runs after the OE-Core's one.
BBLAYERS_CONF_UPDATE_FUNCS += "conf/bblayers.conf:ONIRO_LAYERS_CONF_VERSION:ONIRO_REQUIRED_LAYERS_CONF_VERSION:oniro_update_bblayersconf"

# All flavours take advanatge of the same bblayers.conf.sample file. The linux
# one is the actual file while all the others are symlinks to it.
ONIRO_LAYERS_CONF_SAMPLE = "${ONIRO_COREBASE}/flavours/${ONIRO_FLAVOUR}/bblayers.conf.sample"

python oniro_update_bblayersconf() {
    version = int(d.getVar('ONIRO_LAYERS_CONF_VERSION', True) or -1)
    required_version = int(d.getVar('ONIRO_REQUIRED_LAYERS_CONF_VERSION', True) or -1)
    upgrade_fail_msg = """You need to update the build's bblayers.conf manually for this version transition.

Compare your build\'s bblayers.conf with the Oniro\'s bblayers.conf.sample and merge
any changes before continuing. You can take advantage of a tool like "${SANITY_DIFF_TOOL}":

"${SANITY_DIFF_TOOL} conf/bblayers.conf ${ONIRO_LAYERS_CONF_SAMPLE}"

Note:
 Once ONIRO_LAYERS_CONF_VERSION (currently ${ONIRO_LAYERS_CONF_VERSION}) value in conf/bblayers.conf matches the
 ONIRO_REQUIRED_LAYERS_CONF_VERSION (currently ${ONIRO_REQUIRED_LAYERS_CONF_VERSION}) value in the Oniro build metadata,
 this error will go away.
"""
    upgrade_fail_msg = d.expand(upgrade_fail_msg)
    downgrade_fail_msg = """Your build's bblayers.conf was generated from a newer build metadata.

Compare your build\'s bblayers.conf with the Oniro\'s bblayers.conf.sample and merge
any changes before continuing. You can take advantage of a tool like \"${SANITY_DIFF_TOOL}\":

${SANITY_DIFF_TOOL} conf/bblayers.conf ${ONIRO_LAYERS_CONF_SAMPLE}

Note:
 Once ONIRO_LAYERS_CONF_VERSION (currently ${ONIRO_LAYERS_CONF_VERSION}) value in conf/bblayers.conf matches the
 ONIRO_REQUIRED_LAYERS_CONF_VERSION (currently ${ONIRO_REQUIRED_LAYERS_CONF_VERSION}) value in the Oniro build metadata,
 this error will go away.
"""
    downgrade_fail_msg = d.expand(downgrade_fail_msg)

    if version == -1 or required_version == -1:
        bb.fatal(upgrade_fail_msg)
    elif version < required_version:
        bb.fatal(upgrade_fail_msg)
    elif version > required_version:
        bb.fatal(downgrade_fail_msg)

    bb.fatal("You need to update bblayers.conf manually for this version transition.")
}
