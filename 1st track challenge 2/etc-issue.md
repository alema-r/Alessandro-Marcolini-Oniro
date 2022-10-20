> Try to change /etc/issue to include your name/nick, tell what happens.
> Question (free form): What happened and why? Can you imagine why Linux distributions do such a thing?

When we try to change the /etc/issue we get an error saying `'/etc/issue' Read-only file system`. Looking at the permissions we can see that the file is owned by root:root and is readable by all users but writable only by root. The /etc/issue file contains information about the system, that are shown before the login. Modifying these, could lead to measleading or incorrect information for the user who logs in. 
Also I think that this information could be used by some installation scripts, thus identifying a wrong OS, even if /etc/os-release and /etc/system-release would be a better idea. 
