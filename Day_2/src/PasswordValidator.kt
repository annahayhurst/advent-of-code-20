data class Rule(val range: Pair<Int, Int>, val letter: Char)

object PasswordValidator {

    fun validate(password: String, rule: Rule): Boolean {
        val characters: List<Char> = password.toList()
        val count = characters.count { it == rule.letter }

        return count >= rule.range.first && count <= rule.range.second
    }

}