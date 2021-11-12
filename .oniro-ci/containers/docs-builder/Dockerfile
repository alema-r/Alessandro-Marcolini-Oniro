# SPDX-FileCopyrightText: Huawei Inc.
# SPDX-License-Identifier: Apache-2.0

FROM ubuntu:20.10

COPY --chown=root:root ppa/zyga-ubuntu-oh-tools-groovy.list /etc/apt/sources.list.d/
COPY --chown=root:root ppa/zyga-ubuntu-oh-tools.gpg /etc/apt/trusted.gpg.d/

ARG DEBIAN_FRONTEND="noninteractive"
RUN apt-get update -qq \
 && apt-get install -y eatmydata \
 && eatmydata apt-get install -qq -y \
      git-repo git git-lfs \
      python3-sphinx python3-sphinx-rtd-theme python3-recommonmark python3-sphinx-tabs \
      python3-sphinxcontrib.plantuml \
 && eatmydata apt-get install -qq -y 'ca-certificates=20210119~20.10.1' \
 && eatmydata apt-get clean && rm -rf /var/lib/apt/lists/*
