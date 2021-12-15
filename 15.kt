import java.io.File

fun main(args: Array<String>) {
    val txt = File(args[0]).useLines { it.toList() }
    txt.forEach { line -> line.split("").filter { t -> t.isNotEmpty() }.forEach { input.add(it.toInt()) } }
    println(input.size)
    solve()
}

data class Node(val coord: Pair<Int, Int>, val sum: Int)

fun solve() {
    val queue = mutableListOf<Node>(Node(Pair(0, 0), 0))
    val visited = mutableListOf<Pair<Int, Int>>()
    val minDist = mutableMapOf<Pair<Int, Int>, Int>()
    while (queue.isNotEmpty()) {
        val last = queue.removeLast()
        if (visited.contains(last.coord)) {
            continue
        }
        val x = last.coord.first
        val y = last.coord.second
        visited.add(last.coord)
        if (x == cols - 1 && y == rows - 1) {
            println("[part one] ${last.sum}")
            return
        }
        for (near in listOf(Pair(x + 1, y), Pair(x - 1, y), Pair(x, y + 1), Pair(x, y - 1))) {
            if (0 <= near.first && near.first < cols && 0 <= near.second && near.second < rows && !visited.contains(near)) {
                val test = input[near.second * cols + near.first]
                val newDist = last.sum + test
                if (!minDist.containsKey(near) || newDist < minDist[near]!!) {
                    minDist[near] = newDist
                    queue.add(Node(near, newDist))
                }
            }
        }
    }
}

val input = mutableListOf<Int>()
var minSum = 500
val cols = 100
val rows = 100

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

