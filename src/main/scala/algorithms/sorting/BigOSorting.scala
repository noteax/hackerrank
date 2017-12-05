package algorithms.sorting

import scala.math.Ordering

object BigOSorting {
  def main(args: Array[String]): Unit = {
    val d = scala.io.StdIn.readLine.toInt

    val items = (0 until d)
      .map(v => scala.io.StdIn.readLine)
      .toList

    printSorted(items)
  }

  def printSorted(items: Seq[String]): Unit = {
    implicit val ordering = new Ordering[String] {
      def compare(x: String, y: String) = {
        if (x.length == y.length) {
          x.compare(y)
        } else {
          x.length.compare(y.length)
        }
      }
    }
    items.sorted
      .foreach {
        println(_)
      }
  }

}
