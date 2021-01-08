// Object that can run through boot code in order to simulate what it does
object Simulator {

    // Run the input boot code until we hit the second loop of the instructions, return the accumulator value
    fun simulateFirstLoopOf(bootCode: List<OpCode>): Int {
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

        return accumulator
    }
}