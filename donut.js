(async function() {
    const ARRAY_SIZE = 1760;
    const CHARS = ".,-~:;=!*#$@";

    const sin = (x) => Math.sin(x);
    const cos = (x) => Math.cos(x);
    const int = (x) => Math.floor(x);

    let A = 0;
    let B = 0;
    let loopEnd = 6.28;
    let charArray = new Array(ARRAY_SIZE);
    let numsArray = new Array(ARRAY_SIZE);

    function sleep(ms) {
        return new Promise(resolve => setTimeout(resolve, ms))
    }

    async function drawDoughnut() {
        for(let j = 0; j < loopEnd; j += 0.07) {
            for(let i = 0; i < loopEnd; i += 0.02) {
                let
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
                t = sini * cosj2 * cosA - sinj * sinA,
                x = int(40 + 30 * mess * (cosi * cosj2 * cosB - t * sinB)),
                y = int(12 + 15 * mess * (cosi * cosj2 * sinB + t * cosB)),
                o = int(x + 80 * y),
                N = int(8 * (
                        (sinj * sinA - sini * cosj * cosA) * cosB
                        - sini * cosj * sinA
                        - sinj * cosA
                        - cosi * cosj * sinB
                ));
                if(22 > y && y > 0 && x > 0 && 80 > x && mess > numsArray[o]) {
                    numsArray[o] = mess
                    charArray[o] = CHARS[(N > 0)? N: 0]
                }
            }
        }

        const output = [];
        for(let k = 0; k <= ARRAY_SIZE; k++) {
            output.push((k % 80 == 0)? "\n": charArray[k]);
        }
        console.log(output.join(""))
    }

    while(true) {
        charArray.fill(" ");
        numsArray.fill(0);

        console.clear();
        drawDoughnut();
        await sleep(40);

        A += 0.04
        B += 0.02
    }
})();
