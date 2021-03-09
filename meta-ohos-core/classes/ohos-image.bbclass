# OpenHarmony functionality for images

# List of tty to mask getty for
SYSTEMD_MASK_GETTY ?= ""

systemd_mask_getty () {
    if [ -e ${IMAGE_ROOTFS}${root_prefix}/lib/systemd/systemd ]; then
        for i in ${SYSTEMD_MASK_GETTY}; do
            systemctl --root="${IMAGE_ROOTFS}" mask "getty@${i}.service"
        done
    fi
}

IMAGE_PREPROCESS_COMMAND_append = " ${@ 'systemd_mask_getty;' if bb.utils.contains('DISTRO_FEATURES', 'systemd', True, False, d) and not bb.utils.contains('IMAGE_FEATURES', 'stateless-rootfs', True, False, d) else ''}"
