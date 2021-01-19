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

    // Part 1
    var vulnerability = cypher.vulnerability
    println("Part 1: First invalid value for this code is ${vulnerability.value} at index ${vulnerability.index} ✨") // -> 542529149

    // Part 2
    var weakValues = cypher.listOfWeakValues
    var encryptionWeakness = cypher.encryptionWeakness
    println("Part 2: The set of values that add up to the vulnerable value are: $weakValues") // -> [An array of very big numbers!]
    println("Therefore, the encryption weakness is: $encryptionWeakness 🎉") // -> 75678618
}