import java.io.File
import kotlin.math.pow

fun main(args: Array<String>) {
    val input = File(args[0]).useLines { it.toList() }
    val columns = input.first().length
    var gamma = 0
    var epsilon = 0
    for (pos in 0 until columns) {
        val column = (columns - 1) - pos
        val oneCount = input.filter { it[column] == '1' }.size
        if (oneCount >= input.size - oneCount) {
            gamma += 2.0.pow(pos).toInt()
        } else {
            epsilon += 2.0.pow(pos).toInt()
        }
    }
    println("[part 1] gamma $gamma, epsilon $epsilon = ${gamma * epsilon}")
    
    // part two
    var oRating = input.toMutableList()
    var cRating = input.toMutableList()
    for (column in 0 until columns) {
        val oOneCount = oRating.filter { it[column] == '1' }.size
        val oMostCommon = if (oOneCount >= oRating.size - oOneCount) '1' else '0'
        val cOneCount = cRating.filter { it[column] == '1' }.size
        val cLeastCommon = if (cOneCount < cRating.size - cOneCount) '1' else '0'
        if (oRating.size > 1) {
            oRating = oRating.filter { it[column] == oMostCommon }.toMutableList()
        }
        if (cRating.size > 1) {
            cRating = cRating.filter { it[column] == cLeastCommon }.toMutableList()
        }
    }
    val a = binaryToInt(oRating.first())
    val b = binaryToInt(cRating.first())
    println("[part 2] $a $b = ${a * b}")
}

fun binaryToInt(s: String): Int {
    var output = 0
    for (pos in 0 until s.length) {
        val column = (s.length - 1) - pos
        if (s[column] == '1') {
            output += 2.0.pow(pos).toInt()
        }
    }
    return output
}