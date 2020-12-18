import java.io.File
import kotlin.math.ceil

// Advent of Code - Day 5
// Binary Boarding

fun main() {
    var allBoardingPasses: MutableList<BoardingPass> = mutableListOf()

    // Start with the lowest possible pass ID to compare to
    var highestPass = BoardingPass("FFFFFFFLLL")
    File(".\\data\\boarding_passes.txt").forEachLine {
        val currentPass = BoardingPass(it)
        allBoardingPasses.add(currentPass)

        if (currentPass.id > highestPass.id) {
            highestPass = currentPass
        }
    }

    // Part 1
    println("Part 1: $highestPass") // -> (113, 4) with ID 908

    // Part 2
    val sortedPasses = allBoardingPasses.sortedBy { it.id }
    println("Part 2: ${findSeatId(sortedPasses)}")

}

// Find the place in the list where an id is missing, i.e. doesn't follow the linear sequence
// e.g. if somewhere in the list we have ids [499, 501, 502...] the seat id is 500
fun findSeatId(passes: List<BoardingPass>): Int {
    for (i in passes.indices) {
        if(passes[i + 1].id != passes[i].id + 1) {
            return passes[i].id + 1
        }
    }

    return 0
}