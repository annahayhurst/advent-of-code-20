import java.io.File

// Advent of Code - Day 9
// Encoding Error

const val PREAMBLE_SIZE = 25

fun main() {
    val allLines: List<String> = File(".\\data\\data.txt").readLines()
    // We need to map to long rather than int because several of the values in the data cause integer overflow.
    val allNumbers = allLines.map { it.toLong() }

    val preamble = allNumbers.take(PREAMBLE_SIZE)
    val code = allNumbers.drop(PREAMBLE_SIZE)

    val cypher: XmasCypher = XmasCypher(preamble, code)

    var firstInvalidValue = cypher.findVulnerability()
    println("Part 1: First invalid value for this preamble is $firstInvalidValue ✨") // -> 542529149
}