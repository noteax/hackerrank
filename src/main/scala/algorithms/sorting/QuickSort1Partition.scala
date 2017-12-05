package algorithms.sorting

import scala.collection.mutable.ListBuffer

object QuickSort1Partition extends App {

  val d = scala.io.StdIn.readLine.toInt
  val items = scala.io.StdIn.readLine.split(' ').map(_.toInt)

  var result = new ListBuffer[Int]()

  for (i <- 0 until d) {
    if (items(i) < items(0)) {
      // add to left
      items(i) +=: result
    } else {
      result += items(i)
    }
  }
  println(result.mkString(" "))
}
