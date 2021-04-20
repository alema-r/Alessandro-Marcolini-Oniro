# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

# Deprecated collections can be either completely removed or replaced by
# another layer. The format of OHOS_DEPRECATED_COLLECTIONS is a list of
# <DEPRECATED_COLLECTION>:<REPLACEMENT_COLLECTION>. When
# <REPLACEMENT_COLLECTION> is not provided, the layer was removed without a
# replacement.
OHOS_DEPRECATED_COLLECTIONS = " \
    meta-ohos-demo:meta-ohos-blueprints \
    "

# Same format as for OHOS_DEPRECATED_COLLECTIONS but for DISTRO configurations.
OHOS_DEPRECATED_DISTROS = " \
    allscenarios-linux-demo-dashboard:allscenarios-linux-blueprint-dashboard \
    "

def unpack_deprecation(elem) -> 'Tuple[str, str]':
    elem_split = elem.split(sep=':', maxsplit=1)
    return (elem_split[0], elem_split[1] if len(elem_split) == 2 else '')

def ohos_is_valid_config() -> bool:
    """Handles AllScenariOS build configuration sanity checks."""

    # By default assume no fatal errors
    success = True

    #
    # Layers deprecation
    #
    for deprecation in d.getVar('OHOS_DEPRECATED_COLLECTIONS').split():
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
    for deprecation in d.getVar('OHOS_DEPRECATED_DISTROS').split():
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

python ohos_sanity_handler() {
    if d.getVar('OHOS_SANITY_SKIP', True) == "1":
        bb.warn('AllScenariOS-specific sanity checks were skipped.')
        return
    if not ohos_is_valid_config():
        bb.fatal("AllScenariOS sanity checks included fatal errors. See above.")
}

addhandler ohos_sanity_handler
ohos_sanity_handler[eventmask] = "bb.event.BuildStarted"
