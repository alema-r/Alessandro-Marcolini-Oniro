.. SPDX-FileCopyrightText: Huawei Inc.
..
.. SPDX-License-Identifier: CC-BY-4.0

Build System Visual Customizations
##################################

.. contents:: 
   :depth: 2

Weston dynamic configuration
****************************

The build exposes mechanism to tweak weston configuration through build
variables. These variables can be provided as part of any configuration (eg.
*local.conf*, *distro.conf*).

The mechanism is enabled by setting ``WESTON_DYNAMIC_INI`` to ``1``. Any of the
following variables will be ignored if this variable is not set to ``1``. The
configuration file path can also be set via a variable: ``WESTON_INI_PATH``.
The default value of ``WESTON_INI_PATH`` should be fine for most of the cases.

Additional variable to be used in conjunction with ``WESTON_DYNAMIC_INI``:

* ``WESTON_INI_NO_TOOLBAR`` - remove the shell panel when set to ``1``
* Configuration for shell background
  * ``WESTON_INI_BACKGROUND_IMAGE`` - sets shell.background-image accordingly
  * ``WESTON_INI_BACKGROUND_COLOR`` - sets shell.background-color accordingly
  * ``WESTON_INI_BACKGROUND_TYPE`` - sets shell.background-type accordingly

Epiphany support for Application mode
*************************************

Epiphany is one of the browsers supported by the build meta-data. It provides a
webkitgtk-based browser.

The build exposes the ability to run the browser as a system service in
application mode. This can be easily configurable and extended via the build
metadata and variables.

Available variables:

* ``EPIPHANY_APP`` - the application name
* ``EPIPHANY_URL`` - the URL to be used when browser starts
* ``EPIPHANY_RDEPENDS`` - additional dependencies needed at runtime
* ``EPIPHANY_SERVICE_ENABLED`` - when set to ``1``, build system will enable
  the systemd service for starting at boot

The build system provides support for using this mechanism with *HomeAssistant*. See this support as an example for how to implement a custom application mode for Epiphany.
