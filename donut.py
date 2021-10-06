from math import sin, cos
from platform import system
from subprocess import Popen


WINDOWS_NAME = "windows"
WINDOWS_COMMAND = "cls"
UNIX_COMMAND = "clear"


def main():
    A = 0
    B = 0

    list_size = 1760
    loop_end = 6.28
    chars = ".,-~:;=!*#$@"

    if WINDOWS_NAME == system().lower():
        command_str = WINDOWS_COMMAND
    else:
        command_str = UNIX_COMMAND

    def clear_console():
        Popen(command_str, shell=True).wait()

    while True:
        chars_list = [" "] * list_size
        nums_list = [0] * list_size

        j = 0
        while j < loop_end:
            i = 0
            while i < loop_end:
                sini = sin(i)
                cosj = cos(j)
                sinA = sin(A)
                sinj = sin(j)
                cosA = cos(A)
                cosj2 = cosj + 2
                mess = 1 / (sini * cosj2 * sinA + sinj * cosA + 5)
                cosi = cos(i)
                cosB = cos(B)
                sinB = sin(B)

                t = sini * cosj2 * cosA - sinj * sinA
                x = int(40 + 30 * mess * (cosi * cosj2 * cosB - t * sinB))
                y = int(12 + 15 * mess * (cosi * cosj2 * sinB + t * cosB))
                o = int(x + 80 * y)
                N = int(8 * (
                        (sinj * sinA - sini * cosj * cosA) * cosB
                        - sini * cosj * sinA
                        - sinj * cosA
                        - cosi * cosj * sinB
                ))

                if 22 > y and y > 0 and x > 0 and 80 > x and mess > nums_list[o]:
                    nums_list[o] = mess
                    chars_list[o] = chars[N if N > 0 else 0]

                i += 0.02
            j += 0.07

        clear_console()

        output = ""

        for k in range(list_size + 1):
            output += chars_list[k] if k % 80 != 0 else "\n"
        
        print(output)

        A += 0.04
        B += 0.02


if __name__ == "__main__":
    main()
