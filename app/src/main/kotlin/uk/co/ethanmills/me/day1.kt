fun day1() {
    var currentPosition = 50
    var zeroCrossings = 0
    var zeroLandings = 0
    val input = getLines("day1.txt")

    for (item in input) {
        val rotation = calculateRotation(item)
        val rotationResult = applyRotation(currentPosition, rotation)
        currentPosition = rotationResult.newValue
        zeroCrossings += rotationResult.zeroCrossings
        if (currentPosition == 0) {
            zeroLandings++
        }
    }
    println("Part 1: ${zeroLandings} Part 2: ${zeroCrossings}")

}

fun calculateRotation(input: String): Int {
    val direction = input.substring(0, 1)
    val amount = input.substring(1).toInt()
    return when(direction) {
        "L" -> -amount
        "R" -> amount
        else -> throw Error("invalid input")
    }
}

data class RotationResult(val newValue: Int, val zeroCrossings: Int)

fun applyRotation(current: Int, change: Int): RotationResult {
    var result = current + change
    val negative = result < 0
    var zeroCrossings = 0
    if (current == 0 && result < 0) {
        zeroCrossings = -1
    }

    if (result == 0) {
        zeroCrossings = 1
    }

    while (result < 0) {
        zeroCrossings++
        result += 100
    }

    if (negative && result == 0) {
        zeroCrossings ++
    }

    while (result > 99) {
        zeroCrossings++
        result -= 100
    }

    return RotationResult(result, zeroCrossings)
}