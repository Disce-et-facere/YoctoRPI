# Yocto Qemu SystemD Hello Internet uppgift

Forkat repo, YoctoRPI, som korrigerats för att utföra uppgiften Hello internet som avnänder systemD för att start programmet när qemu startas och skickar meddelandet "Hello, Internet!" med wget till webhook.site.

## Korrigerade Filer

```
meta-lager/conf/disto/custom-distro.conf
meta-lager/recipes-example/hello-internet/files/hello-internet.c
meta-lager/recipes-example/hello-internet/files/hello-internet.service
meta-lager/recipes-example/hello-internet/hello-internet_0.1.bb

```

## Steg för steg

### 1. Setup 

Kör endast detta steg första gången.

Öppna bash i dev-containern och kör dessa kommandon rad för rad:

```bash
# Gå till yocto mappen
cd /yocto

# Använd repo för att lägga till git dependancies från manifestet 
repo init -u /yocto/project -m /yocto/project/default.xml

# Ladda ner git dependancies
repo sync

```

### 2. Starta byggmiljön

Detta steg måste göras varje gång man öppnar en ny terminal.

```bash

# Starta byggmiljön
source /yocto/sources/poky/oe-init-build-env /yocto/build

```

### 3. Bygg en egen Linux

Detta tar långt tid. Bitbake cachar bra så det gåt att avbryta med `ctrl+c`.

```bash

# Bygg en minimal Linux med Bitbake och custom-distro (hello world)
DISTRO=custom-distro bitbake -k core-image-minimal

```

### 4. Starta Qemu i terminalen

```bash

# Kör bygget emulerat med Qemu NoGraphic
runqemu qemux86-64 nographic

```

### 5. Logga in

Användarnamn: root


### 7. Kör Hello Internet i Qemu

```bash
# Kör programmet
hello-internet

# Se var programmet är installerat
whereis hello-internet

# Se vilka bibliotek programmet använder
ddl hello-internet

# Se information om tjänsten
systemctl status hello-internet

# Starta tjänsten igen
systemctl start hello-internet

# Se loggen för tjänsten
journalctl -u hello-internet
```
