fun day2() {
    val input = getLines("day2.txt")[0]
    val ranges = input.split(",").map { it.split("-").map { it.toLong() } }
    val ids = ranges.flatMap {rangeToValues(it)}

    println("Part 1: ${part1(ids)}, Part 2: ${part2(ids)}")
}

fun rangeToValues(range: List<Long>): List<String> {
    return (range[0]..range[1]).toList().map{ it.toString() }
}

fun part1(ids: List<String>): Long {
    return ids.filter { detectDoubled(it) }.map { it.toLong() }.sum() 
}

fun part2(ids: List<String>): Long {
    return ids.filter { detectRepeats(it) }.map { it.toLong() }.sum()
}

fun detectDoubled(id: String): Boolean {
    if (id.length % 2 == 1) {
        return false
    }

    val candidate = id.substring(0, id.length / 2)
    if (candidate + candidate == id) {
        return true
    }

    return false
}

fun detectRepeats(id: String): Boolean {
    var len = 1
    while (len <= (id.length / 2)) {
        if (id.length % len != 0) {
            len++
            continue
        }
        val repeats = id.length / len

        val candidate = id.substring(0, len)

        if (candidate.repeat(repeats) == id) {
            return true
        }
        len++
    }

    return false
}