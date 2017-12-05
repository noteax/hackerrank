package algorithms.search

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object CutTheTree extends App {

  val n = scala.io.StdIn.readLine.toInt
  val nodesWithWeight = scala.io.StdIn.readLine.split(' ')
    .map(_.toInt)
    .zipWithIndex
    .map(v => v._2 + 1 -> Node(v._2 + 1, v._1, ListBuffer.empty))
    .toMap

  val toNodesSet = new mutable.HashSet[Int]()

  val sum = nodesWithWeight.values.map(v => v.weight).sum

  (1 until n)
    .map(v => scala.io.StdIn.readLine.split(" ").map(_.toInt))
    .map(v => v(0) -> v(1))
    .foreach(v => {
      nodesWithWeight(v._1).children += nodesWithWeight(v._2)
      nodesWithWeight(v._2).children += nodesWithWeight(v._1)
    })

  println(getCutDifference(nodesWithWeight(1), sum))

  def getCutDifference(rootNode: Node, sum: Int) = {
    var minDiff = sum

    def computeNodeWeight(currentNode: Node, parentNode: Option[Node]): Int = {
      val weight = currentNode.weight + currentNode.children
        .filterNot(v => parentNode.contains(v))
        .map(currentChild => computeNodeWeight(currentChild, Some(currentNode)))
        .sum
      val diff = Math.abs(sum - 2 * weight)
      if (diff < minDiff) minDiff = diff
      weight
    }

    computeNodeWeight(rootNode, Option.empty)
    minDiff
  }

  case class Node(index: Int, weight: Int, children: ListBuffer[Node])

}
