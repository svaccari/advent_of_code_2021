fun main() {
    val input = mutableListOf(
        4,7,3,8,6,1,5,5,5,6,
        6,7,4,4,4,2,3,7,4,1,
        2,8,1,2,8,6,8,8,2,7,
        8,8,4,4,3,6,5,6,2,4,
        4,5,4,6,6,7,4,2,6,6,
        4,5,1,8,6,7,4,2,7,8,
        7,4,5,7,2,3,7,4,3,1,
        4,5,2,4,8,7,3,2,4,7,
        3,1,5,3,3,4,1,3,1,4,
        3,7,2,1,4,1,4,6,6,7,
    )

    var flashes = 0
    for (step in 1..250) {
        val flashed = mutableListOf<Int>()
        for (i in 0 until 100) {
            input[i]++
        }
        while (input.find { it > 9 } != null) {
            for (y in 0 until 10) {
                for (x in 0 until 10) {
                    val coord = y * 10 + x
                    if (input[coord] > 9) {
                        input[coord] = 0
                        flashed.add(coord)
                        for (yy in Math.max(0, y - 1)..Math.min(y + 1, 9)) {
                            for (xx in Math.max(0, x - 1)..Math.min(x + 1, 9)) {
                                val coord2 = yy * 10 + xx
                                if (!flashed.contains(coord2)) {
                                    input[coord2]++ 
                                }
                            }
                        }
                    }
                }
            }
        }
        flashes += flashed.size
        if (step == 100) {
            println("[part one] $flashes")
        }
        if (input.find { it != 0 } == null) {
            println("[part two] step $step")
        }
    }
}