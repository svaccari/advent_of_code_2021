fun main() {
    var input = mutableListOf(5,1,1,1,3,5,1,1,1,1,5,3,1,1,3,1,1,1,4,1,1,1,1,1,2,4,3,4,1,5,3,4,1,1,5,1,2,1,1,2,1,1,2,1,1,4,2,3,2,1,4,1,1,4,2,1,4,5,5,1,1,1,1,1,2,1,1,1,2,1,5,5,1,1,4,4,5,1,1,1,3,1,5,1,2,1,5,1,4,1,3,2,4,2,1,1,4,1,1,1,1,4,1,1,1,1,1,3,5,4,1,1,3,1,1,1,2,1,1,1,1,5,1,1,1,4,1,4,1,1,1,1,1,2,1,1,5,1,2,1,1,2,1,1,2,4,1,1,5,1,3,4,1,2,4,1,1,1,1,1,4,1,1,4,2,2,1,5,1,4,1,1,5,1,1,5,5,1,1,1,1,1,5,2,1,3,3,1,1,1,3,2,4,5,1,2,1,5,1,4,1,5,1,1,1,1,1,1,4,3,1,1,3,3,1,4,5,1,1,4,1,4,3,4,1,1,1,2,2,1,2,5,1,1,3,5,2,1,1,1,1,1,1,1,4,4,1,5,4,1,1,1,1,1,2,1,2,1,5,1,1,3,1,1,1,1,1,1,1,1,1,1,2,1,3,1,5,3,3,1,1,2,4,4,1,1,2,1,1,3,1,1,1,1,2,3,4,1,1,2)
    var group = mutableMapOf<Int, Long>()
    input.forEach {
        if (!group.containsKey(it)) {
            group[it] = 0
        }
        group[it] = group[it]!! + 1
    }
    for (day in 1..256) {
        var newGroup = mutableMapOf<Int, Long>()
        for (i in -1..8) {
            newGroup[i] = 0
        }
        group.keys.forEach {
            newGroup[it - 1] = group[it]!!
        }
        newGroup[6] = newGroup[6]!! + newGroup[-1]!!
        newGroup[8] = newGroup[8]!! + newGroup[-1]!!
        newGroup.remove(-1)
        group = newGroup
        if (day == 80 || day == 256) {
            var sum = 0L
            group.values.forEach { sum += it }
            println("day $day, fish count = $sum")
        }
    }
}