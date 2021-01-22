package joltage_utils

// Abstraction of a joltage adapter plug
class JoltageAdapter(val rating: Int) {
  // An adapter can accept its rated joltage and up to 3 jolts lower
  val acceptedJoltages: List[Int] = List[Int](rating, rating - 1, rating - 2, rating - 3)
}


