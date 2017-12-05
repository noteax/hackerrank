package algorithms.sorting

import algorithms.sorting.FullCountingSort.Item
import org.specs2.mutable.Specification
import org.scalacheck.{Arbitrary, Gen}

class FullCountingSortTest extends Specification with org.specs2.ScalaCheck {

  override def is =
    s2"""

  FullCountingSort spec

  Solution should
   work on simple case                 $simple
   work on big input                   $bigInput
                                                      """

  def simple = {
    val items = List(
      Item(0, "ab"),
      Item(6, "cd"),
      Item(0, "ef"),
      Item(6, "gh"),
      Item(4, "ij"),
      Item(0, "ab"),
      Item(6, "cd"),
      Item(0, "ef"),
      Item(6, "gh"),
      Item(0, "ij"),
      Item(4, "that"),
      Item(3, "be"),
      Item(0, "to"),
      Item(1, "be"),
      Item(5, "question"),
      Item(1, "or"),
      Item(2, "not"),
      Item(4, "is"),
      Item(2, "to"),
      Item(4, "the"))

    FullCountingSort.getItems(items, 20) should beEqualTo(List(
      "-", "-", "-", "-", "-", "to", "be", "or", "not", "to", "be", "-", "that", "is", "the", "question", "-", "-", "-", "-"
    ))

  }

  def bigInput = {
    val items = Gen.listOfN(1000000, Gen.choose(0, 99).map(v => Item(v, "ss"))).sample.get
    FullCountingSort.getItems(items, items.length).length should beGreaterThan(0)
  }

}
