package joltage

// Abstraction of a joltage adapter plug
case class JoltageAdapter(rating: Int) extends Equals {
  // An adapter can connect to another adapter up to 3 jolts higher than its own rating
  val acceptedAdapterRatings: List[Int] = List[Int](rating, rating + 1, rating + 2, rating + 3)

  // Equals interface methods
  def canEqual(that: Any) = that.isInstanceOf[JoltageAdapter]

  override def equals(that: Any): Boolean = {
    that match {
      case that: JoltageAdapter => {
        that.canEqual(this) &&
          that.rating == this.rating
      }
      case _ => false
    }
  }
}


