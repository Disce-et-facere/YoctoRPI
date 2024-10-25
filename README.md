# Yocto Qemu SystemD Hello Internet uppgift

Forkat repo, YoctoRPI, som korrigerats för att utföra uppgiften Hello internet som använder systemD för att starta programmet när qemu startas och skickar meddelandet "Hello, Internet!" med wget till webhook.site.

## Korrigerade Filer

```
# Borttaget
meta-lager/recipes-example/hello-internet/files/hello-world.c
meta-lager/recipes-example/hello-internet/files/hello-world.service
meta-lager/recipes-example/hello-internet/hello-world_0.1.bb

# Ändrad
meta-lager/conf/disto/custom-distro.conf

# Skapat
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

Detta tar långt tid. Bitbake cachar bra så det går att avbryta med `ctrl+c`.

```bash

# Bygg en minimal Linux med Bitbake och custom-distro (hello world)
DISTRO=custom-distro bitbake -k core-image-minimal

```

### 4. Starta Qemu i terminalen

```bash

# Kör bygget emulerat med Qemu
runqemu qemux86-64 nographic

```

### 5. Logga in

Användarnamn: root


### 6. Kör Hello Internet i Qemu

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

### 7. Resultat

![webhook-prtsc](https://github.com/user-attachments/assets/a9b3b0d0-4021-4040-a9bc-eb349a6db4e7)
