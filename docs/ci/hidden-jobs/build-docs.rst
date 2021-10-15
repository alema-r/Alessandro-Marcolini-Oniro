.. SPDX-FileCopyrightText: Huawei Inc.
..
.. SPDX-License-Identifier: CC-BY-4.0

===========
.build-docs
===========

The ``.build-docs`` job builds reStructuredText documentation.

This job offers no configuration and works by convention. This is done to both
simplify its use and improve uniformity of developer experience. The job
automatically reacts to changes in the ``docs/`` directory as well as the
definition of the pipeline.  Built documentation is collected as job artifacts.

Usage Guide
===========

To use this job in your pipeline include the generic build definition file,
``build-generic.yaml`` and define a ``build-docs`` job:

.. code-block:: yaml

    build-docs:
      extends: .build-docs

The job expects the project to have the following documentation structure.

.. code-block::

    └── docs
        ├── index.rst
        └── Makefile

The ``Makefile`` should build the documentation into the ``build`` directory,
relative to the ``docs`` directory. A sample, re-usable example is is provided below:

.. code-block:: makefile

    # SPDX-FileCopyrightText: Huawei Inc.
    #
    # SPDX-License-Identifier: Apache-2.0

    .PHONY: all
    all:
        sphinx-build -W -C \
            -D html_theme=sphinx_rtd_theme \
            -D project='Name of the project' \
            -D copyright='Copyright Holder' \
            . build

    clean:
        rm -rf ./build
