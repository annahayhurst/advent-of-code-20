import kotlin.math.ceil

// Models a boarding pass and can create one out of an incoming valid string
// No validation because I'm tired today and the data set is all valid anyway
class BoardingPass(pass: String) {
    private val maxRow = 127
    private val maxColumn = 7

    private val rowTextLength = 6
    private val columnTextLength = 2

    val text = pass
    val row: Int
    val column: Int
    val id: Int

    init {
        row = calculateRow(pass)
        column = calculateColumn(pass.drop(7))
        id = (row * 8) + column
    }

    override fun toString(): String =
        """
            Original text: $text
            Row: $row
            Column: $column
            ID: $id
        """.trimIndent()

    private fun calculateRow(input: String): Int {
        var lower = 0
        var upper = maxRow

        for (i in 0..rowTextLength) {
            when (input[i]) {
                'F' -> upper = (lower + upper + 1)/2 - 1 // Front half of current row set
                'B' -> lower = (lower + upper + 1)/2     // Back half of current row set
            }
        }

        // The final character determines which bound is used
        return when(input[rowTextLength]) {
            'F' -> lower
            'B' -> upper
            else -> 0
        }
    }

    private fun calculateColumn(input: String): Int {
        var lower = 0
        var upper = maxColumn

        for (i in 0..columnTextLength) {
            when (input[i]) {
                'L' -> upper = (lower + upper + 1)/2 - 1 // Left half of current column set
                'R' -> lower = (lower + upper + 1)/2     // Right half of current column set
            }
        }

        // The final character determines which bound is used
        return when(input[columnTextLength]) {
            'L' -> lower
            'R' -> upper
            else -> 0
        }
    }


}