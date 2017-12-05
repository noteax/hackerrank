package algorithms.sorting

object TutorChallenge extends App {

  val searching = scala.io.StdIn.readLine.toInt
  scala.io.StdIn.readLine.toInt
  println(
    scala.io.StdIn.readLine
      .split(' ')
      .map(_.toInt)
      .zipWithIndex
      .find(v => v._1 == searching)
      .get
      ._2)

}
