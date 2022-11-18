## 2nd track challenge 3
> Run a system audit with lynis, analyse the result. Which options should be changed in your opinion? Submit a document with your findings

The lynis output and report can be find in this folder. I'll now analyze them.

### Warnings
Lynis only found one warning being the following:
1. Hostname contains invalid characters [NETW-2400] 

In the oniro case, the hostname is the following: oniro-linux-qemux86-64. Analyzing the lynis output, we can see that the unexpected character is the hyphen `-`.
By looking at the `hostname(7)` manpage, it clearly states that the allowed character for the hostname are:
- ASCII letters (a-z)
- digits (0-9)
- hyphen(s)

The only rule is that a hostname may not start with a hyphen. So, in this case I think that no action is necessary.

### Suggestions
1. If not required, consider explicit disabling of core dump in /etc/security/limits.conf file [KRNL-5820] 

Core dumps can be useful to troubleshoot software defects, but on the other hand, they may contain sensitive data like passwords or cryptographic keys. Usually, core dumps are only readable by root, so I think that's more useful to keep them, in order to know what's going on if a software crash happens.

2. Check PAM configuration, add rounds if applicable and expire passwords to encrypt with new values [AUTH-9229]

PAM is the standard Unix Password Authentication Module.
To enforce security of passwords it is advisable to add rounds of hash encryption. Adding rounds (let's say 65536) forces a malicious user to hash every password attempt 65536 times, thus slowing down password bruteforce attacks. If rounds are not set, the default value is 5000 which is, as stated in login.defs manpage, "orders of magnitude too low for modern hardware."
Adding password expiration, instead, ensures that the user periodically changes his password, which can be useful if he uses the same password for multiple accounts (really bad security practice) and the password is found due to some password breaches.

Personally, I would add, at least, password rounds. This can be achieved by modifying the `/etc/pam.d/passwd` file.

3. Configure minimum encryption algorithm rounds in /etc/login.defs [AUTH-9230]
4. Configure maximum encryption algorithm rounds in /etc/login.defs [AUTH-9230] 

Same as 2.

5. Install a PAM module for password strength testing like pam_cracklib or pam_passwdqc [AUTH-9262] 

Installing such a module can be useful because it prevents the user to set an easy password with low strength.
It could be useful to make users aware about password security.

6. When possible set expire dates for all password protected accounts [AUTH-9282]

Same as 2.

7. Configure minimum password age in /etc/login.defs [AUTH-9286] 
8. Configure maximum password age in /etc/login.defs [AUTH-9286] 

Same as 2.

9. To decrease the impact of a full /var file system, place /var on a separate partition [FILE-6310] 

The /var partition, if not in a separate partition, could fill the entire filesystem, thus causing a system malfunction.
In particular the /var/log directoy is used by system services to store log data. Since this directory may contain world-writable files there is a risk of resource exhaustion. Furthermore, mounting the /var on a different partition allows to add additional mount options such as noexec/nosuid/nodev. 
From the `mount(8)` manpage:
- noexec: prevents the direct executions of any binaries on the mounted filesystem
- nosuid: does not honor set-user-ID and set-group-ID bits or file capabilities when executing programs from this filesystem
- nodev: does not interpret character or block special devices on the filesystem

I think that this could be useful only if the machine is generating a lot of logs (for example a web application with lots of users).

10. The database required for 'locate' could not be found. Run 'updatedb' or 'locate.updatedb' to create this file. [FILE-6410]

This basically says that the locate command has no database, so it cannot search files. Not a big deal.

11. Disable drivers like USB storage when not used, to prevent unauthorized storage or data theft [USB-1000] 

Disabling drivers for USB storage can prevent users with physical access to the machine to copy sensitive data.

12. Disable drivers like firewire storage when not used, to prevent unauthorized storage or data theft [STRG-1846] 

Same as 11.

13. Check DNS configuration for the dns domain name [NAME-4028] 

Since `dnsdomainname` didn't returned anything, it says to check the dns configuration.

14. Install a package audit tool to determine vulnerable packages [PKGS-7398] 

Installing a package audit tool helps with finding known vulnerable package so that the user can update them.

15. Determine if protocol 'dccp' is really needed on this system [NETW-3200] 
16. Determine if protocol 'sctp' is really needed on this system [NETW-3200] 
17. Determine if protocol 'rds' is really needed on this system [NETW-3200] 
18. Determine if protocol 'tipc' is really needed on this system [NETW-3200]

It would be safer to disable protocols that are not needed in order to reduce the attack surface on the machine.

19. Configure a firewall/packet filter to filter incoming and outgoing traffic [FIRE-4590] 

A firewall or packet filter could be useful to set which traffic is allowed and which not. Some firewall examples that could be installed are iptables and ufw.
I believe that the firewall could be a useful addition to further reduce tha attack surface.

20. Check if log files are properly rotated [LOGG-2146] 

Log files should be rotated to prevent them growing big in size. I think that adopting a rotation strategy depends on the system: if the system generates lots of logs, rotation makes sense. Furthermore, tools like `logrotate(8)` not only rotate logs but can also compress and email them.

21. Enable logging to an external logging host for archiving purposes and additional protection [LOGG-2154] 

It is advisable to mantain log files on an external host to prevent data from being lost.

22. Add a legal banner to /etc/issue, to warn unauthorized users [BANN-7126] 
23. Add legal banner to /etc/issue.net, to warn unauthorized users [BANN-7130] 

If the system is monitored, we should make users aware about their privacy (if required by legal requirements or law).

24. Enable process accounting [ACCT-9621]

Process accounting is useful to track system resources. It records and summarize commands and processes.
Accounting can be enabled with `/usr/sbin/accton on`. It stores the info in the file `/var/account/pacct`. As this file can get quite large it can be useful to add log rotation.
Process accounting is actually not a process, but it's managed by the kernel itself.

25. Enable sysstat to collect accounting (no results) [ACCT-9626]

sysstat is a toolbox for monitoring system performance. It can be useful to have a track of how system resources are used.

26. Enable auditd to collect audit information [ACCT-9628] 

The audit daemon can audit files and processes. If suspicious changes were made, they will trigger an event to be logged by auditd. It might be a powerful resource in detecting system intrusions or monitoring the system integrity.

27. Check available certificates for expiration [CRYP-7902] 

Certificates should be checked for expiration to avoid misuse of them. In our case there are 2 expired certificates which are:
- `/usr/share/ca-certificates/mozilla/Cybertrust_Global_Root.crt`
- `/usr/share/ca-certificates/mozilla/GlobalSign_Root_CA_-_R2.crt`

28. Utilize software pseudo random number generators [CRYP-8005] 

29. Install a file integrity tool to monitor changes to critical and sensitive files [FINT-4350]

A file integrity tool (like AIDE) can help detecting changes to critical and sensitive files. It can be configured to alert system or security personnel on events.

30. Determine if automation tools are present for system management [TOOL-5002] 

Automation tools can hel with automating system management and increasing the integrity and stability of the system

31. One or more sysctl values differ from the scan profile and could be tweaked [KRNL-6000] 

The scan reveals that some sysctl values are different from the scan profile. sysctl values are used to adjust kernel specific functions.

32. Harden the system by installing at least one malware scanner, to perform periodic file system scans [HRDN-7230] 

Periodically scan for malwares are recommended to ensure the system's security. This can be achieved by launching regularly a malware scanner such as rkhunter, chkrootkit, OSSEC.


### Conclusions

There are some suggestions that can be applied universally and others that are only relevant for some specific systems.
