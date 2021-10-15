.. SPDX-FileCopyrightText: Huawei Inc.
..
.. SPDX-License-Identifier: CC-BY-4.0

==========
.lava-test
==========

The ``.lava-test`` job creates the LAVA test job definition based on the
pipeline information and submits the job to LAVA.

The artifact of this job is the list of LAVA job id(s) submitted by this job.
This will be consumed by the :doc:`lava-report`

Job Variables
=============

The ``.lava-test`` job defines several variables as a way to customize
the LAVA job definition before submiting the job to LAVA.

CI_LAVA_JOB_DEFINITION
----------------------

This is the url to the job definition template. The template needs to have
variables inside which will be replaced by this job. The list of variables is
the following:
- $ci_pipeline_id - pipeline ID of the CI job ($CI_PIPELINE_ID in GitLab)
- $ci_job_id - CI job ID ($CI_JOB_ID in GitLab)
- $ci_project_id - CI project ID ($CI_PROJECT_ID in GitLab)
- $build_job_id - job ID of the **build** job within the same pipeline
- $callback_url - url which triggers the execution or the manual job which
collects the results back from LAVA to GitLab. See :doc:`lava-report`

CI_BUILD_JOB_NAME
-----------------

The name of the job that builds the artifacts which will be used by LAVA to
boot up the DUT for this particular LAVA job. If we test multiple builds in one
CI job, each will have different build job name.

CI_REPORT_JOB_NAME
------------------

The name of the ``report`` job which will be triggered manually when the LAVA
job(s) are finished with executution. This job will collect the results from
LAVA and import them to GitLab.

CI_LAVA_INSTANCE
----------------

The base url of the LAVA server.
