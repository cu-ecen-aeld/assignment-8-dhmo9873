# Recipe for the AESD character driver kernel module
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://git@github.com/cu-ecen-aeld/assignment3-dhmo9873.git;protocol=ssh;branch=master"

# Update these to match your latest commit and versioning
PV = "1.0+git${SRCPV}"
SRCREV = "d1fe583845ae41c501867c15fab716fbccd5ae9a"

# Set the source directory to the sub-folder containing the driver code
S = "${WORKDIR}/git/aesd-char-driver"

# This magic line handles the cross-compilation against the target kernel headers
inherit module

inherit update-rc.d
INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME:${PN} = "aesdchar-start-stop"
INITSCRIPT_PARAMS = "defaults 97"

FILES:${PN} += "${base_libdir}/modules/${KERNEL_VERSION}/extra/aesdchar.ko"
FILES:${PN} += "${sysconfdir}/init.d/aesdchar-start-stop"

do_install:append () {
    # Install the init script to load the module at boot
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${S}/aesdchar-start-stop ${D}${sysconfdir}/init.d/aesdchar-start-stop
}
