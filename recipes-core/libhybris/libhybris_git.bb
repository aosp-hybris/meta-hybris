DESCRIPTION = "Hybris is a solution that commits hybris, by allowing us to use \
bionic-based HW adaptations in glibc systems"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRCBRANCH ??= "master"
SRCREV ?= "${AUTOREV}"
SRC_URI = "git://github.com/aosp-hybris/libhybris;branch=${SRCBRANCH};protocol=git"

PV = "0.1.0+git${SRCPV}"
S = "${WORKDIR}/git/hybris"

PACKAGE_ARCH = "${MACHINE_ARCH}"

# We need the android headers which are now provided for compatiblity reasons as external
# component. The android-headers are specific for the environment the android-system-image
# is build with and can differ between different machines.
DEPENDS = "virtual/android-headers \
        ${@base_contains('DISTRO_FEATURES', 'wayland', 'wayland', '', d)}"

PROVIDES += "virtual/libgles1 virtual/libgles2 virtual/egl virtual/libandroid-properties \
             virtual/libhardware"

# most MACHINEs don't use libhybris and depend on mesa to provide *gl*
# Multiple .bb files are due to be built which each provide virtual/libgles1 (virtual/libgles2, virtual/egl)
EXCLUDE_FROM_WORLD = "1"

# current version is known to fail on x86-64
COMPATIBLE_MACHINE_x86-64 = "(-)"

# We don't ship any android binaries but depend on someone else doing this
# Your image needs to pull right MACHINE specific implementation
# use VIRTUAL-RUNTIME_android-system-image in some packagegroup
# RDEPENDS cannot be used because this is TUNE_PKGARCH recipe and
# android-system-image is MACHINE_ARCH
# RDEPENDS_${PN} += "${VIRTUAL-RUNTIME_android-system-image}"

# If you want to enable debugging/tracing functionality add the following to a bbappend
# EXTRA_OECONF += "--enable-debug --enable-trace"

# Build with android headers
EXTRA_OECONF += "--with-android-headers=${STAGING_INCDIR}/android"

# Enable support for wayland
EXTRA_OECONF += "${@base_contains('DISTRO_FEATURES', 'wayland', '--enable-wayland', '', d)}"

# brokensep because configure creates include/android symlink to with-android-headers in ${S} not ${B}
inherit autotools pkgconfig
