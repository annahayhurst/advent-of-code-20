import java.io.File
import kotlin.math.ceil

// Advent of Code - Day 5
// Binary Boarding

fun main() {
    // Start with the lowest possible pass ID to compare to
    var highestPass = BoardingPass("FFFFFFFLLL")
    File(".\\data\\boarding_passes.txt").forEachLine {
        val currentPass = BoardingPass(it)

        if (currentPass.id > highestPass.id) {
            highestPass = currentPass
        }
    }

    // Part 1
    println("Part 1: $highestPass") // -> (113, 4) with ID 908
}