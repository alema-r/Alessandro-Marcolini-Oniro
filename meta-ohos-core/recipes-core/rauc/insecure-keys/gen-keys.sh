#!/bin/sh
# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

# The insecure pair of keys that are present in this layer were generated with
# the following command. The keys will expire after a year. This is
# intentional.
openssl req -x509 -newkey rsa:4096 \
    -keyout key.pem \
    -out cert.pem \
    -days 365 \
    -nodes \
    -subj "/CN=ostc-eu.org/O=Open Source Technology Center (Europe)/C=PL/L=Warsaw" </dev/null

