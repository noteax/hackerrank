package algorithms.sorting

object InsertionSortPart2 extends App {

  val d = scala.io.StdIn.readLine.toInt
  val items = scala.io.StdIn.readLine.split(' ').map(_.toInt)
  insertionSort(items)


  def insertionSort(items: Array[Int]) = {
    for (i <- items.indices) {
      // shifting element left
      var completed = false
      val buffer = items(i)
      for (j <- i - 1 to 0 by -1) {
        if (items(j) >= buffer) {
          items(j + 1) = items(j)
          if (j == 0) {
            items(j) = buffer
          }
        } else if (!completed) {
          items(j + 1) = buffer
          completed = true
        }
      }
      if (i != 0)
        println(items.mkString(" "))
    }
  }

}
