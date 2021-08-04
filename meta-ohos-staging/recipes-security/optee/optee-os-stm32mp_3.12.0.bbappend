# This is only safe because this recipes uses out-of-tree builds. The package's
# make design caches sysroot paths as make dependencies (see the .d files).
# Some of these paths are versioned (gcc for example) so recompiling after a
# gcc bump would make those dependencies fail. Forcing a clean build directory
# when configure task is invalidated (which happens when native sysroot is
# regenerated), fixes the dependency issue.
do_configure[cleandirs] = "${B}"
