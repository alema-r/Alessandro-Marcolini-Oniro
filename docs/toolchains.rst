.. SPDX-FileCopyrightText: Huawei Inc.
..
.. SPDX-License-Identifier: CC-BY-4.0

.. include:: definitions.rst

.. _Toolchains:

Toolchains
##########

|main_project_name| provides and supports both gcc based and clang/LLVM based toolchains.


.. contents:: 
    :depth: 2

gcc
***
|main_project_name|'s gcc toolchain is based on GNU binutils 2.38 and gcc 12.1.
|main_project_name|'s 2.0 branch may move to newer gcc releases within the gcc 12 branch
(12.2, 12.3, ...), but will stay on the gcc 12 branch.
Post-2.0 releases of |main_project_name| will likely update to new major versions.

To select gcc, edit `conf/local.conf` and add:
.. code-block:: sh

   TOOLCHAIN="gcc"

clang
*****
|main_project_name|'s clang toolchain is based on LLVM 14.0.6, including lld.
|main_project_name|'s 2.0 branch may move to newer clang releases within the llvm 14 branch,
(14.0.7, ...) but will stay on the llvm 14 branch.
Post-2.0 releases of |main_project_name| will likely update to new major versions.

To select clang, edit `conf/local.conf` and add:
.. code-block:: sh

   TOOLCHAIN="clang"

You may also want to add:
.. code-block:: sh

   RUNTIME="llvm"

`RUNTIME="llvm"` enables the use of additional LLVM components over their gcc counterparts:
`compiler-rt` replaces `libgcc`, `libc++` replaces `libstdc++` and the LLVM version of
`libunwind` is preferred.

`libc++` and `libstdc++` are (almost) fully source level compatible, but not binary compatibile.
You can't link an application using one to a library using the other. It is therefore recommended
to use the same C++ library for all packages in the system.

Which one to use
****************
Since different compilers optimize differently, there is some code that will perform better
when built with clang while there is other code that will perform better when built with gcc.

If you're adding your own code to |main_project_name|, you may want to try both toolchains and
compare their performance on your code.

If you're using the Linux kernel, both toolchains have been tested for a long time and will
work.

If you're using the Zephyr kernel, gcc is officially supported by the upstream project while
clang support is considered experimental.

If you need `OpenHarmony` compatibility, you have to use clang and the LLVM runtime. In that
case, you need to add this to `conf/local.conf`:

.. code-block:: sh

   TOOLCHAIN="clang"
   RUNTIME="llvm"
