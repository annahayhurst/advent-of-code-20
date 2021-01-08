// Object that can run through boot code in order to simulate what it does
object Simulator {

    // Run the input boot code until either:
    // - We hit the second loop of the op codes in boot code that infinite loops
    // - We hit the final op code in boot code that terminates normally (always an accumulator)
    // Return the accumulator value, and the final step that was reached.
    fun simulate(bootCode: List<OpCode>): SimulationResult {
        // The set of indices corresponding to instructions that have already been run once
        var instructionsAlreadySeen: MutableSet<Int> = mutableSetOf()
        var accumulator = 0

        var index = 0
        // For as long as the current op code is new, and is in a valid position,
        while (!instructionsAlreadySeen.contains(index) && bootCode.indices.contains(index)) {
            // add the current instruction to the set of those seen,
            instructionsAlreadySeen.add(index)

            // and update the simulator state according to its instruction and value.
            val (instruction, value) = bootCode[index]
            when (instruction) {
                Instruction.NOP -> index++
                Instruction.JMP -> index += value
                Instruction.ACC -> {
                    accumulator += value
                    index++
                }
            }
        }

        return SimulationResult(accumulator, index)
    }

    // Change a single instruction in order to prevent boot code from infinite looping.
    // We don't care about the accumulator here - we just need to see which instruction could be flipped
    // in order to reach the final index in the code.
    fun fix(bootCode: List<OpCode>): List<OpCode> {

        for (index in bootCode.indices) {
            val swappedBootCode = swapInstructionAt(index, bootCode)
            val (_, finalStep) = simulate(swappedBootCode)

            if (finalStep == bootCode.size) {
                println("[Simulator] Fixed instruction at index $index: changed ${bootCode[index]} to ${swappedBootCode[index]}")
                return swappedBootCode
            }
        }

        // If we reach this point, the code isn't broken, or something went wrong
        println("[Simulator] Didn't fix input boot code")
        return bootCode
    }

    private fun swapInstructionAt(index: Int, bootCode: List<OpCode>): List<OpCode> {
        var output: MutableList<OpCode> = bootCode.toMutableList()

        var swappedOpCode = OpCode(output[index].instruction, output[index].value)
        swappedOpCode.swapInstruction()
        output[index] = swappedOpCode

        return output
    }
}

// Data class representing the output from a simulation run
data class SimulationResult(val accumulator: Int, val finalStepExecuted: Int)