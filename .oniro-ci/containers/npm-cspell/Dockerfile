# SPDX-FileCopyrightText: Huawei Inc.
# SPDX-License-Identifier: Apache-2.0

FROM ubuntu:21.04

ARG DEBIAN_FRONTEND="noninteractive"
RUN apt-get update -qq \
 && apt-get install -y eatmydata \
 && eatmydata apt-get install -qq -y npm \
 && eatmydata apt-get install -qq -y 'ca-certificates=20210119ubuntu0.21.04.1' \
 && eatmydata apt-get clean && rm -rf /var/lib/apt/lists/*

RUN npm install --global cspell
