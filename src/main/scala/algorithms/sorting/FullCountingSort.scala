package algorithms.sorting

import scala.collection.mutable

object FullCountingSort {

  def main(args: Array[String]): Unit = {
    val d = scala.io.StdIn.readLine.toInt
    print(getItems2(
      (1 to d)
        .map(v => scala.io.StdIn.readLine.split(" "))
        .map(v => Item(v(0).toInt, v(1))), d
    ))
  }

  case class Item(index: Int, term: String)

  def getItems(items: Iterable[Item], size: Int): List[String] = {
    val buffer = Array.fill[mutable.MutableList[String]](100)(new mutable.MutableList[String])
    items
      .zipWithIndex
      .map(v => if (v._2 < size / 2) Item(v._1.index, "-") else v._1)
      .foreach(item => buffer(item.index) += item.term)
    buffer.flatten.toList
  }

  def getItems2(items: Iterable[Item], size: Int): String = {
    val buffer = Array.fill[mutable.StringBuilder](100)(new mutable.StringBuilder)
    items
      .zipWithIndex
      .map(v => if (v._2 < size / 2) Item(v._1.index, "-") else v._1)
      .foreach(item => buffer(item.index).append(item.term))

    val s = new mutable.StringBuilder
    buffer.foreach(v => s.append(v+" "))
    s.toString()
  }




}
