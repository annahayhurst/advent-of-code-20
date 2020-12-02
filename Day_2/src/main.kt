import java.io.File

// Advent of Code - Day 2
// Password Philosophy

fun main() {
    var correctAmountCounter = 0
    var correctPositionCounter = 0


    File(".\\data\\passwords.txt").forEachLine {
        val splitString = it.split(":")

        if (splitString.size == 2) {
            val password = splitString[1]
            val rule = parseRule(splitString[0])

            // Part 1
            if (PasswordValidator.validateAmount(password, rule)) {
                correctAmountCounter++
            }

            // Part 2
            if (PasswordValidator.validatePositions(password, rule)) {
                correctPositionCounter++
            }
        }
    }

    println("Part 1: $correctAmountCounter 🎄") // -> 640
    println("Part 2: $correctPositionCounter 🎄") // -> 472

}

private fun parseRule(string: String): Rule {
    val components = string.split("-", " ")

    val range: Pair<Int, Int> = Pair(components[0].toInt(), components[1].toInt())
    val letter = components[2].first()

    return Rule(range, letter)
}