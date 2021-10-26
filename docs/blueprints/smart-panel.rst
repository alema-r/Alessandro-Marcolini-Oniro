.. SPDX-FileCopyrightText: Huawei Inc.
..
.. SPDX-License-Identifier: CC-BY-4.0

.. include:: ../definitions.rst

Smart Panel Blueprint
#####################

.. contents::
   :depth: 4

Overview
********

The Smart Panel Blueprint provides support for building a PoC for a home
automation system with components showing the capabilities of the build
infrastructure in leveraging different kernels for building an end to end
solution.

The setup is composed of an Avenger96 board acting as a gateway and running
HomeAssistant. The gateway also provides UI as a browser connected to the
localhost HomeAssistant server. The gateway is connected over Bluetooth to two
Nitrogen boards exposing sensors and/or actuator (e.g. controlled light source).

.. image:: assets/smart-home-blueprint-arch.png

.. uml::

    title
      <b>High level connection diagram
    end title

    node "Avenger96" #11AAFF {
      [Linux]
    }

    node "Nitrogen #1" #116699 {
      [Zephyr#1]
    }

    node "Nitrogen #2" #116699 {
      [Zephyr#2]
    }

    [LED] #3BB300
    [LCD] #3BB300
    [MotionSensor] #3BB300
    [HumidityTemperature] #3BB300
    [Display]

    [Linux] --> [Display] : HDMI

    [Zephyr#1] --> Linux : BLE
    [Zephyr#1] --> LCD : I2C
    [Zephyr#1] <-- MotionSensor : I2C
    [Zephyr#1] <-- HumidityTemperature : GPIO

    [Zephyr#2] <-- Linux : BLE
    [Zephyr#2] --> LED : GPIO

    legend
    |= Legend: |
    |<back:#11AAFF>Avenger96:96Boards</back>|
    |<back:#116699>Nitrogen:96Boards</back>|
    |<back:#3BB300>Grove sensors</back>|
    endlegend

    [Human] --> [Display]

Bill of materials
*****************

IoT controller (Avenger96)
--------------------------
Equipment
^^^^^^^^^
* 1x 96Boards Avenger96
* 1x Power supply 12 V minimum 2 A,
* 1x microSD card at least 8 GB,
* 1x HDMI touch screen,

Cables and connectors
^^^^^^^^^^^^^^^^^^^^^
* 1x HDMI-HDMI.
* 1x microUSB-USB type A cable.
* 1x Ethernet cable.

IoT devices (Nitrogens)
-----------------------
Equipment
^^^^^^^^^
* 2x 96Boards Nitrogen,
* 2x Grove Mezzanine board,
* 1x Grove LED module,
* 1x Grove DHT11 temperature and humidity sensor,
* 1x Grove AK9753 human presence sensor,
* 1x Grove LCD RGB Backlight screen,

Cables and connectors
^^^^^^^^^^^^^^^^^^^^^
* 4x Grove connector cables,
* 2x microUSB cable.

Assembly
********

IoT controller
--------------
#. Connect the screen's display output with the board using HDMI cable.
#. Connect the screen's touch controller with the board using USB cable.
#. The board to a network with DHCP server configured using the Ethernet cable.
#. Do not insert the microSD card into the board's slot. It will be needed for
   flashing.
#. Connect power supply to the power jack.

   .. image:: assets/smarthome-blueprint-assembled.jpg
      :alt: The whole assembled setup with labels

IoT devices
-----------
#. Connect Grove Mezzanine boards to both Nitrogen boards. One of them will act
   as a light switching device, the other as sensors device.
#. Assemble the light switching device:

   * Connect Grove LED module to GPIO IJ port on the Mezzanine board.

#. Assemble the sensors device:

   * Connect Grove DHT11 module to GPIO GH port on the Mezzanine board,
   * Connect Grove AK9753 module to GPIO KL port on the Mezzanine board,
   * Connect Grove LCD module to I2C0 port on the Mezzanine board,

#. Connect Nitrogens to your computer with microUSB cables.

Get sources
***********
#. 
   Get |main_project_name| sources as described in the :ref:`documentation <OniroQuickBuild>`.

#. 
   If you already have sources cloned, update them to the most recent revision

   .. code-block:: bash

      $ repo sync -d

Prepare IoT devices (Nitrogen/Zephyr flavour)
*********************************************

Build
-----

#. 
   Create build directory

   .. code-block:: bash

       $ TEMPLATECONF=../oniro/flavours/zephyr . ./oe-core/oe-init-build-env build-oniro-zephyr-96b-nitrogen

#. 
   Edit ``conf/local.conf``, and uncomment the following line:

   .. code-block:: bash

      #MACHINE ?= "96b-nitrogen"

#. 
   Build ``zephyr-blueprint-smarthome-sensors`` image using ``bitbake``,
   with the following override:

   .. code-block:: bash

      $ DISTRO=oniro-zephyr-blueprint-dashboard bitbake zephyr-blueprint-smarthome-sensors

   .. note::

      * The build will configure the MAC address with sane defaults. If you
        want a custom MAC address, you can prepend to the command above the
        following:
        
        BB_ENV_EXTRAWHITE="$BB_ENV_EXTRAWHITE SMART_HOME_SENSORS_MAC" SMART_HOME_SENSORS_MAC="<custom_mac_address>"

      * The MAC address can have any value provided it starts with ``C0`` and
        is unique in your environment.

#. 
   Make sure you have at least 3 GB of free space on the partition
   where the build directory is located.

#. 
   Build ``zephyr-blueprint-smarthome-switch`` image using ``bitbake``,
   with the following override:


   * ``SMART_HOME_SWITCH_MAC`` set to MAC address of the IoT device.
     **It must be different than the MAC address used in the previous step**.

     .. code-block:: bash

        $ DISTRO=oniro-zephyr-blueprint-dashboard bitbake zephyr-blueprint-smarthome-switch

     .. note::

      * The build will configure the MAC address with sane defaults. If you
        want a custom MAC address, you can prepend to the command above the
        following:
        
        BB_ENV_EXTRAWHITE="$BB_ENV_EXTRAWHITE SMART_HOME_SWITCH_MAC" SMART_HOME_SWITCH_MAC="<custom_mac_address>"

      * The MAC address can have any value provided it starts with ``C0`` and
        is unique in your environment.

Flash
-----

#. 
   Connect 96Boards Nitrogen boards to your computer.

#. 
   Assuming both boards are connected simultaneously, retrieve their IDs

   .. code-block:: bash

      $ pyocd list
        #   Probe              Unique ID
      ---------------------------------------------------
        0   Arch BLE [nrf51]   9009022103BB2A02FE6545F3
        1   Arch BLE [nrf51]   9009022103BB3A2DFE6555DC

   If you do not have PyOCD in your system, follow the guide in the PyOCD section of the
   :ref:`Nitrogen documentation <SupportedBoardNitrogenPyOCD>`.

#. 
   Flash the first board with ``zephyr-blueprint-smarthome-sensors`` image. Use the same
   command you used for build, with the following changes:


   * add ``-c flash_usb`` suffix,
   * remove the MAC address override (it is only effective at build time),
   * add board ID override in the form of:
     ``BB_ENV_EXTRAWHITE="$BB_ENV_EXTRAWHITE PYOCD_FLASH_IDS" PYOCD_FLASH_IDS="<id>"``

     .. code-block:: bash

        $ BB_ENV_EXTRAWHITE="$BB_ENV_EXTRAWHITE PYOCD_FLASH_IDS" \
                PYOCD_FLASH_IDS="9009022103BB2A02FE6545F3" bitbake zephyr-blueprint-smarthome-sensors -c flash_usb

#. 
   Flash the other board with ``zephyr-blueprint-smarthome-switch`` image

   .. code-block:: bash

      $ BB_ENV_EXTRAWHITE="$BB_ENV_EXTRAWHITE PYOCD_FLASH_IDS" \
                PYOCD_FLASH_IDS="9009022103BB3A2DFE6555DC" bitbake zephyr-blueprint-smarthome-switch -c flash_usb

Prepare IoT controller (Avenger96/Linux flavour)
************************************************

Build
-----
#. 
   Create build directory

   .. code-block:: bash

       $ TEMPLATECONF=../oniro/flavours/linux . ./oe-core/oe-init-build-env build-oniro-linux-stm32mp1-av96

#. 
   Edit ``conf/local.conf``, and uncomment the following line:

   .. code-block:: bash

      #MACHINE ?= "stm32mp1-av96"

#. 
   Make sure you have at least 25 GB of free space on the partition
   where the build directory is located.


#. Build ``blueprint-dashboard-gateway-image`` image using ``bitbake``,
   with the following overrides:

   * ``DISTRO`` set to ``oniro-linux-blueprint-dashboard`` --
     this distribution configuration enhances the regular distribution
     with dependencies necessary for this demonstration scenario,
   * ``SMART_HOME_SENSORS_MAC`` and ``SMART_HOME_SWITCH_MAC`` set to MAC
     addresses of IoT devices, as set in the previous section.

     .. code-block:: bash

        $ DISTRO=oniro-linux-blueprint-dashboard \
            BB_ENV_EXTRAWHITE="$BB_ENV_EXTRAWHITE SMART_HOME_SENSORS_MAC SMART_HOME_SWITCH_MAC" \
            SMART_HOME_SENSORS_MAC="C0:BA:DD:06:F0:0D" \
            SMART_HOME_SWITCH_MAC="C0:BA:DD:06:F0:0E" \
            bitbake blueprint-dashboard-gateway-image


Flash
-----
#. 
   Build artifacts are located in ``./tmp/deploy/images/stm32mp1-av96/`` relative
   to the build directory. Flashing script is located in
   ``./scripts/create_sdcard_from_flashlayout.sh`` relative to the build artifacts
   directory. FSD card flash layout used to convert build artifacts to the image is
   located in ``flashlayout_<image name>/extensible/FlashLayout_sdcard_stm32mp157a-av96-extensible.tsv``
   relative to the build artifacts directory.

#. 
   Go to the build artifacts directory and convert flash layout into a build image

   .. code-block:: bash

      $ cd tmp/deploy/images/stm32mp1-av96

      $ ./scripts/create_sdcard_from_flashlayout.sh \
          flashlayout_blueprint-dashboard-gateway-image/extensible/FlashLayout_sdcard_stm32mp157a-av96-extensible.tsv

#. 
   Lots of text will appear, but the most important part are the two commands

   .. code-block:: bash

       WARNING: before to use the command dd, please umount all the partitions
               associated to SDCARD.
           sudo umount `lsblk --list | grep <sd card name> | grep part | gawk '{ print $7 }' | tr '\n' ' '`

       To put this raw image on sdcard
           sudo dd if=<image>.raw of=<sd card node> bs=8M conv=fdatasync status=progress

#. Put a microSD card in your card reader. Copy the commands above and paste them
   into your terminal. Do not remove the microSD card from the reader just yet.

Add Bluetooth firmware
----------------------
Due to licensing details, |main_project_name| cannot provide the firmware file for
the on-board Bluetooth controller. However, user may download and install it
manually.

#. Download the `Bluetooth firmware file from GitHub <https://github.com/dh-electronics/meta-av96/raw/9d2a3fdacf49aebc9298a7c444f5a021d3e99e13/recipes-bsp/firmware-files/files/lib/firmware/brcm/BCM4345C0.hcd>`_.

#. Create directory ``lib/firmware/brcm`` on the ``rootfs`` partition of the
   microSD card and copy the downloaded file into that directory. Assuming your
   card's mount point is ``/mnt/rootfs``, you may use the example commands to
   accomplish that:

   .. code-block:: bash

      $ sudo mkdir /mnt/rootfs/lib/firmware/brcm
      $ sudo cp ~/Downloads/BCM4345C0.hcd /mnt/rootfs/lib/firmware/brcm
      $ sudo chmod 755 /mnt/rootfs/lib/firmware/brcm

#. Unmount all microSD card partitions, remove them from the reader and put
   it in Avenger96 card slot. Power up the board.

Home Assistant
**************

Set up
------
#. Put ths SD card into Avenger96 and press power on button.
#. On start up, Avenger96 will try to contact a DHCP server. Be sure to
   have one in your network, where you can see the IP address assigned to
   the board. Alternatively, you can use UART-USB adapter, log in to the
   system and set IP address manually.
#. Using a web browser, go to the IP address of Avenger96, port ``8123``.
   E.g. if you find out the Avenger's IP address is ``192.0.2.137`` , go to
   ``http://192.0.2.137:8123``.
#. Create a user account and click your way through the basic settings.
#. You should find yourself in Home Assistant dashboard. Three circular
   icons on top show temperature, humidity and human presence.

   .. image:: assets/smarthome-blueprint-dashboard.png
      :alt: Home Assistant dashboard

Customizing the Dashboard
-------------------------
#. Click three vertical dots in the top-right corner of the dashboard
   and select ``Configure UI``.
#. Agree to take control over the UI, remove the default widgets and
   click the yellow ``+`` button in the bottom-right corner of the dashboard.
#. Choose widgets according to your taste. They are already pre-configured,
   connected to the existing sensors (temperature, humidity, human presence)
   and light controls.
#. Adjust widgets configuration according to your taste. Most notable
   possibility is that you can combine multiple entities in one widget,
   e.g. creating a single widget with all the sensors and a light control
   switch.

   .. image:: assets/smarthome-blueprint-dashboard-config.png
      :alt: Home Assistant dashboard configuration


   .. image:: assets/smarthome-blueprint-dashboard-config-done.png
      :alt: Home Assistant dashboard finished configuration

Set up automations
------------------
Home Assistant can do things for you based on state sensors. This is how
Home Assistant can be configured to turn the light on/off based on
user presence nearby the human presence sensor. Imagine it is a kitchen
light turning on every time it detects you are about to prepare a meal!


#. Go to Configuration menu using the cog button located on the left-hand
   sidebar, then to Automations.
#. Click the yellow ``+`` button in the bottom-right corner of the screen, then
   skip the smart automations generator.
#. Name your automation accordingly, e.g. *Lights on when somebody in the room*.
#. Set the trigger as follows:

   * Trigger type: ``State``,
   * Entity: ``sensor.all_scenarios_os_smarthome_device_presence``,
   * From: ``False`` (*person not detected*).
   * To: ``True`` (*person detected*)/

     .. image:: assets/smarthome-blueprint-dashboard-automations-trigger.png
        :alt: Home Assistant dashboard automations trigger

#. 
   Set the action as follows:


   * Action type: ``Call service``,
   * Service: ``switch.turn_on``,
   * Name(s) of entities to turn on: ``switch.all_scenarios_os_smarthome_device_light``.

     .. image:: assets/smarthome-blueprint-dashboard-automations-action.png
        :alt: Home Assistant dashboard automations action

#. 
   Click the yellow save button in the bottom-right corner of the screen.

#. Repeat steps 2-6 for the opposite automation, i.e. turning the light off,
   when human presence state switches from ``True`` to ``False``.

Verify operations
*****************
#. Temperature and humidity readings should show temperature and humidity
   in the room where the Nitrogen with sensors is located. Try to blow
   hot/cold air on the sensor to see values changing
#. Human presence state should change when you move your hand close to
   the sensor.
#. The light switch should control the LED.
#. The LED should turn on/off automatically when human presence is detected.
#. The LCD screen should display the current temperature, humidity
   and the connection state marked with a ``<B>`` symbol.
