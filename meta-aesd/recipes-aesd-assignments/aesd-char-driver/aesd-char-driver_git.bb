# Recipe for the AESD character driver kernel module
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://git@github.com/cu-ecen-aeld/assignment3-dhmo9873.git;protocol=ssh;branch=master"

# Update these to match your latest commit and versioning
PV = "1.0+git${SRCPV}"
SRCREV = "0bb40f19117b139137c4beba4233c0b327854c0a"

# Set the source directory to the sub-folder containing the driver code
S = "${WORKDIR}/git/aesd-char-driver"

# This magic line handles the cross-compilation against the target kernel headers
inherit module

RPROVIDES:${PN} += "kernel-module-aesdchar"
RDEPENDS:${PN} += "kernel-module-aesdchar"

FILES:${PN} += "${bindir}/aesdchar_load ${bindir}/aesdchar_unload"

do_install:append () {
    # Create the destination directory
    install -d ${D}${bindir}
    
    # Install the load and unload scripts from your source directory (S)
    install -m 0755 ${S}/aesdchar_load ${D}${bindir}/
    install -m 0755 ${S}/aesdchar_unload ${D}${bindir}/
}
