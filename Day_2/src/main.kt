import java.io.File

// Advent of Code - Day 2
// Password Philosophy

fun main() {
    var counter = 0
    // Part 1
    File(".\\data\\passwords.txt").forEachLine {
        val splitString = it.split(":")

        if (splitString.size == 2) {
            val password = splitString[1]
            val rule = parseRule(splitString[0])

            if (PasswordValidator.validate(password, rule)) {
                counter++
            }
        }
    }

    println("Part 1: $counter 🎄")

}

private fun parseRule(string: String): Rule {
    val components = string.split("-", " ")

    val range: Pair<Int, Int> = Pair(components[0].toInt(), components[1].toInt())
    val letter = components[2].first()

    return Rule(range, letter)
}