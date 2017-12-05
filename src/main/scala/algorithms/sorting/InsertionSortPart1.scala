package algorithms.sorting

object InsertionSortPart1 extends App {

  val d = scala.io.StdIn.readLine.toInt
  val items = scala.io.StdIn.readLine.split(' ').map(_.toInt)

  // shift from the end until values are not match
  var buf = items(d - 1)
  var completed = false
  for (i <- d - 2 to 0 by -1) {
    if (items(i) >= buf) {
      items(i + 1) = items(i)
      if (i == 0) {
        println(items.mkString(" "))
        items(i) = buf
      }
    } else if (!completed) {
      items(i + 1) = buf
      println(items.mkString(" "))
      completed = true
    }

    if (!completed)
      println(items.mkString(" "))
  }

}
