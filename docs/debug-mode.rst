.. SPDX-FileCopyrightText: Huawei Inc.
..
.. SPDX-License-Identifier: CC-BY-4.0

.. include:: definitions.rst

.. _DebugMode:

Debug Mode
##########

In some situations, you may want to compile an image with some additional tools
and features for easier debugging. For this reason, |main_project_name|
offers the debug mode.

Images built with the `debug mode` enabled contain additional tools and allow
to log in as root (which is disabled in the default images).

To enable the `debug mode`, edit your ``local.conf`` and add:

.. code-block:: console

    INHERIT += "oniro-debug-linux"

For convenience, the section is already present commented-out in the template
and you may uncoment it.

When done, rebuild your image as usual. To go back to the production mode,
comment the line out again and rebuild your image.

.. Note::

   Debug mode images are unsupported by the project and do not take part
   of the release. They are provided for convenience only.
