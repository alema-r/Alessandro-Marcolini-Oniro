# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

# This class provides support for defining, at the level of the recipe, the
# required writable paths. It provides support for maintaining persistent or
# volatile state at runtime for read-only filesystems.
#
# To take advantage of the provided functionality, one would define a set of
# writable as part of WRITABLES:
# WRITABLES = "logs database misc"
#
# Each writable's associated path is defined using a VarFlag:
#
# WRITABLE_PATH[logs] = "/foo/bar/logs"
# WRITABLE_PATH[database] = "/foo/bar/db"
# WRITABLE_PATH[misc] = "/foo/bar/misc"
#
# Each WRITABLE_PATH is a directory.
#
# By default, each writable is of type persistent (it will use a bind mount
# from the state partition to the writable's path). This can be switched to
# volatile by defining:
#
# WRITABLE_TYPE[logs] = "volatile"
#
# The supported types are "volatile" and "persistent".
#
# This will use a tmpfs for the provided writable path.

inherit systemd

# The read-write area needs to be provided by the OS in the form of a rw
# mountpoint handled with systemd mount unit. In this way the state/writable
# systemd mount units this class generates, will have the correct dependency on
# having the read-write partition mounted.
SYSTEM_STATE_RUNTIME_MOUNTPOINT ??= "/var/run/mount/sysdata"
SYSTEM_STATE_MOUNT_UNIT ??= "run-mount-sysdata.mount"

# The mount units depend on having the system state partition mounted at a
# known location as described above. The respective system partition mount
# units are part of the oniro-mounts package. This provides the
# SYSTEM_STATE_MOUNT_UNIT systemd mount unit.
RDEPENDS_${PN} += "oniro-mounts"

# This is the root filesystem hierarchy used as part of the bind mount units to
# provide read-write locations.
SYSDATA_OVERLAY ??= "rootfs-overlay"

def escapeSystemdUnitName(path):
    escapeMap = {
        '/': '-',
        '-': "\\x2d",
        '\\': "\\x5d"
    }
    return "".join([escapeMap.get(c, c) for c in path.strip('/')])

def mountUnitName(unit):
    return escapeSystemdUnitName(unit) + '.mount'

def write_mount_unit(writable, what, dst):
    """Writes a mount unit to destination - argument `dst`. The mount unit is
       described as a dictionary (argument `writable`) for most of the mount
       unit parts with the exception of `What` which is provided by the
       argument 'what'."""

    MountUnit = "[Unit]\n" \
                "DefaultDependencies=no\n" \
                "Conflicts=umount.target\n" \
                "Before=local-fs.target umount.target\n"
    if writable["after"]:
        MountUnit += "After=%s\n" % ' '.join(writable["after"])
    if writable["requires"]:
        MountUnit += "Requires=%s\n" % ' '.join(writable["after"])
    MountUnit += "\n"\
                 "[Mount]\n"
    if what:
        MountUnit += "What=%s\n" % what
    if writable["where"]:
        MountUnit += "Where=%s\n" % writable["where"]
    if writable["type"]:
        MountUnit += "Type=%s\n" % writable["type"]
    if writable["options"]:
        MountUnit += "Options=%s\n" % ','.join(writable["options"])
    MountUnit += "\n"\
                 "[Install]\n" \
                 "WantedBy=local-fs.target\n"

    with open((dst), 'w') as f:
        f.write(MountUnit)

def get_writable_data(d):
    wdata = []
    writables = d.getVar('WRITABLES')
    if not writables:
        bb.fatal("No writable paths defined")
    for writable in writables.split():
        where = d.getVarFlag('WRITABLE_PATH', writable)
        if not where:
            bb.fatal("No writable path defined for %s" % writable)
        options = []
        after = []
        requires = []
        type = d.getVarFlag('WRITABLE_TYPE', writable) or 'persistent'
        if type == 'persistent':
            state_mount_unit = d.getVar('SYSTEM_STATE_MOUNT_UNIT')
            if not state_mount_unit:
                bb.fatal("SYSTEM_STATE_MOUNT_UNIT not defined")
            type = 'none'
            options.append('bind')
            after.append(state_mount_unit)
            requires.append(state_mount_unit)
        elif type == 'volatile':
            type = 'tmpfs'
        else:
            bbfatal("%s mount type not implemented" % type)
        writable_data = {
            'after': after,
            'requires': requires,
            'id': writable,
            'where': where,
            'type': type,
            'options': options,
        }
        wdata.append(writable_data)
    return wdata

python() {
    import os

    systemd_system_unitdir = d.getVar('systemd_system_unitdir')
    for writable in get_writable_data(d):
        d.appendVar('FILES_' + d.getVar('PN'), ' ' + writable['where'])
        d.appendVar('FILES_' + d.getVar('PN'), ' ' + \
            os.path.join(systemd_system_unitdir, mountUnitName(writable['where'])))
        d.appendVar('SYSTEMD_SERVICE_' + d.getVar('PN'), ' ' + \
            mountUnitName(writable['where']))
}

python install_mount_units() {
    import os

    # Validate and define the path where the persistent state is kept
    state_mountpoint = d.getVar('SYSTEM_STATE_RUNTIME_MOUNTPOINT')
    if not state_mountpoint:
        bb.fatal("SYSTEM_STATE_RUNTIME_MOUNTPOINT not defined.")
    sys_data_overlay = d.getVar('SYSDATA_OVERLAY')
    if not sys_data_overlay:
        bb.fatal("SYSDATA_OVERLAY not defined.")
    persistent_path = os.path.join(state_mountpoint, sys_data_overlay)

    dee = d.getVar('D')
    systemd_system_unitdir = d.getVar('systemd_system_unitdir').strip('/')
    d_systemd_system_unitdir = os.path.join(dee, systemd_system_unitdir)
    os.makedirs(d_systemd_system_unitdir, exist_ok=True)

    for writable in get_writable_data(d):
        where = writable['where'].strip('/')
        # Where usually is created by systemd when not in place but on a
        # read-only filesystem that won't work so we make sure we have the
        # right mountpoints on the filesystem.
        where_installpath = os.path.join(dee, where)
        if os.path.exists(where_installpath):
            if os.path.isdir(where_installpath) and os.listdir(where_installpath):
                bb.fatal("The path for %s writable (%s) already exists and contains entries that will be shadowed at runtime. Please fix." % (writable, where_installpath))
            elif os.path.isdir(where_installpath) and not os.listdir(where_installpath):
                pass
            else:
                bb.fatal("The path for %s writable (%s) already exists and it is not an empty directory. Please fix." % (writable, where_installpath))
        else:
            os.makedirs(os.path.join(dee, where))
        if writable['type'] == 'tmpfs':
            what = 'tmpfs'
        else:
            what = os.path.join(persistent_path, where)
        dst = os.path.join(d_systemd_system_unitdir, mountUnitName(where))
        write_mount_unit(writable, what, dst)
}

do_install[vardeps] += "WRITABLES WRITABLE_PATH WRITABLE_TYPE"
do_install[postfuncs] += "install_mount_units"
