import java.io.File
import kotlin.math.pow

fun main(args: Array<String>) {
    val input = File(args[0]).useLines { it.toList() }
    var partOne = 0
    var partTwo = 0
    input.forEach { line ->
        val tokens = line.split(" | ")
        val segments = tokens[0].split(" ")
        val output = tokens[1].split(" ")
        partOne += output.filter { it.length == 2 || it.length == 3 || it.length == 4 || it.length == 7}.size
        // part two
        val mapping = mutableMapOf<Int, String>()
        mapping[8] = "abcdefg"
        segments.forEach {
            val sorted = it.toCharArray().sorted().joinToString("")
            when (it.length) {
                2 -> mapping[1] = sorted
                3 -> mapping[7] = sorted
                4 -> mapping[4] = sorted
            }
        }
        var number = 0
        output.forEachIndexed { index, value ->
            val dec = 10.0.pow(3 - index).toInt()
            when (value.length) {
                2 -> number += dec * 1
                3 -> number += dec * 7
                4 -> number += dec * 4
                7 -> number += dec * 8
                5 -> {
                    // can be 2, 3, 5
                    val has4 = value.sumOf { if (mapping[4]!!.contains(it)) 1 else 0 as Int }
                    val has7 = value.sumOf { if (mapping[7]!!.contains(it)) 1 else 0 as Int }
                    when {
                        has4 == 2 && has7 == 2 -> number += dec * 2
                        has4 == 3 && has7 == 3 -> number += dec * 3
                        has4 == 3 && has7 == 2 -> number += dec * 5
                    }
                }
                6 -> {
                    // can be 0, 6, 9
                    val has4 = value.sumOf { if (mapping[4]!!.contains(it)) 1 else 0 as Int }
                    val has7 = value.sumOf { if (mapping[7]!!.contains(it)) 1 else 0 as Int }
                    when {
                        has4 == 3 && has7 == 3 -> number += dec * 0
                        has4 == 3 && has7 == 2 -> number += dec * 6
                        has4 == 4 && has7 == 3 -> number += dec * 9
                    }
                }
            }
        }
        partTwo += number
    }
    println("[part one] $partOne")
    println("[part two] $partTwo")    
}