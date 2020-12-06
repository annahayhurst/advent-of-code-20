object AnswerEvaluator {
    fun countUnique(input: String): Int = input.toSet().size

    fun countCommon(input: List<String>): Int {
        if (input.size == 1) return input.first().length

        // Seeing as they need to be shared by everyone, we can consider everything that
        // is in the first set of answers and compare it to each other set of answers.
        // After all, anything that isn't in the first set can't be common to all of them.
        var counter = 0
        input.first().forEach {
            if(allElementsContainSharedChar(it, input.drop(1))) counter++
        }

        return counter
    }

    private fun allElementsContainSharedChar(char: Char, list: List<String>): Boolean
        = list.filter { it.contains(char) }.size == list.size
}