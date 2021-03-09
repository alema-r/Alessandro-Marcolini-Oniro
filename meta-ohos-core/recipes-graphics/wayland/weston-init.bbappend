WESTON_DYNAMIC_INI ?= "0"
WESTON_INI_PATH ?= "etc/xdg/weston/weston.ini"

WESTON_INI_NO_TOOLBAR ?= "0"

python generate_dynamic_ini() {
    import configparser

    # Avoid everything if dynamic configuration is not requested .
    do = d.getVar('WESTON_DYNAMIC_INI', True)
    if do != '1':
        bb.note("No dynamic weston.ini configuration requested.")
        return
    bb.note("Dynamic weston.ini configuration requested.")

    config = configparser.ConfigParser()
    ini_path = os.path.join(d.getVar('D', True), d.getVar('WESTON_INI_PATH', True))

    # Prepopulate an existing configuration.
    if os.path.exists(ini_path):
        if not os.path.isfile(ini_path):
            bb.fatal("weston.ini already exists and it is not a regular file")
        config.read(ini_path)

    # Handle no toolbar configuration.
    if d.getVar('WESTON_INI_NO_TOOLBAR', True) == '1':
        bb.note('Handling WESTON_INI_NO_TOOLBAR.')
        if 'shell' not in config.sections():
            config.add_section('shell')
        config.set('shell', 'panel-position', 'none')

    # Finally, write the configuration. Keep this at the end.
    with open(ini_path, 'w') as init_path_fo:
        config.write(init_path_fo, space_around_delimiters=False)
}
do_install[postfuncs] += "generate_dynamic_ini"
