package algorithms.search

object ShortPalindrome extends App {

  val line = scala.io.StdIn.readLine
  println(shortPalindromesCount2(line))

  def shortPalindromesCount2(items: String) = {
    implicit class StringImprovements(s: String) {
      def xtails = if (s.length > 0) s.substring(1).tails else Stream.empty
    }

    items.tails.flatMap(tail1 =>
      tail1.xtails.flatMap(tail2 =>
        tail2.xtails.flatMap(tail3 =>
          tail3.xtails.map(item4 =>
            !item4.isEmpty && tail1.charAt(0) == item4.charAt(0) && tail2.charAt(0) == tail3.charAt(0)
          )
        ))).count(v => v)
  }

  def shortPalindromesCount(items: String) = {


    implicit class StringImprovements(s: String) {
      def xtails = if (s.length > 0) s.substring(1).tails else Stream.empty
    }

    items.tails.flatMap(tail1 =>
      tail1.xtails.flatMap(tail2 =>
        tail2.xtails.flatMap(tail3 =>
          tail3.xtails.map(item4 =>
            !item4.isEmpty && tail1.charAt(0) == item4.charAt(0) && tail2.charAt(0) == tail3.charAt(0)
          )
        ))).count(v => v)
  }


}
