package algorithms.search

import scala.collection.mutable

object Pairs extends App {

  val Array(n, k) = scala.io.StdIn.readLine.split(" ").map(_.toInt)
  val items = scala.io.StdIn.readLine.split(' ').map(_.toInt)

  println(pairs(items, k))

  def pairs(items: Seq[Int], k: Int) = {
    val memo = new mutable.HashSet[Int]()
    items.map(v => {
      val r1 = if (memo.contains(v - k)) 1 else 0
      val r2 = if (memo.contains(v + k)) 1 else 0
      memo.add(v)
      r1 + r2
    }).sum
  }

}
