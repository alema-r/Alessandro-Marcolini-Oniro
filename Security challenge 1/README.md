```
oniro@oniro-linux-qemux86-64:/$ grep -v "\(nologin\|false\|sync\)" /etc/passwd | awk -F: '{print $1}'
root
oniro
```

To find out which user can login into the image, I looked for users on /etc/passwd ignoring lines that have /sbin/nologin, /bin/false and /bin/sync as a shell.

Alternatively, this could be achieved by checking which shells are allowed in /etc/shells and filtering the /etc/passwd file accordingly.

```
oniro@oniro-linux-qemux86-64:/$ cat /etc/shells 
# /etc/shells: valid login shells
/bin/sh
oniro@oniro-linux-qemux86-64:/$ grep "/bin/sh" /etc/passwd | awk -F: '{print $1}'
root
oniro
```
