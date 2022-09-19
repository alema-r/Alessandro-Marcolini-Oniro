.. SPDX-FileCopyrightText: Huawei Inc.
..
.. SPDX-License-Identifier: CC-BY-4.0

.. include:: ../definitions.rst

ModBus
######

`Modbus <https://modbus.org/>` is a communication protocol from the 1970s
designed to support industrial use cases. Its main modes of operation are Modbus
RTU (Remote Terminal Unit) and Modbus TCP. The RTU variant is mostly used over
RS-485 serial ports, while the newer Modbus TCP variant allows for a communication
over Ethernet for more flexibility.

Besides its core industrial use cases in SCADA (Supervisory control and data
acquisition) systems it also moved closer to the consumer when being integrated
into homes e.g. in home heating systems and PV systems.

|main_project_name| maintains support for Modbus in its Linux and Zephyr
flavours. On the Linux side the user-space Modbus implementation `libmodbus
<https://libmodbus.org/>` is used while on Zephyr the native `Modbus subsystem
<https://docs.zephyrproject.org/latest/services/modbus/index.html>` is used.

RTU and TCP operation modes are supported.

.. note::

    Modbus support in the |main_project_name| exists purely for interoperability
    purpose with existing systems. For new products without this requirement we
    do not recommend using Modbus.

Enabling Modbus in Oniro
************************

Modbus functionality is not enabled by default in the |main_project_name| and
there is currently no Blueprint demonstrating its use cases. The basic integration
is working none the less and Modbus can be enabled in the Linux images by adding
libmodbus to the image you are building. This can be done by adding the
following line to your `conf/local.conf` file:

.. code-block::

    IMAGE_INSTALL:append = " libmodbus"

On Zephyr you would need to enable the `CONFIG_MODBUS` options for your modes of
operations. The Zephyr upstream project does offer `samples for RTU as well as
TCP operations modes in different roles
<https://github.com/zephyrproject-rtos/zephyr/tree/main/samples/subsys/modbus>`

Modbus-testing
**************

The `libmodbus` library does not come with any examples on how to use it by
default. To fill this gap and allow integrators and developers to have a
head-start when developing an application using `libmodbus` we developed a small
set of examples to demonstrate the usage. These examples can be found at our
`modbus-testing repo <https://gitlab.eclipse.org/stefanschmidt/modbus-testing>`
and included in the image by the following addition to your `conf/local.conf` file:

.. code-block::

    IMAGE_INSTALL:append = " libmodbus modbus-testing"

To test Modbus RTU communication over an existing RS-485 serial port (e.g.
/dev/ttyUSB0) under Linux you can run a server as follows:

.. code-block:: console

    $ modbus-server /dev/ttyUSB0

To execute the client side instead run the following command:

.. code-block:: console

    $ modbus-client /dev/ttyUSB0

To run the same example for Modbus TCP instead of RTU adapt the parameters as
shown below:

.. code-block:: console

    $ modbus-server -tcp

.. code-block:: console

    $ modbus-client -tcp SERVER_IP_ADDRESS_HERE

Keep in mind that these examples have hard-coded values for many parts (e.g.
device id, register values, etc) as they are only suited for demonstration
purpose and as a head-start for your own development.

