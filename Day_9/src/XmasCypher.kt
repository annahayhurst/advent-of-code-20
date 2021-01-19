class XmasCypher(private val preamble: List<Long>, private val code: List<Long>) {
    private var validValues: List<Long>
    private var currentPreamble: MutableList<Long> = preamble.toMutableList()

    var vulnerability: CypherVulnerability
    var listOfWeakValues: List<Long>
    var encryptionWeakness: Long

    init {
        validValues = getCurrentValidValues()
        vulnerability = findVulnerability()
        listOfWeakValues = findWeakValues()

        encryptionWeakness = generateEncryptionWeakness()
    }

    private fun findVulnerability(): CypherVulnerability {
        for(i in code.indices) {
            val currentValue = code[i]
            // If this value isn't in the current list of valid values, return it
            if(!isValueValid(currentValue)) return CypherVulnerability(currentValue, i)

            // If it is valid, update the current list of valid values to use this one.
            currentPreamble = currentPreamble.drop(1).toMutableList()
            currentPreamble.add(currentValue)
            validValues = getCurrentValidValues()
        }

        // Incorrect code input if we reach this point
        return CypherVulnerability(-1, -1)
    }

    private fun findWeakValues(): List<Long> {
        val (value, index) = vulnerability
        // We need the preamble and the code together for this search
        val fullCode: List<Long> = listOf(preamble, code).flatten()

        // This is a mess but I'm mega tired so... 💁‍♀️
        for(i in 0..index + preamble.size) {
            var collectedValues: MutableList<Long> = mutableListOf()
            var counter: Long = 0
            var j = i

            // Look through sets of contiguous values in the list. If they add up to the target value, return them
            while (counter < value && j < index + preamble.size) {
                collectedValues.add(fullCode[j])
                counter += fullCode[j]

                if (counter == value) return collectedValues

                j++
            }
        }

        return listOf()
    }

    private fun generateEncryptionWeakness(): Long {
        val sortedWeakValues = listOfWeakValues.sorted()
        return sortedWeakValues.first() + sortedWeakValues.last()
    }

    private fun isValueValid(value: Long): Boolean = validValues.contains(value)

    private fun getCurrentValidValues(): List<Long> {
        var output = mutableListOf<Long>()

        for (firstValue in currentPreamble) {
            for (secondValue in currentPreamble) {
                if (firstValue != secondValue) output.add(firstValue + secondValue)
            }
        }

        return output
    }
}

data class CypherVulnerability(val value: Long, val index: Int)