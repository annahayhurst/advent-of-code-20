import java.io.File

// Advent of Code
// Day 8 - Handheld Halting

fun main() {
    val allLines: List<String> = File(".\\data\\boot-code.txt").readLines()

    val bootCode: List<OpCode> = generateBootCode(allLines)

    // Part 1
    val accumulatorValue = Simulator.simulateFirstLoopOf(bootCode)
    println("Part 1: $accumulatorValue ✨")
}

fun generateBootCode(input: List<String>): List<OpCode> {
    var output: MutableList<OpCode> = mutableListOf()
    for (line in input) {
        val components = line.split(" ")

        // e.g. convert acc -> ACC, retrieve enum value
        val instruction = Instruction.valueOf(components[0].toUpperCase())
        // Remove + from beginning of value if it exists, convert to number
        val valueAsString = components[1]
        val value = if (valueAsString[0] == '+') valueAsString.drop(1).toInt() else valueAsString.toInt()

        output.add(OpCode(instruction, value))
    }

    return output
}