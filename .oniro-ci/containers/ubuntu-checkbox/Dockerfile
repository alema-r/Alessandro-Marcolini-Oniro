FROM ubuntu:20.04

ARG DEBIAN_FRONTEND="noninteractive"
RUN sed -i 's/archive.ubuntu.com/mirrors.ustc.edu.cn/g' /etc/apt/sources.list
RUN apt-get update -qq \
 && apt-get install -y software-properties-common \
 && add-apt-repository -y ppa:hardware-certification/public \
 && apt-get install -y checkbox-ng plainbox-provider-resource-generic plainbox-provider-certification-client plainbox-provider-checkbox canonical-certification-client jq
