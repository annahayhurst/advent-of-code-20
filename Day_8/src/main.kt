import java.io.File

// Advent of Code
// Day 8 - Handheld Halting

fun main() {
    val allLines: List<String> = File(".\\data\\boot-code.txt").readLines()

    val bootCode: List<OpCode> = generateBootCode(allLines)

    // Part 1 -> 1548
    val singleLoopAccumulatorValue = Simulator.simulate(bootCode).accumulator
    println("Part 1: $singleLoopAccumulatorValue ✨")

    // Part 2 -> 1375
    // "[Simulator] Fixed instruction at index 226: changed JMP -159 to NOP -159"
    val fixedBootCode = Simulator.fix(bootCode)
    val fullAccumulatorValue = Simulator.simulate(fixedBootCode).accumulator
    println("Part 2: $fullAccumulatorValue ✨")
}

fun generateBootCode(input: List<String>): List<OpCode> {
    var output: MutableList<OpCode> = mutableListOf()
    for (line in input) {
        val components = line.split(" ")

        // e.g. convert acc -> ACC, retrieve enum value
        val instruction = Instruction.valueOf(components[0].toUpperCase())
        // Convert value to int
        val value = components[1].toInt()

        output.add(OpCode(instruction, value))
    }

    return output
}