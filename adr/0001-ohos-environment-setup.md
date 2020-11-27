OHOS environment setup for different kernels
============================================

Given that OHOS will run on multiple different kernels we need 
a straight-forward way of setting up build environment.
There are many possible solutions, so we need to pick a sensible one
that'll suit our needs.

## Configuration / adaptation layers for kernels withing meta-openharmony

One option is to make configuration / adaptaion layer per kernel which
will contain all necessary bitbake configs (e.g. local.conf.sample,
bblayers.conf.sample, etc.) that can be used together with oe-init-build-env
script:

    TEMPLATECONF=meta-openharmony/meta-ohos-zephyr/conf . oe-init-build-env

This will create a build/conf directory based on the contents of meta-ohos-zephyr/conf
where all required layers and configs can be defined. The same applise for other
kernels, like FreeRTOS, Liteos or any other Linux-based distro.

Below is a sequence diagram of how this could look like for Zephyr: 
![ohos-zephyr-build-sequence-diagram](../diagrams/0001-meta-openharmony-zephyr-build-sequence-diagram.svg)

