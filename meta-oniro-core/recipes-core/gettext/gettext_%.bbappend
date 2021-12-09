# SPDX-FileCopyrightText: Huawei Inc.
# SPDX-License-Identifier: Apache-2.0

# Use system libraries instead of internal bundled copies.
# It's better for code size and security (fix bugs in one place),
# and startup time of something only run on a developer machine
# hardly matters.
DEPENDS += "libxml2 libcroco glib-2.0"
do_configure:prepend () {
	# Get rid of forcing bundled library versions
	sed -i \
		-e 's,gl_LIBXML(\[yes\]),gl_LIBXML([no]),g' \
		-e 's,gl_LIBCROCO(\[yes\]),gl_LIBCROCO([no]),g' \
		-e 's,gl_LIBGLIB(\[yes\]),gl_LIBGLIB([no]),g' \
		${S}/libtextstyle/gnulib-m4/gnulib-comp.m4
	sed -i -e 's,force_included=yes,force_included=no,g' \
		${S}/configure ${S}/*/configure
}
