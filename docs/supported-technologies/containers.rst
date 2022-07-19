.. SPDX-FileCopyrightText: Huawei Inc.
..
.. SPDX-License-Identifier: CC-BY-4.0

.. include:: ../definitions.rst

Containers
##########

|main_project_name| maintains support for a container-based architecture
included as part of the OS when using the reference images (or images derived
from them).  This OS functionality aims at facilitating the deployment of
container-based applications. This feature plays a part in the wider
|main_project_name| update software stack for managing deployments and updates
from the host operating system to the container-based applications.

.. note::

    The current support is aiming at the Linux-based |main_project_name| images.

As part of this architecture, |main_project_name| currently provides the
integration and support for the following container management engines:

Podman
********

`Podman <https://podman.io/>`_ is a daemonless container engine that can run
both in root and rootless mode. |main_project_name| supports both of these
modes while keeping the read-only root filesystem assumption valid by taking
advantage of the application partition for storing persistent data.

The OS integration provides seamless integration with `docker` CLI  so that
`docker` commands are handled transparently by `podman`.

When using `podman` alone, deploying a service is as simple as running the
associated CLI command for spawning a container of a specific image. For
example, running a `busybox` container using an image from `dockerhub`
container registry is achieved by running the following when logged in as the
`oniro` user:

.. code-block:: console

    $ podman run -ti --rm docker.io/library/busybox:latest ls

This will spawn, run and remove a `busybox` rootless container. You can run the
same example in root mode:


.. code-block:: console

    $ sudo podman run -ti --rm docker.io/library/busybox:latest ls

Updating a containerised application, when using `podman` alone follows the
classic container flow:

  * stop container
  * update/pull image
  * start a new container

As an example, we use the same container image above running a tool in
background mode:

.. code-block:: console

    # run a container in background
    $ podman run -d --name busybox_top docker.io/library/busybox:latest top
      8fcc484d8a905a9e6cb24e1eac87782970f91951ab1cb0d1b0ec64729e0ffb01

    # the container is running
    $ podman ps
      CONTAINER ID  IMAGE                             COMMAND     CREATED        STATUS            PORTS       NAMES
      8fcc484d8a90  docker.io/library/busybox:latest  top         6 seconds ago  Up 4 seconds ago              busybox_top

    # stop the container
    $ podman stop busybox_top
      busybox_top

    # pull an updated container image
    $ podman pull docker.io/library/busybox:latest
      Trying to pull docker.io/library/busybox:latest...
      Getting image source signatures
      Copying blob 19d511225f94 skipped: already exists
      Copying config 62aedd01bd done
      Writing manifest to image destination
      Storing signatures
      62aedd01bd8520c43d06b09f7a0f67ba9720bdc04631a8242c65ea995f3ecac8

    # run the updated container
    $ podman run -d --name busybox_top_update docker.io/library/busybox:latest top
      1a3a7383a111e79b702fd081d4204a5827698b252d2d00ee9ae818306dbbef87

    # check that the updated container is running
    $ podman ps
      CONTAINER ID  IMAGE                             COMMAND     CREATED        STATUS            PORTS       NAMES
      1a3a7383a111  docker.io/library/busybox:latest  top         8 seconds ago  Up 5 seconds ago              busybox_top_update

    # cleanup
    $ podman stop busybox_top_update
    $ podman rm busybox_top busybox_top_update

The output above may be slightly different due to variations in your local
setup. That is expected.
