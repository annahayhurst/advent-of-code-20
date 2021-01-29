package joltage

import scala.collection.mutable.ListBuffer

object AdapterDaisyChainer {

  def findJoltageDifferences(input: List[JoltageAdapter]): JoltageDifferences = {
    var adapters = new ListBuffer[JoltageAdapter]()
    adapters.addAll(input)
    var differences = new ListBuffer[Int]

    while (adapters.nonEmpty) {
      val currentAdapter = adapters.head
      val nextAdapter = findNextMatch(adapters.toList)

      // No next match - add nothing, clear down the list of other adapters
      if(currentAdapter == nextAdapter) {
        adapters.clear()
      } else {
        differences.addOne(nextAdapter.rating - currentAdapter.rating)
        adapters.remove(0, adapters.indexOf(nextAdapter))
      }
    }

    JoltageDifferences(
      oneJolt = differences.count({x => x == 1}),
      twoJolts = differences.count({x => x == 2}),
      threeJolts = differences.count({x => x == 3})
    )
  }

  // Match against the next three entries in the sorted list to see if they're valid
  def findNextMatch(adapters: List[JoltageAdapter]): JoltageAdapter = adapters match {
    case n :: a :: _ if n.acceptedAdapterRatings.contains(a.rating) => a
    case n :: _ :: b :: _ if n.acceptedAdapterRatings.contains(b.rating) => b
    case n :: _ :: _ :: c :: _ if n.acceptedAdapterRatings.contains(c.rating) => c
    // If none of the entries match, just return the starting adapter
    case n :: _ => n
  }

}
