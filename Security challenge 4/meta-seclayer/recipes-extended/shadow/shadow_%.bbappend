# set ENCRYPT_METHOD, SHA_CRYPT_MIN_ROUNDS and PASS_MIN_LEN accordingly to PAM configuration
do_install:append () {
    sed -i -e 's:^[#]*ENCRYPT_METHOD.*:ENCRYPT_METHOD SHA512:' ${D}${sysconfdir}/login.defs
    sed -i -e 's:^[#]*SHA_CRYPT_MIN_ROUNDS.*:SHA_CRYPT_MIN_ROUNDS 65536:' ${D}${sysconfdir}/login.defs
    sed -i -e 's:^[#]*PASS_MIN_LEN.*:PASS_MIN_LEN 8:' ${D}${sysconfdir}/login.defs
}
