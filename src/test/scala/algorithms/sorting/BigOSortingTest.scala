package algorithms.sorting

import org.scalacheck.Gen
import org.specs2.mutable.Specification

class BigOSortingTest extends Specification with org.specs2.ScalaCheck {

  override def is =
    s2"""

  BigOSortingTest spec

  Solution should
      not timeout on big input                     $bigInput
                                                      """


  //
  def bigInput = {
    val items = Gen.listOfN(10, Gen.choose(900000, 1000000).map(v => "3" * v)).sample.get
    BigOSorting.printSorted(items)
    0 must beEqualTo(0)
  }


}
