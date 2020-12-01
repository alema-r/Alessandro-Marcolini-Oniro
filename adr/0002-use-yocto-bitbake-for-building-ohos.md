# 2. Use Yocto / bitbake for building OHOS

Date: 2020-12-01

## Status

Accepted

## Context

OHOS as a distributed operating system is designed to be hosted on 
top of OS kernel. Currently Liteos-a (targeting ARM Cortex-A based devices) 
and Liteos-m (targeting ARM Cortex-M based devices) kernels are supported.

OHOS Liteos-A has BSP for only two ip cameras devices, whereas Liteos-M
has BSP for only one wifi iot device. Qemu support is missing.

## Decision

To leverage existing BSPs OHOS will be made compatible with Yocto.
We will start with Linux Poky distribution for ARM Cortex-A based devices
and Zephyr for the Cortex-M based devices.

## Consequences

meta-ohos layer (when done properly) will enable nearly effortless porting 
of OHOS to new kernels / distros.

Decoupling of existing OHOS services from Liteos-A/M kernels has to be done.

HQ modification to the OHOS stack between releases can require repeating
decoupling works with each and every release if we're not aligned and in sync.
