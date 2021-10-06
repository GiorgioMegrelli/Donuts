#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <math.h>


#define CHARS ".,-~:;=!*#$@"

#define ARRAY_SIZE 1760

#define WINDOWS_COMMAND "cls"
#define UNIX_COMMAND "clear"


void clear_console();

int main() {
    float A = 0, B = 0, i, j, loop_end;
    float z[ARRAY_SIZE];
    char b[ARRAY_SIZE];
    char output[ARRAY_SIZE + 2];

    output[ARRAY_SIZE + 1] = '\0';

    loop_end = 6.28;

    clear_console();
    while(0 == 0) {
        memset(b, (int)' ', ARRAY_SIZE);
        memset(z, 0, 4*ARRAY_SIZE);
        for(j = 0; j < loop_end; j += 0.07) {
            for(i = 0; i < loop_end; i += 0.02) {
                float
                sini = sin(i),
                cosj = cos(j),
                sinA = sin(A),
                sinj = sin(j),
                cosA = cos(A),
                cosj2 = cosj + 2,
                mess = 1 / (sini * cosj2 * sinA + sinj * cosA + 5),
                cosi = cos(i),
                cosB = cos(B),
                sinB = sin(B),
                t = sini * cosj2 * cosA - sinj * sinA;
                int
                x = 40 + 30 * mess * (cosi * cosj2 * cosB - t * sinB),
                y = 12 + 15 * mess * (cosi * cosj2 * sinB + t * cosB),
                o = x + 80 * y,
                N = 8 * (
                    (sinj * sinA - sini * cosj * cosA) * cosB
                    - sini * cosj * sinA
                    - sinj * cosA
                    - cosi * cosj * sinB
                );

                if(22 > y && y > 0 && x > 0 && 80 > x && mess > z[o]) {
                    z[o] = mess;
                    b[o] = CHARS[N > 0 ? N : 0];
                }
            }
        }

        clear_console();

        for (int k = 0; k <= ARRAY_SIZE; k++) {
            output[k] = ((k % 80 > 0)? b[k] : '\n');
        }

        printf("%s", output);

        A += 0.04;
        B += 0.02;
    }
    return 0;
}

void clear_console() {
    #ifdef _WIN32
        system(WINDOWS_COMMAND);
    #else
        system(UNIX_COMMAND);
    #endif
}
