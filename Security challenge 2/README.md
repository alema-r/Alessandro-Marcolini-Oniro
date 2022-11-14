## 2nd track challenge 2

> Disable the root login

Actually, I've found multiple ways to solve this challenge.

### 1. The `IMAGE_FEATURE` and `EXTRA_IMAGE_FEATURES` variables (Preferred method)
By looking at the Yocto documentation, there are some image features that can be added to our image. Among these, there are:
- allow-root-login: which allow the root login over ssh (Dropbear and OpenSSH)
- debug-tweaks: which add some features useful for development, including allow-root-login

So, in order to disable root login, we must ensure that these features are not added. In order to do this, we can run the command `bitbake -e | grep "IMAGE_FEATURES"` and verify that these features are not set.

Under the hood, setting the allow-root-login feature, basically adds to the ROOTFS_POSTPROCESS_COMMAND the function `ssh_allow_root_login` which can be found in oe-core/meta/classes/rootfs-postcommands.bbclass.

### 2. Dropbear and OpenSSH flags
In order to prevent root access, we can specify specific flags both for Dropbear and OpenSSH.

For Dropbear, the -w flag disallow root logins and the -g flag disallows password login for root.
While for OpenSSH, we can add the line "PermitRootLogin no" to the config file.
The following function add this flags to the relevant config files, then we use the ROOTFS_POSTPROCESS_COMMAND to execute it.
```
disallow_root_login() {
    for config in sshd_config sshd_config_readonly; do
        if [ -e ${IMAGE_ROOTFS}${sysconfdir}/ssh/$config ]; then
            sed -i 's/^[#[:space:]]*PermitRootLogin.*//' ${IMAGE_ROOTFS}${sysconfdir}/ssh/$config;
            echo "PermitRootLogin no" > ${IMAGE_ROOTFS}${sysconfdir}/ssh/$config;
        fi
    done

    if [ -e ${IMAGE_ROOTFS}${sysconfdir}/default/dropbear ]; then
        echo "DROPBEAR_EXTRA_ARGS= -w -g" > ${IMAGE_ROOTFS}${sysconfdir}/default/dropbear
    fi
}

ROOTFS_POSTPROCESS_COMMAND:append = " disallow_root_login;"
```

This can be seen as a modification of the previous function `ssh_allow_root_login`.

### 3. The usermod command
Another way to disable the root login is using the `usermod` command.

We can achieve this by writing the following lines in a .bb file of our choice.

```
inherit extrausers

EXTRA_USERS_PARAMS = "\
    usermod -L root; \
    usermod -s /bin/false root; \
"
```

What the commands actually do is:
- `usermod -L root`: locks the root account
- `usermod -s /bin/false root`: changes the root shell to /bin/false
