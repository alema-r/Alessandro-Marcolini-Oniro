# SPDX-FileCopyrightText: Huawei Inc.
# SPDX-License-Identifier: Apache-2.0

FROM ubuntu:20.04

COPY --chown=root:root ppa/zyga-ubuntu-oh-tools-focal.list /etc/apt/sources.list.d/
COPY --chown=root:root ppa/zyga-ubuntu-oh-tools.gpg /etc/apt/trusted.gpg.d/

ARG DEBIAN_FRONTEND="noninteractive"
RUN apt-get update -qq \
 && apt-get install -y eatmydata \
 && eatmydata apt-get install -qq -y \
      git-repo git git-lfs \
      python3-pip python3-sphinx python3-sphinx-rtd-theme python3-recommonmark \
      python3-sphinxcontrib.plantuml \
 && eatmydata apt-get install -qq -y 'ca-certificates=20211016~20.04.1' \
 && eatmydata apt-get clean && rm -rf /var/lib/apt/lists/*

# We need to fix the version here to avoid pulling docutils 0.18 which is
# incompatible with the sphinx version from Ubuntu focal. See more here:
# https://github.com/sphinx-doc/sphinx/issues/9841
RUN pip3 install sphinx-tabs==3.4.0
