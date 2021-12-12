val nodes = mutableListOf<Node>()
val paths = mutableListOf<String>()

fun main() {
    for (i in 0 until input.size) {
        if (i % 2 == 0) {
            val a = input[i]
            val b = input[i + 1]
            if (nodes.find { it.name == a } == null) {
                val node = Node(a)
                node.links.add(b)
                nodes.add(node)
            } else {
                nodes.firstOrNull { it.name == a }!!.links.add(b)
            }
            if (nodes.find { it.name == b } == null) {
                val node = Node(b)
                node.links.add(a)
                nodes.add(node)
            } else {
                nodes.firstOrNull { it.name == b }!!.links.add(a)
            }
        }
    }
    nodes.forEach { println(it.name + " " + it.links) }
    val start = nodes.firstOrNull { it.name == "start" }!!
    paths.add("")
    Step(start, 0)
    println("[part one] paths = ${paths.filter { it.endsWith("end") }.size}")
}

fun Step(curr: Node, pathIndex: Int) {
    paths[pathIndex] += curr.name
    if (curr.name == "end") {
        return
    }
    curr.links.forEach { link ->
        val next = nodes.firstOrNull { it.name == link }!!
        val isVisited = next.name == next.name.lowercase() && paths[pathIndex].contains(next.name)
        if (!isVisited) {
            paths.add(paths[pathIndex] + " ") // MUST add a space to avoid false concatenation
            Step(next, paths.size - 1)
        }
    }
}

data class Node(val name: String) {
    val links = mutableSetOf<String>()
}

/* 
val input = listOf(
    "fs", "end",
    "he", "DX",
    "fs", "he",
    "start", "DX",
    "pj", "DX",
    "end", "zg",
    "zg", "sl",
    "zg", "pj",
    "pj", "he",
    "RW", "he",
    "fs", "DX",
    "pj", "RW",
    "zg", "RW",
    "start", "pj",
    "he", "WI",
    "zg", "he",
    "pj", "fs",
    "start", "RW"
    )
*/

val input = listOf(
    "EO", "jc",
    "end", "tm",
    "jy", "FI",
    "ek", "EO",
    "mg", "ek",
    "jc", "jy",
    "FI", "start",
    "jy", "mg",
    "mg", "FI",
    "jc", "tm",
    "end", "EO",
    "ds", "EO",
    "jy", "start",
    "tm", "EO",
    "mg", "jc",
    "ek", "jc",
    "tm", "ek",
    "FI", "jc",
    "jy", "EO",
    "ek", "jy",
    "ek", "LT",
    "start", "mg"
)
