val nodes = mutableListOf<Node>()
var paths = mutableListOf<String>()

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
    partOne()
    paths = mutableListOf<String>()
    partTwo()
}

fun partOne() {
    paths.add("")
    Step("start", 0)
    println("[part one] paths = ${paths.filter { it.endsWith("end") }.size}")
}

fun partTwo() {
    paths.add("")
    StepPartTwo("start", 0)
    println("[part two] paths = ${paths.filter { it.endsWith("end") }.size}")
}

fun Step(curr: String, pathIndex: Int) {
    paths[pathIndex] += curr
    if (curr == "end") {
        return
    }
    val node = nodes.firstOrNull { it.name == curr }!!
    node.links.forEach { next ->
        val isVisited = next == next.lowercase() && paths[pathIndex].contains(next)
        if (!isVisited) {
            paths.add(paths[pathIndex] + " ") // MUST add a space to avoid false concatenation
            Step(next, paths.size - 1)
        }
    }
}

fun StepPartTwo(curr: String, pathIndex: Int) {
    paths[pathIndex] += curr
    if (curr == "end") {
        return
    }
    val doneSmall = paths[pathIndex].split(" ").filter { it != "start" && it == it.lowercase() }
    val hasDoubleVisit = doneSmall.size > doneSmall.toSet().size
    val node = nodes.firstOrNull { it.name == curr }!!
    node.links.forEach { next ->
        val isVisited = when {
            next == "start" -> true
            next == next.lowercase() && paths[pathIndex].contains(next) && hasDoubleVisit -> true
            else -> false
        }
        if (!isVisited) {
            paths.add(paths[pathIndex] + " ") // MUST add a space to avoid false concatenation
            StepPartTwo(next, paths.size - 1)
        }
    }
}

data class Node(val name: String) {
    val links = mutableSetOf<String>()
}

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