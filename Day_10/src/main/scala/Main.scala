import scala.io.Source
import scala.collection.mutable.ListBuffer

import joltage._

// Advent of Code 2020
// Day 10 - Adapter Array

object Main extends App {
  val filename = ".\\src\\main\\data\\data.txt"
  val data: List[Int] = Source.fromFile(filename).getLines.toList.map(x => x.toInt)

  // Turn the data into joltage adapter models
  var buffer = new ListBuffer[JoltageAdapter]()
  // First, add a representation of the charging port (rating 0)
  buffer.addOne(JoltageAdapter(0))
  // Add all of the adapters available in your bag
  data.foreach(x => buffer += JoltageAdapter(x))
  // Finally, add a representation of the devices charging port (rating 3 higher than the highest adapter)
  buffer = buffer.sortWith(_.rating < _.rating)
  buffer.addOne(JoltageAdapter(buffer.last.rating + 3))

  val adapters = buffer.toList

  // Part 1
  val joltageDifferences = AdapterDaisyChainer.findJoltageDifferences(adapters)
  val total = joltageDifferences.oneJolt * joltageDifferences.threeJolts
  println(s"Part 1: Number of 1 jolt differences multiplied by 3 jolt differences is: $total 🎉")
}
