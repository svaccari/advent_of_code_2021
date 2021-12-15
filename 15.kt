import java.io.File

fun main(args: Array<String>) {
    val txt = File(args[0]).useLines { it.toList() }
    txt.forEach { line -> line.split("").filter { t -> t.isNotEmpty() }.forEach { input.add(it.toInt()) } }
    println(input.size)
    solve(Pair(0, 0), -input[0], listOf())
}

fun solve(xy: Pair<Int, Int>, sum: Int, path: List<Pair<Int, Int>>) {
    if (path.contains(xy)) {
        return
    }
    val x = xy.first
    val y = xy.second
    val add = sum + input[y * cols + x]
    if (add >= minSum) {
        return
    }
    if (x == cols - 1 && y == rows - 1) {
        if (add < minSum) {
            minSum = add
            println("[part one] $minSum")
        }
        return
    }
    val newPath = path.toMutableList()
    newPath.add(xy)
    val e = Pair(x + 1, y)
    val s = Pair(x, y + 1)
    val w = Pair(x - 1, y)
    val n = Pair(x, y - 1)
    val next = 
    when {
        x < cols - 1 -> next = Math.min(next, input[e.second * cols + e.first]
        y < rows - 1 -> next[input[s.second * cols + s.first]] = s
        x > 0 -> next[input[w.second * cols + w.first]] = w
        y > 0 -> next[input[n.second * cols + n.first]] = n
    }
    if (next.isNotEmpty()) {
        val lowest = next[next.keys.sorted().first()]!!
        solve(lowest, add, newPath)
    }
}

fun get(x: Int, y: Int): Int {
    return input[y * cols + x]
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

