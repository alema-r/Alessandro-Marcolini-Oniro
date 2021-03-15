<!--
SPDX-FileCopyrightText: Huawei Inc.

SPDX-License-Identifier: Apache-2.0
-->

# Introduction

This README file contains information on the contents of the meta-ohos-acts layer.

Please see the corresponding sections below for details.

# Dependencies

This layer has no dependencies outside of poky.

# Patches

Please submit any patches for meta-ohos-acts layer as pull requests against the
meta-ohos repository at https://git.ostc-eu.org/OSTC/OHOS/meta-ohos/

# Table of Contents

- Adding the meta-ohos-acts layer to your build
- Misc

## Adding the meta-ohos-acts layer to your build

Run 'bitbake-layers add-layer <path to meta-ohos>/meta-ohos-acts'

## Misc

This layer builds a subset of the ACTS (Application Compatibility Test Suite)
for Open Harmony OS. Some tests are not ported yet. Some tests are excluded
because they are duplicated in the greater bitbake ecosystem (e.g. LTP). Some
tests are hardware or kernel specific and are not considered in this phase yet.
