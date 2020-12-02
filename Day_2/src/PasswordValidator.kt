data class Rule(val range: Pair<Int, Int>, val letter: Char)

object PasswordValidator {

    fun validateAmount(password: String, rule: Rule): Boolean {
        val characters: List<Char> = password.toList()
        val count = characters.count { it == rule.letter }

        return count >= rule.range.first && count <= rule.range.second
    }

    fun validatePositions(password: String, rule: Rule): Boolean {
        // At first position?
        var firstPosition = rule.range.first
        var atFirstPosition = (password.length > firstPosition) && (password[firstPosition] == rule.letter)

        // At second position?
        var secondPosition = rule.range.second
        var atSecondPosition = (password.length > secondPosition) && (password[secondPosition] == rule.letter)

        // Only in one of these, and not both? (i.e. exclusive or)
        return atFirstPosition xor atSecondPosition
    }

}