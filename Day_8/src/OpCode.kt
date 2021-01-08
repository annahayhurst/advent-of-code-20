// All possible instructions; NOP is no-op, ACC changes the accumulator value, JMP moves to a different instruction.
enum class Instruction {
    NOP, ACC, JMP
}

// Data class representing an instruction and the value it holds (positive or negative)
data class OpCode(val instruction: Instruction, val value: Int)