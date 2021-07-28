import java.util.Arrays;
import java.io.PrintStream;


class Donut {
    static final char[] CHARS = ".,-~:;=!*#$@".toCharArray();

    static final int ARRAY_SIZE = 1760;

    static final String OS_NAME = System.getProperty("os.name");

    static double sin(double x) {
        return Math.sin(x);
    }

    static double cos(double x) {
        return Math.cos(x);
    }

    static void clearConsole() {
        try {
            if(OS_NAME.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        PrintStream out = System.out;

        double A = 0, B = 0, i, j, loop_end;
        double[] z = new double[ARRAY_SIZE];
        char[] b = new char[ARRAY_SIZE];
        int k;

        loop_end = 6.28;

        clearConsole();
        while(true) {
            Arrays.fill(b, ' ');
            Arrays.fill(z, 0);
            for(j = 0; j < loop_end; j += 0.07) {
                for(i = 0; i < loop_end; i += 0.02) {
                    double
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
                    x = (int)(40 + 30 * mess * (cosi * cosj2 * cosB - t * sinB)),
                    y = (int)(12 + 15 * mess * (cosi * cosj2 * sinB + t * cosB)),
                    o = x + 80 * y,
                    N = (int)(8 * (
                        (sinj * sinA - sini * cosj * cosA) * cosB
                        - sini * cosj * sinA
                        - sinj * cosA
                        - cosi * cosj * sinB
                    ));

                    if(22 > y && y > 0 && x > 0 && 80 > x && mess > z[o]) {
                        z[o] = mess;
                        b[o] = CHARS[N > 0 ? N : 0];
                    }
                }
            }

            clearConsole();
            for (k = 0; k <= ARRAY_SIZE; k++) {
                out.print((k % 80 != 0) ? b[k] : 10);
            }

            A += 0.04;
            B += 0.02;
        }
    }
}
