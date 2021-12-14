
fun main() {
    for (step in 1..10) {
        template = step(template)
    }
    val charsMap = mutableMapOf<Char, Long>()
    template.forEach { charsMap[it] = charsMap.getOrDefault(it, 0) + 1 }
    var min = charsMap.minByOrNull { it.value }!!.value
    var max = charsMap.maxByOrNull { it.value }!!.value
    println("[part one] $min $max = ${max - min}")
    // part two
    charsMap.clear()
    var tuples = mutableMapOf<String, Long>()
    for (i in 0 until templatePartTwo.length - 1) {
        val str = templatePartTwo.substring(i, i + 2)
        tuples[str] = tuples.getOrDefault(str, 0) + 1
    }
    for (step in 1..40) {
        tuples = stepPartTwo(tuples)
    }
    // count single chars
    tuples.forEach { tuple -> tuple.key.forEach { c -> charsMap[c] = charsMap.getOrDefault(c, 0) + tuple.value } }
    // add 1 to first and last template chars
    charsMap[templatePartTwo.first()] = charsMap.getOrDefault(templatePartTwo.first(), 0) + 1
    charsMap[templatePartTwo.last()] = charsMap.getOrDefault(templatePartTwo.last(), 0) + 1
    // divide by two
    charsMap.keys.forEach { key -> charsMap[key] = charsMap[key]!! / 2}
    min = charsMap.minByOrNull { it.value }!!.value
    max = charsMap.maxByOrNull { it.value }!!.value
    println("[part two] $min $max = ${max - min}")
}

fun step(t: String): String {
    var output = t
    var index = 0
    while (index < output.length - 1) {
        val test = "${output[index]}${output[index + 1]}"
        val insert = input.firstOrNull { it.first == test }!!.second
        output = output.substring(0, index + 1) + insert + output.substring(index + 1, output.length)
        index += 2
    }
    return output
}

fun stepPartTwo(inputTuples: MutableMap<String, Long>): MutableMap<String, Long> {
    val output = mutableMapOf<String, Long>()
    inputTuples.forEach { tuple ->
        val insert = input.firstOrNull { it.first == tuple.key }!!.second
        val a = tuple.key.first() + insert
        val b = insert + tuple.key.last()
        output[a] = output.getOrDefault(a, 0) + tuple.value
        output[b] = output.getOrDefault(b, 0) + tuple.value
    }
    return output
}

var template = "CBNBOKHVBONCPPBBCKVH"
var templatePartTwo = "CBNBOKHVBONCPPBBCKVH"

val input = listOf(
Pair("FK", "O"),
Pair("BK", "B"),
Pair("PB", "N"),
Pair("VS", "P"),
Pair("OF", "H"),
Pair("KP", "K"),
Pair("PS", "K"),
Pair("OV", "N"),
Pair("FO", "H"),
Pair("KN", "P"),
Pair("HF", "K"),
Pair("BV", "N"),
Pair("OO", "B"),
Pair("KC", "V"),
Pair("CK", "H"),
Pair("BC", "P"),
Pair("VV", "S"),
Pair("NS", "C"),
Pair("SF", "O"),
Pair("BN", "V"),
Pair("NH", "N"),
Pair("VP", "F"),
Pair("KH", "S"),
Pair("BO", "N"),
Pair("VN", "K"),
Pair("BB", "H"),
Pair("CH", "H"),
Pair("HP", "O"),
Pair("KK", "O"),
Pair("CB", "S"),
Pair("VC", "P"),
Pair("FH", "B"),
Pair("SP", "C"),
Pair("NF", "O"),
Pair("HN", "N"),
Pair("PO", "P"),
Pair("PP", "C"),
Pair("SO", "F"),
Pair("FB", "B"),
Pair("SB", "B"),
Pair("SC", "B"),
Pair("HK", "O"),
Pair("BF", "V"),
Pair("OB", "B"),
Pair("NC", "V"),
Pair("HC", "F"),
Pair("KO", "C"),
Pair("NV", "C"),
Pair("HB", "H"),
Pair("FP", "S"),
Pair("OS", "O"),
Pair("HH", "K"),
Pair("OK", "B"),
Pair("OH", "C"),
Pair("NP", "V"),
Pair("SN", "H"),
Pair("SK", "B"),
Pair("HV", "F"),
Pair("VF", "P"),
Pair("CP", "H"),
Pair("FN", "H"),
Pair("FV", "B"),
Pair("CN", "H"),
Pair("OC", "O"),
Pair("KV", "P"),
Pair("CF", "B"),
Pair("OP", "B"),
Pair("FC", "O"),
Pair("PC", "B"),
Pair("CV", "S"),
Pair("PV", "H"),
Pair("VK", "N"),
Pair("SS", "C"),
Pair("HO", "F"),
Pair("VH", "C"),
Pair("NB", "S"),
Pair("NN", "F"),
Pair("FF", "K"),
Pair("CC", "H"),
Pair("SV", "H"),
Pair("CO", "K"),
Pair("BP", "O"),
Pair("SH", "H"),
Pair("KS", "K"),
Pair("FS", "F"),
Pair("PF", "S"),
Pair("BS", "H"),
Pair("VO", "H"),
Pair("NK", "F"),
Pair("PK", "B"),
Pair("KB", "K"),
Pair("CS", "C"),
Pair("VB", "V"),
Pair("BH", "O"),
Pair("KF", "N"),
Pair("HS", "H"),
Pair("PH", "K"),
Pair("ON", "H"),
Pair("PN", "K"),
Pair("NO", "S")
)