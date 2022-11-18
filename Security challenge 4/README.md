## 2nd track challenge 4

> (free run) Propose a security improvement for the blueprint

Based on the lynis output in the previous task, I decided to perform the following changes.

Improvements made:
- added rounds on PAM configuration (65536)
- added pam-cracklib module to ensure a better password strength: minimum password length of 8 as advised by [NIST](https://pages.nist.gov/800-63-3/sp800-63b.html#5111-memorized-secret-authenticators), password with only one class of characters (between alpha, digits, other) not allowed
- modified file `/etc/login.defs` to match PAM configuration (encryption method, sha-512 min rounds and minimum password length)

Known issues:
- problem with `passwd`: when changing user password with `passwd` I ran into a "Token manipulation error". I suppose that this problem is not caused by my layer because even without it the problem persists. Even with further investigation, I couldn't find out what is causing the error.
- I wanted to add the `PASS_MAX_DAYS` variable to `/etc/login.defs` but the above problem doesn't let me do that (because on the first login the user is prompted to change the password - this is because the last password change date is Apr 05, 2011 accordingly to `chage -l oniro` command).

Changes can be seen in the `meta-seclayer` folder.
