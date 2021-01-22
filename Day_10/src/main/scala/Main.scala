import scala.io.Source
import scala.collection.mutable.ListBuffer

import joltage_utils._

// Advent of Code 2020
// Day 10 - Adapter Array

object Main extends App {
  val filename = ".\\src\\main\\data\\example.txt"
  val data: List[Int] = Source.fromFile(filename).getLines.toList.map(x => x.toInt)

  // Turn the data into joltage adapter models
  val convertToAdapter: (Int) => (JoltageAdapter) = rating => { new JoltageAdapter(rating) }
  var buffer = new ListBuffer[JoltageAdapter]()
  data.foreach(buffer += convertToAdapter)
  val adapters = buffer.toList

  // Part 1
}
