FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://lxc.cfg file://ebtables.cfg file://vswitch.cfg file://cgroup-hugetlb.cfg file://docker.cfg file://xt-checksum.cfg file://0001-patch-for-CVE-2022-0847.patch"

