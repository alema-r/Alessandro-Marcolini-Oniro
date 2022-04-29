# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

# CVE-check example script
# Produce a list of recipes without CVEs from the JSON cve-check output

import sys
import getopt

infile = "in.json"
outfile = "out.csv"


def show_syntax_and_exit(code):
    """
    Show the program syntax and exit with an errror
    Arguments:
        code: the error code to return
    """
    print("Syntax: %s [-h][-i inputfile][-o outputfile]" % __name__)
    print("Default files: in.json and out.csv")
    sys.exit(code)


def exit_error(code, message):
    """
    Show the error message and exit with an errror
    Arguments:
        code: the error code to return
        message: the message to show
    """
    print("Error: %s" % message)
    sys.exit(code)


def parse_args(argv):
    """
    Parse the program arguments, put options in global variables
    Arguments:
        argv: program arguments
    """
    global infile, outfile
    try:
        opts, args = getopt.getopt(argv, "hi:o:", ["input", "output"])
    except getopt.GetoptError:
        show_syntax_and_exit(1)
    for opt, arg in opts:
        if opt == "-h":
            show_syntax_and_exit(0)
        elif opt in ("-i", "--input"):
            infile = arg
        elif opt in ("-o", "--output"):
            outfile = arg


def load_json(filename):
    """
    Load the JSON file, return the resulting dictionary
    Arguments:
        filename: the file to open
    Returns:
        Parsed file as a dictionary
    """
    import json

    out = {}
    try:
        with open(filename, "r") as f:
            out = json.load(f)
    except FileNotFoundError:
        exit_error(1, "Input file (%s) not found" % (filename))
    except json.decoder.JSONDecodeError as error:
        exit_error(1, "Malformed JSON file: %s" % str(error))
    return out


def write_csv(filename, data):
    """
    Write the resulting CSV with one line for each package
    Arguments:
        filename: the file to write to
        data: dictionary from parsing the JSON file
    Returns:
        Parsed file as a dictionary
    """
    with open(filename, "w") as f:
        if not "version" in data or data["version"] != "1":
            exit_error(1, "Unrecognized format version number")
        if not "package" in data:
            exit_error(1, "Mandatory 'package' key not found")

        for package in data["package"]:
            keys_in_package = {"name", "layer", "version", "products"}
            if keys_in_package - package.keys():
                exit_error(
                    1,
                    "Missing a mandatory key in package: %s"
                    % (keys_in_package - package.keys()),
                )

            package_name = package["name"]
            layer = package["layer"]
            package_version = package["version"]
            has_cves = "False"
            for product in package["products"]:
                if not "cvesInRecord" in product:
                    exit_error(
                        1, "Missing 'cvesInRecord' for package %s" % package_name
                    )
                if product["cvesInRecord"] == "Yes":
                    has_cves = "True"
            line = "%s;%s;%s;%s\n" % (layer, package_name, package_version, has_cves)
            f.write(line)


def main(argv):
    global infile, outfile
    parse_args(argv)
    data = load_json(infile)
    write_csv(outfile, data)


if __name__ == "__main__":
    main(sys.argv[1:])
