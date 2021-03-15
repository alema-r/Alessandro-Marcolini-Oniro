<!--
SPDX-FileCopyrightText: Huawei Inc.

SPDX-License-Identifier: CC-BY-4.0
-->

-   [Gitlab contributions](#gitlab-contributions)
    -   [Overview](#overview)
-   [REUSE compliance](#reuse-compliance)
    -   [SPDX information and REUSE standard](#spdx-information-and-reuse-standard)
        -   [SPDX header example](#spdx-header-example)
        -   [Substantial contributions](#substantial-contributions)
-   [DCO sign-off](#dco-sign-off)
    -   [Overview](#overview-1)
    -   [Developer Certificate of Origin](#developer-certificate-of-origin)
-   [`%PROJECTNAME%`-specific contributions process and guidelines](#projectname-specific-contributions-process-and-guidelines)

# Gitlab contributions

## Overview

OpenHarmony project handles contributions as [merge requests](https://docs.gitlab.com/ee/user/project/merge_requests/) to relevant repositories part of the OpenHarmony [GitLab instance](https://git.ostc-eu.org/OSTC/OHOS). The flow for handling that is classic: fork-based merge requests. This means that once you have an account, you can fork any repository, create a branch with proposed changes and raise a merge request against the forked repository. More generic information you can find on the Gitlab's documentation as part of ["Merge requests workflow"](https://docs.gitlab.com/ee/development/contributing/merge_request_workflow.html).

# REUSE compliance

## SPDX information and REUSE standard

All projects and files for an hosted project **MUST** be [REUSE](https://reuse.software/) compliant. REUSE requires SPDX information for each file, rules for which are as follows:

-   Any new file must have a SPDX header (copyright and license).
-   For files that don't support headers (for example binaries, patches etc.) an associated `.license` file must be included with the relevant SPDX information.
-   Do not add Copyright Year as part of the SPDX header information.
-   The general rule of thumb for the license of a patch file is to use the license of the component for which the patch applies.
-   When modifying a file through this contribution process, you may (but don't have to) claim copyright by adding a copyright line.
-   Never alter copyright statements made by others, but only add your own.

Some files will make an exception to the above rules as described below:

-   Files for which copyright is not claimed and for which this information was not trivial to fetch (for example backporting patches, importing build recipes etc. when upstream doesn't provide the SPDX information in the first place)
-   license files (for example `common-licenses` in bitbake layers)

### SPDX header example

Make sure all of your submitted new files have a licensing statement in the headers. Please make sure that the license for your file is consistent with the licensing choice at project level and that you select the correct SPDX identifier, as in the following example for Apache 2.0 license:

``` text
/*
 * SPDX-FileCopyrightText: Jane Doe <jane@example.com>
 *
 * SPDX-License-Identifier: Apache-2.0
 */
```

### Substantial contributions

Therefore, if your contribution is only a patch directly applied to an existing file, then you are not required to do anything. If your contribution is an entire new project, or a substantial, copyrighted contribution, you **MUST** make sure that you do that following the [IP Policy](https://git.ostc-eu.org/OSTC/OHOS/governance/ip-policy/-/blob/dev/oss/policy/source/sections/section05.rst) and that you comply with REUSE standard to include the licensing information where they are required.

# DCO sign-off

## Overview

Commits **MUST** be submitted only with a sign-off by the submitter. A commit without a sign-off will be automatically rejected. You need not be the author or the copyright holder of the contribution, but you must make sure that you have the power to submit on behalf of those who are.

To sign your work, just add a line like this at the end of your commit message:

``` text
Signed-off-by: Jane Doe <jane@example.com>
```

This could be done automatically in the `git` submission:

``` text
git commit --signoff -m "comment"
```

## Developer Certificate of Origin

By doing this you state that you certify the following (from [https://developercertificate.org](https://developercertificate.org/)):

``` text
Developer Certificate of Origin
Version 1.1

Copyright (C) 2004, 2006 The Linux Foundation and its contributors.
1 Letterman Drive
Suite D4700
San Francisco, CA, 94129

Everyone is permitted to copy and distribute verbatim copies of this
license document, but changing it is not allowed.


Developer's Certificate of Origin 1.1

By making a contribution to this project, I certify that:

(a) The contribution was created in whole or in part by me and I
    have the right to submit it under the open source license
    indicated in the file; or

(b) The contribution is based upon previous work that, to the best
    of my knowledge, is covered under an appropriate open source
    license and I have the right under that license to submit that
    work with modifications, whether created in whole or in part
    by me, under the same open source license (unless I am
    permitted to submit under a different license), as indicated
    in the file; or

(c) The contribution was provided directly to me by some other
    person who certified (a), (b) or (c) and I have not modified
    it.

(d) I understand and agree that this project and the contribution
    are public and that a record of the contribution (including all
    personal information I submit with it, including my sign-off) is
    maintained indefinitely and may be redistributed consistent with
    this project or the open source license(s) involved.
```

# `%PROJECTNAME%`-specific contributions process and guidelines

%POPULATE ME%
