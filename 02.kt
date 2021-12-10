import java.io.File

fun main(args: Array<String>) {
    val input = File(args[0]).useLines { it.toList() }
    var horizontal = 0
    var depth = 0
    input.forEach { line ->
        val tokens = line.split(" ")
        when (tokens[0]) {
            "forward" -> horizontal += tokens[1].toInt()
            "up" -> depth -= tokens[1].toInt()
            "down" -> depth += tokens[1].toInt()
        }
    }
    println("[part one] horizontal $horizontal, depth $depth = ${horizontal * depth}")

    // part two
    var aim = 0
    horizontal = 0
    depth = 0
    input.forEach { line ->
        val tokens = line.split(" ")
        when (tokens[0]) {
            "forward" -> {
                horizontal += tokens[1].toInt()
                depth += tokens[1].toInt() * aim
            }
            "up" -> aim -= tokens[1].toInt()
            "down" -> aim += tokens[1].toInt()
        }
    }
    println("[part two] horizontal $horizontal, depth $depth = ${horizontal * depth}")
}
