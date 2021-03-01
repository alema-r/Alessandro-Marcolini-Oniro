# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

SUMMARY = "OHOS Softbus"
DESCRIPTION = "OHOS communication utility for distributed services"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=98c2e72b17fae6c40fb14fd5e43b29ec"

DEPENDS += "cjson libsec samgr softbus-kits iam-kits hichainsdk hichainsdk-innerkits mbedtls"

SRC_URI = "git://gitee.com/openharmony/communication_services_softbus_lite.git;protocol=https"

PV = "1.0+git${SRCPV}"
PVSHORT = '${@d.getVar("PV", False).split("+")[0]}'
PVMAJOR = '${@d.getVar("PV", False).split(".")[0]}'

SRCREV = "d110d2cd61806efc89d7eaa651d2c36fce66df97"

S = "${WORKDIR}/git"

SB_SRCS = "${S}/discovery/coap/source/coap_discover.c		\
		${S}/discovery/coap/source/json_payload.c	\
		${S}/discovery/coap/source/nstackx_common.c	\
		${S}/discovery/coap/source/nstackx_device.c	\
		${S}/discovery/coap/source/coap_socket.c	\
		${S}/discovery/coap/source/coap_adapter.c	\
		${S}/os_adapter/source/L1/os_adapter.c		\
		${S}/discovery/discovery_service/source/discovery_service.c	\
		${S}/discovery/discovery_service/source/coap_service.c		\
		${S}/discovery/discovery_service/source/common_info_manager.c	\
		${S}/trans_service/source/libdistbus/tcp_session.c		\
		${S}/trans_service/source/libdistbus/tcp_session_manager.c	\
		${S}/trans_service/source/libdistbus/auth_conn_manager.c	\
		${S}/trans_service/source/libdistbus/trans_lock.c		\
		${S}/trans_service/source/utils/tcp_socket.c	\
		${S}/trans_service/source/utils/message.c	\
		${S}/trans_service/source/utils/aes_gcm.c	\
		${S}/authmanager/source/auth_conn.c		\
		${S}/authmanager/source/auth_interface.c	\
		${S}/authmanager/source/msg_get_deviceid.c	\
		${S}/authmanager/source/wifi_auth_manager.c	\
		${S}/authmanager/source/bus_manager.c"

SB_INC = "-I${S}/discovery/coap/include				\
		-I${S}/os_adapter/include			\
		-I${S}/discovery/discovery_service/include	\
		-I${S}/authmanager/include			\
		-I${S}/trans_service/include/libdistbus		\
		-I${S}/trans_service/include/utils		\
		-I${S}/trans_service/source/libdistbus		\
		-I${S}/trans_service/source/utils"
SB_INC += "-I${STAGING_INCDIR}/cjson"

CFLAGS_SB = "${CFLAGS} ${SB_INC} -fPIC -shared"
# Flags copied from the original OHOS build configuration. To be replaced.
CFLAGS_SB += "-D_GNU_SOURCE -D_SCANTY_MEMORY_ -D__LINUX__"
LDFLAGS_SB = "${LDFLAGS} -lsec -lcjson -lrt -L${B} -Wl,-soname,lib${PN}.so.${PVMAJOR}"

do_compile () {
	${CC} ${CFLAGS_SB} ${LDFLAGS_SB}	\
		${SB_SRCS}			\		
		-o ${B}/lib${PN}.so.${PVSHORT}

}

do_install () {
	install -d ${D}${libdir}
	oe_soinstall ${B}/lib${PN}.so.${PVSHORT} ${D}${libdir}
}
