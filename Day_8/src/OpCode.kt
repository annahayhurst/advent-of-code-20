// All possible instructions; NOP is no-op, ACC changes the accumulator value, JMP moves to a different instruction.
enum class Instruction {
    NOP, ACC, JMP
}

// Data class representing an instruction and the value it holds (positive or negative)
data class OpCode(var instruction: Instruction, var value: Int) {

    fun swapInstruction() {
        when(instruction) {
            Instruction.NOP -> instruction = Instruction.JMP
            Instruction.JMP -> instruction = Instruction.NOP
            else -> { }
        }
    }

    override fun toString(): String = "${instruction.name} $value"
}