package algorithms.sorting

import scala.collection.mutable

object CountSortPart1 extends App {

  val d = scala.io.StdIn.readLine.toInt
  val items = scala.io.StdIn.readLine.split(' ').map(_.toInt)
  println(countSort1(items).mkString(" "))

  def countSort1(items: Array[Int]) = {
    val counts = mutable.ArrayBuffer.fill(100)(0)

    for (i <- items.indices) {
      counts(items(i)) += 1
    }

    counts
  }

}
