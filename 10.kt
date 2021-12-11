import java.io.File

val push = listOf("(", "[", "{", "<")
val pop = listOf(")", "]", "}", ">")

fun main(args: Array<String>) {
    val input = File(args[0]).useLines { it.toList() }
    val incomplete = partOne(input)
    partTwo(incomplete)
}

fun partOne(input: List<String>): List<String> {
    var error = 0
    val incomplete = mutableListOf<String>()
    val stack = mutableListOf<String>()
    input.forEach { line ->
        var localError = 0
        val chars = line.split("").filter { it.isNotEmpty() }
        chars.forEach { char -> 
            if (push.contains(char)) {
                stack.add(char)
            } else {
                val last = stack.removeLast()
                when (char) {
                    ")" -> if (last != "(") localError += 3
                    "]" -> if (last != "[") localError += 57
                    "}" -> if (last != "{") localError += 1197
                    ">" -> if (last != "<") localError += 25137
                }
            }
        }
        if (localError == 0) {
            incomplete.add(line)
        } else {
            error += localError
        }
    }
    println("[part one] $error")
    return incomplete
}

fun partTwo(input: List<String>) {
    val scores = mutableListOf<Long>()
    input.forEach { line ->
        var error = 0L
        val stack = mutableListOf<String>()
        val chars = line.split("").filter { it.isNotEmpty() }
        chars.forEach { char -> 
            if (push.contains(char)) {
                stack.add(char)
            } else {
                stack.removeLast()
            }
        }
        while (stack.isNotEmpty()) {
            val last = stack.removeLast()
            when (last) {
                "(" -> error = error * 5L + 1L
                "[" -> error = error * 5L + 2L
                "{" -> error = error * 5L + 3L
                "<" -> error = error * 5L + 4L
            }
        }
        scores.add(error)
    }
    val sorted = scores.sorted()
    println("[part two] ${sorted.size} ${sorted.size / 2} ${sorted[sorted.size / 2]}")
}