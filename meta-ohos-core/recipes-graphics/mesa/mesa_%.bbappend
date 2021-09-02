# SPDX-FileCopyrightText: Huawei Inc.
# SPDX-License-Identifier: Apache-2.0

# Building mesa with -Warray-bounds results in a suspicious warning:
#
# src/intel/compiler/brw_eu_emit.c: In function 'brw_set_dest':
# src/intel/compiler/brw_inst.h:1311:34: error: array subscript 67108863 is above array bounds of 'uint64_t[2]' {aka 'long unsigned int[2]'} [-Werror=array-bounds]
#  1311 |    inst->data[word] = (inst->data[word] & ~mask) | (value << low);
#       |                        ~~~~~~~~~~^~~~~~
# ../mesa-20.0.2/src/intel/compiler/brw_inst.h:47:13: note: while referencing 'data'
#    47 |    uint64_t data[2];
#       |             ^~~~
#
# This warning, however, is harmless as long as the function it refers to is
# called with correct parameters.
# The value of word depends on a parameter and can theoretically go all the
# way to 0x3ffffff, but if the function is used correctly, can not actually
# exceed 2.
# 
# Similarly, code in m_vector.c triggers a format-nonliteral warning, but it
# can only be run on a safe, hardcoded string.

# Removing -Werror=array-bounds and -Werror=format-nonliteral here allows us to
# use these flags globally in OPTIMIZE_FOR=security mode while keeping
# mesa building.

TARGET_CFLAGS_remove = "-Werror=array-bounds -Wformat-nonliteral -Werror=format-nonliteral"
