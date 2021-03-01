<!--
SPDX-FileCopyrightText: Huawei Inc.

SPDX-License-Identifier: Apache-2.0
-->

# 3. OHOS environment setup for various kernels

Date: 2020-12-18

## Status

Accepted

## Context

Given that OHOS will run on multiple different kernels we need 
a straight-forward way of setting up build environment.
There are many possible solutions, so we need to pick a sensible one
that'll suit our needs.

## Decision

Add OHOS kernel flavours which
will contain all necessary bitbake sample files (e.g. local.conf.sample,
bblayers.conf.sample, etc.) that can be used together with 
oe-init-build-env script:

    TEMPLATECONF=sources/ohos/flavours/zephyr/conf . oe-init-build-env build-zephyr

This will create a build-zephyr/conf directory based on the contents 
of meta-ohos-zephyr/conf where all required layers and configs 
can be defined. The same applies for other kernels, like FreeRTOS, 
Liteos or any other Linux-based distro.

Below is a sequence diagram of how this could look like for Zephyr: 
```plantuml
!includeurl https://raw.githubusercontent.com/RicardoNiepel/C4-PlantUML/release/1-0/C4_Container.puml

LAYOUT_AS_SKETCH()

Person(dev, "Developer", "Anyone willing to build the OHOS based image")
System_Boundary(ohos, "OHOS") {
    Boundary(ohos_flavours, "flavours") {
        Container(zephyr_config, "zephyr", "bb sample files", "Layer set and initial configuration for OHOS on Zephyr")
        Container(linux_config, "linux", "bb sample files", "Layer set and initial configuration for OHOS on Poky")
    }
    Container(meta_ohos_foundation, "meta-ohos-foundation", "meta-layer", "OHOS foundation service (samgr, softbus)")
    Container(ohos_docs, "docs", "Documentation", "OHOS build system documentation")
}

Container(meta_zephyr, "meta-zephyr", "meta-layer", "ZephyrOS meta-layer")
System_Ext(yocto, "yocto-poky", "distro scaffolding: predefined meta-data, bitbake build system")

Rel(dev, yocto, "1. selects OHOS Zephyr flavour", "environment shell script")
Rel(yocto, zephyr_config, "2. gets default OHOS Zephyr configuration and sets up build environemt", "conf/ subdirectory")
Rel(dev, yocto, "3. initiates the build", "bitbake")
Rel(yocto, meta_ohos_foundation, "4. incorporates Zephyr-compatible OHOS layers")
Rel(yocto, meta_zephyr, "4. incorporates upstream yocto layers")

Lay_D(zephyr_config, linux_config)
Lay_D(meta_ohos_foundation, ohos_docs)
```

## Consequences

The benefit of that approach is that a single command is sufficient
to setup the OHOS build environment for a selected kernel. All 
required layers and configs are pre-defined.

On the down side, this approach can lead to problems when the default
_build_ directory is used for working with different kernels.
When sourcing _oe-init-bild-env_ script, it will create _conf/_ 
basing on the directory pointed to by TEMPLATECONF variable
only when one is missing.

Example:

Working with Zephyr in default _build_ directory instead of e.g. _build-zephyr_:

    TEMPLATECONF=sources/ohos/flavours/zephyr/conf . oe-init-build-env

Then switching to Poky also in default _build_ directory:

    TEMPLATECONF=sources/ohos/flavours/poky/conf . oe-init-build-env
    # this command will not result in changing the environment setup
    # as the _build/conf_ directory already exists
