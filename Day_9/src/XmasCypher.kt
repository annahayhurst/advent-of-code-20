class XmasCypher(private val preamble: List<Long>, private val code: List<Long>) {
    private var validValues: List<Long>
    private var currentPreamble: MutableList<Long> = preamble.toMutableList()

    init {
        validValues = generateAllValidValues()
    }

    fun findVulnerability(): Long {
        for(i in code.indices) {
            val currentValue = code[i]
            // If this value isn't in the current list of valid values, return it
            if(!isValueValid(currentValue)) return currentValue

            // If it is valid, update the current list of valid values to use this one.
            currentPreamble = currentPreamble.drop(1).toMutableList()
            currentPreamble.add(currentValue)
            validValues = generateAllValidValues()
        }

        // Incorrect code input if we reach this point
        return -1
    }

    private fun isValueValid(value: Long): Boolean = validValues.contains(value)

    private fun generateAllValidValues(): List<Long> {
        var output = mutableListOf<Long>()

        for (firstValue in currentPreamble) {
            for (secondValue in currentPreamble) {
                if (firstValue != secondValue) output.add(firstValue + secondValue)
            }
        }

        return output
    }
}