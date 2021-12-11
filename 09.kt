import java.io.File
import kotlin.math.pow

fun main(args: Array<String>) {
    val input = File(args[0]).useLines { it.toList() }
    val map = mutableListOf<List<Int>>()
    input.forEach { line ->
        map.add(line.split("").filter { it.isNotEmpty() }.map { it.toInt() })
    }
    partOne(map)
    partTwo(map)
}

fun partOne(map: List<List<Int>>) {
    var partOne = 0
    for (y in 0 until map.size) {
        for (x in 0 until map.first().size) {
            partOne += getLowPointScore(x, y, map)
        }
    }
    println("[part one] $partOne")
}

fun partTwo(map: List<List<Int>>) {
    val done = mutableListOf<MutableList<Boolean>>()
    for (y in 0 until map.size) {
        done.add(mutableListOf<Boolean>())
        for (x in 0 until map.first().size) {
            done[y].add(map[y][x] == 9)
        }
    }
    val basins = mutableListOf<Int>()
    while (done.find { it.contains(false) } != null) {
        var basinSize = 0
        var seeds = mutableListOf<Pair<Int, Int>>()
        for (y in 0 until map.size) {
            for (x in 0 until map.first().size) {
                if (!done[y][x]) {
                    seeds.add(Pair(x, y))
                    break
                }
            }
            if (seeds.isNotEmpty()) {
                break
            }
        }
        while (seeds.isNotEmpty()) {
            basinSize += seeds.size
            seeds.forEach { done[it.second][it.first] = true}
            var newSeeds = mutableListOf<Pair<Int, Int>>()
            seeds.forEach {
                val x = it.first
                val y = it.second
                val w = Pair(x - 1, y)
                val e = Pair(x + 1, y)
                val n = Pair(x, y - 1)
                val s = Pair(x, y + 1)
                if (x > 0 && !done[y][x - 1] && !newSeeds.contains(w)) {
                    newSeeds.add(w)
                }
                if (x < map[0].size - 1 && !done[y][x + 1] && !newSeeds.contains(e)) {
                    newSeeds.add(e)
                }
                if (y > 0 && !done[y - 1][x] && !newSeeds.contains(n)) {
                    newSeeds.add(n)
                }
                if (y < map.size - 1 && !done[y + 1][x] && !newSeeds.contains(s)) {
                    newSeeds.add(s)
                }
            }
            seeds = newSeeds
        }
        basins.add(basinSize)
    }
    val result = basins.sorted().takeLast(3)
    println("[part two] $result = ${result[0] * result[1] * result[2]}")
}

fun getLowPointScore(x: Int, y: Int, map: List<List<Int>>): Int {
    val center = map[x][y]
    var low = 0
    if (x > 0) {
        low += if (map[x - 1][y] > center) 1 else 0
    } else {
        low += 1
    }
    if (x < map[0].size - 1) {
        low += if (map[x + 1][y] > center) 1 else 0
    } else {
        low += 1
    }
    if (y > 0) {
        low += if (map[x][y - 1] > center) 1 else 0
    } else {
        low += 1
    }
    if (y < map.size - 1) {
        low += if (map[x][y + 1] > center) 1 else 0
    } else {
        low += 1
    }
    return if (low == 4) center + 1 else 0
}