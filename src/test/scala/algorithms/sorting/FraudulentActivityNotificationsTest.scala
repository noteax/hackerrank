package algorithms.sorting

import org.scalacheck.{Arbitrary, Gen}
import org.specs2.mutable.Specification


class FraudulentActivityNotificationsTest extends Specification with org.specs2.ScalaCheck {

  override def is =
    s2"""

  FraudulentActivityNotifications spec

  Solution should
    (Tree) handle simple case                           $simple
    (Tree) not timeout on big input                     $bigInputTree
    (CountSort) not timeout on big input                $bigInputCountSort
    (Tree) pass all small property based                $propertyBasedTreeVersion
    (CountSort) pass all small property based           $propertyBasedCountSortVersion
                                                      """

  def simple =
    FraudulentActivityNotifications.activityNotificationsTreeBased(List(126, 49, 94, 33, 131), 2) must beEqualTo(1)

  def bigInputTree =
    FraudulentActivityNotifications.activityNotificationsTreeBased(1 to 200000, 10000) must beEqualTo(1)

  def bigInputCountSort =
    FraudulentActivityNotifications.activityNotificationsCountSort(List.fill(1000)(1 to 200).flatten, 10000) must beEqualTo(0)

  //
  def inputData = for {
    numElems <- Gen.choose(1, 1000)
    d <- Gen.choose(1, numElems)
    elems <- Gen.listOfN(numElems, Gen.choose(0, 200))
  } yield (elems, d)

  implicit def inputArbitrary: Arbitrary[(List[Int], Int)] =
    Arbitrary(inputData)

  def propertyBasedTreeVersion = {
    prop((s: (List[Int], Int)) => FraudulentActivityNotifications.activityNotificationsTreeBased(s._1, s._2)
      must beEqualTo(FraudulentActivityNotifications.activityNotificationsDummy(s._1, s._2))).setArbitrary(inputArbitrary)
  }

  def propertyBasedCountSortVersion = {
    prop((s: (List[Int], Int)) => FraudulentActivityNotifications.activityNotificationsCountSort(s._1, s._2)
      must beEqualTo(FraudulentActivityNotifications.activityNotificationsDummy(s._1, s._2))).setArbitrary(inputArbitrary)
  }

}
