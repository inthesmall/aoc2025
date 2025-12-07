import kotlin.math.*

fun day3() {
    // val input = listOf(
    //     "987654321111111",
    //     "811111111111119",
    //     "234234234234278",
    //     "818181911112111",
    // )
    val input = getLines("day3.txt")


    println("Part 1: ${input.map { findMaxJoltage(it, 2) }.sum()}, Part 2: ${input.map { findMaxJoltage(it, 12) }.sum()}")
}

fun findMaxJoltage(bank: String, batteries: Int): Long {
    val digits = bank.map(Character::getNumericValue)
    var joltage: Long = 0
    var dropFront = 0

    for (i in batteries-1 downTo 0) {
        val digit = digits.drop(dropFront).dropLast(i).max()
        dropFront += digits.drop(dropFront).indexOf(digit) + 1
        joltage += digit.toLong() * 10.toDouble().pow(i).toLong()
    }
    
    return joltage
}