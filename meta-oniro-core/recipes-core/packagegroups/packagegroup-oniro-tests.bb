# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

DESCRIPTION = "Oniro Project tests package group"

inherit packagegroup

PACKAGES = "\
	packagegroup-oniro-tests \
	"

RDEPENDS:packagegroup-oniro-tests = "\
	ptest-runner \
	python3-checkbox-ng-service \
        "
