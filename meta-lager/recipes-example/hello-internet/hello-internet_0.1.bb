SUMMARY = "Simple hello-internet application"
SECTION = "examples"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit systemd

SRC_URI = "file://hello-internet.c \
           file://hello-internet.service"

S = "${WORKDIR}"

do_compile() {
    ${CC} ${LDFLAGS} hello-internet.c -o hello-internet
}

do_install() {
    install -d ${D}${bindir}
    install -m 0755 hello-internet ${D}${bindir}

    # Install the systemd service file
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/hello-internet.service ${D}${systemd_system_unitdir}
}

SYSTEMD_SERVICE:${PN} = "hello-internet.service"
FILES:${PN} += "${systemd_system_unitdir}/hello-internet.service"