package algorithms.search

object KnightL extends App {

  val n = scala.io.StdIn.readLine.toInt
  println(shortestPathMatrix(n).map(v => v.mkString(" ")).mkString("\n"))

  def shortestPathMatrix(n: Int) =
    (1 until n).map(i => (1 until n).map(j => shortestPathLength(n, i, j)))

  def shortestPathLength(n: Int, k: Int, l: Int): Int =
    wideSearch(n, (k, l), 0, List((0, 0)), List())

  def wideSearch(n: Int, step: (Int, Int), currentLayer: Int, currentNodes: List[(Int, Int)], previousNodes: List[(Int, Int)]): Int = {
    val children = currentNodes.flatMap {
      case (x: Int, y: Int) => List(
        (x + step._1, y + step._2),
        (x + step._1, y - step._2),
        (x - step._1, y + step._2),
        (x - step._1, y - step._2),

        (x + step._2, y + step._1),
        (x + step._2, y - step._1),
        (x - step._2, y + step._1),
        (x - step._2, y - step._1)
      )
    }.filter {
      case (x: Int, y: Int) =>
        x >= 0 && y >= 0 && x < n && y < n
    }.filter(x => !previousNodes.contains(x) && !currentNodes.contains(x))
      .distinct

    if (children.isEmpty) {
      -1
    } else if (children.contains((n - 1, n - 1))) {
      currentLayer + 1
    } else {
      wideSearch(n, step, currentLayer + 1, children, currentNodes)
    }
  }
}