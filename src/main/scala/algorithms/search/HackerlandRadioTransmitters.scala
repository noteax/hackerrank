package algorithms.search

object HackerlandRadioTransmitters extends App {

  val Array(n, k) = scala.io.StdIn.readLine.split(" ").map(_.toInt)
  val items = scala.io.StdIn.readLine.split(' ').map(_.toInt)
  println(radioTransmitter(k, items))

  def radioTransmitter(k: Int, items: Array[Int]) = {
    val sortedItems = items.sorted.toList.toArray

    var transmittersCount: Int = 0
    var lastTransmitter: Option[Int] = Option.empty
    var firstUncoveredHouse: Option[Int] = sortedItems.headOption
    for (i <- sortedItems.indices) {
      if (lastTransmitter.isDefined && lastTransmitter.get + k < sortedItems(i) && firstUncoveredHouse.isEmpty) {
        firstUncoveredHouse = Option(sortedItems(i))
      }

      if (lastTransmitter.isEmpty || lastTransmitter.get + k < sortedItems(i)) {
        // only if not covered by last transmitter
        if (firstUncoveredHouse.get + k > sortedItems(i)) {
          // skip that house
        } else if (firstUncoveredHouse.get + k == sortedItems(i)) {
          // transmitter on current house
          lastTransmitter = Option(sortedItems(i))
          transmittersCount = transmittersCount + 1
          firstUncoveredHouse = Option.empty
        } else {
          // transmitter on previous house
          lastTransmitter = Option(sortedItems(i - 1))
          transmittersCount = transmittersCount + 1
          if (lastTransmitter.get + k < sortedItems(i)) {
            firstUncoveredHouse = Option(sortedItems(i))
          } else {
            firstUncoveredHouse = Option.empty
          }
        }
      }
    }

    if (firstUncoveredHouse.isDefined)
      transmittersCount = transmittersCount + 1


    transmittersCount
  }

}
