#include <stdlib.h>

int main() {
    // Send Hello internet with wget to webhook
    system("wget --post-data=\"message=Hello, Internet!\" https://webhook.site/677a88f4-25a9-4b28-8287-85312e730460 -O /dev/null");
    return 0;
}