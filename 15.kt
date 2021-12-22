import java.io.File

fun main(args: Array<String>) {
    val txt = File(args[0]).useLines { it.toList() }
    txt.forEach { line -> line.split("").filter { t -> t.isNotEmpty() }.forEach { input.add(it.toInt()) } }
    solve("one")
    val oldCols = cols
    val oldRows = rows
    val mult = 5
    cols *= mult
    rows *= mult
    val secondInput = mutableListOf<Int>()
    for (i in 0 until cols * rows) {
        secondInput.add(0)
    }
    for (y in 0 until rows) {
        for (x in 0 until cols) {
            val yMod = y % oldRows
            val xMod = x % oldCols
            val offset = y / oldRows + x / oldCols
            var tmp = input[yMod * oldCols + xMod] + offset
            while (tmp > 9)
                tmp -= 9
            secondInput[y * cols + x] = tmp
        }
    }
    input = secondInput
    solve("two")
}

data class Node(val coord: Pair<Int, Int>, val sum: Int)

fun solve(part: String) {
    val queue = mutableListOf<Node>(Node(Pair(0, 0), 0))
    val visited = mutableSetOf<Pair<Int, Int>>()
    val minDist = mutableMapOf<Pair<Int, Int>, Int>()
    while (queue.isNotEmpty()) {
        var last = Node(Pair(0, 0), 99999)
        queue.forEach {
            if (it.sum < last.sum) {
                last = it
            }
        }
        queue.remove(last)
        val x = last.coord.first
        val y = last.coord.second
        if (x == cols - 1 && y == rows - 1) {
            println("[part $part] (${cols}x${rows}) ${last.sum}")
            return
        }
        if (visited.contains(last.coord)) {
            continue
        }
        visited.add(last.coord)
        getNear(x, y, visited).forEach { near ->
            val newDist = last.sum + input[near.second * cols + near.first]
            if (!minDist.containsKey(near) || newDist < minDist[near]!!) {
                minDist[near] = newDist
                queue.add(Node(near, newDist))
            }
        }
    }
}

fun getNear(x: Int, y: Int, visited: Set<Pair<Int, Int>>): Set<Pair<Int, Int>> {
    val output = mutableSetOf<Pair<Int, Int>>()
    for (near in listOf(Pair(x + 1, y), Pair(x - 1, y), Pair(x, y + 1), Pair(x, y - 1))) {
        if (0 <= near.first && near.first < cols && 0 <= near.second && near.second < rows && !visited.contains(near)) {
            output.add(near)
        }
    }
    return output
}

var input = mutableListOf<Int>()
var cols = 100
var rows = 100

/*
val input = listOf(
1,1,6,3,7,5,1,7,4,2,
1,3,8,1,3,7,3,6,7,2,
2,1,3,6,5,1,1,3,2,8,
3,6,9,4,9,3,1,5,6,9,
7,4,6,3,4,1,7,1,1,1,
1,3,1,9,1,2,8,1,3,7,
1,3,5,9,9,1,2,4,2,1,
3,1,2,5,4,2,1,6,3,9,
1,2,9,3,1,3,8,5,2,1,
2,3,1,1,9,4,4,5,8,1,
)
*/

