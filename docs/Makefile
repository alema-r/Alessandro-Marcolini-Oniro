# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

default: help all

sudo ?=


.PHONY: all

help:
	@echo "Usage: "
	@echo "make all # to build HTML from rst sources"
	@echo "make clean # to cleanup"
	@echo "make setup/debian sudo=sudo # To install build tools (on debian and derived)"

all:
	sphinx-build -W . build

clean:
	rm -rf ./build

setup/debian: /etc/debian_version
	${sudo} apt-get install -y \
make \
python3-sphinx-tabs \
python3-sphinxcontrib.plantuml \
python3-dask-sphinx-theme \
# EOL
