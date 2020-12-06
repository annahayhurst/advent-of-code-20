import java.io.File

// Advent of Code - Day 6
// Custom Customs

fun main() {
    val allAnswers = readAllAnswersAsText(".\\data\\answers.txt")

    // Why is processing AOC input data always the worst part?
    // I ended up changing the starting format:
    // > Groups of people are separated by spaces
    // > Answers that different people gave within a group are separated by dashes
    val answersByGroup: List<String> = allAnswers.split(" ")

    // Part 1 - What is the sum of the unique answers chosen by all groups?
    var uniqueAnswerCounter = 0
    answersByGroup.forEach {
        uniqueAnswerCounter += getUniqueAnswersForGroup(it)
    }
    println("Part 1: $uniqueAnswerCounter 🎄") // -> 6382

    // Part 2 - What is the sum of the number of answers that EVERYONE chose in a group?
    var commonAnswerCounter = 0
    answersByGroup.forEach {
        commonAnswerCounter += getCommonAnswersForGroup(it)
    }
    println("Part 2: $commonAnswerCounter 🎄")
}

private fun getUniqueAnswersForGroup(group: String): Int =
    AnswerEvaluator.countUnique(group.filter { it != '-' })

private fun getCommonAnswersForGroup(group: String): Int =
    AnswerEvaluator.countCommon(group.split("-"))

// Helpers
private fun readAllAnswersAsText(fileName: String): String
    = File(fileName).readText(Charsets.UTF_8)
