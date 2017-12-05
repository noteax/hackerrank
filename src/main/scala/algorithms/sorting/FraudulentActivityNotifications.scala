package algorithms.sorting

import scala.collection.mutable
import collection.JavaConverters._

object FraudulentActivityNotifications {

  def main(args: Array[String]): Unit = {
    val d = scala.io.StdIn.readLine.split(" ").map(_.toInt).last
    val items = scala.io.StdIn.readLine.split(" ").map(_.toInt)
    println(activityNotificationsCountSort(items, d))
  }

  case class Item(value: Int, index: Int)

  case class Median(value: Either[Item, (Item, Item)])

  def activityNotificationsTreeBased(values: Seq[Int], d: Int) = {

    def getMedian(items: java.util.TreeMap[Item, Int]) = {
      val keys = items.keySet().iterator().asScala
      if (items.size() % 2 == 1) {
        val item = keys.drop(items.size / 2).next()
        Median(Left(item))
      } else {
        val iterator = keys.drop(items.size / 2 - 1)
        val item1 = iterator.next()
        val item2 = iterator.next()
        Median(Right(item1 -> item2))
      }
    }

    def getMedianAfterAddition(items: java.util.TreeMap[Item, Int],
                               oldMedian: Median,
                               newItem: Item,
                               removingItem: Item): Median = {
      oldMedian.value match {
        case Left(oldMedianVal) =>
          val isAddedLeft = items.comparator().compare(oldMedianVal, newItem) > 0
          val isRemovedLeft = items.comparator().compare(oldMedianVal, removingItem) > 0
          val isRemovedMedian = items.comparator().compare(oldMedianVal, removingItem) == 0

          if (isAddedLeft) {
            if (isRemovedLeft) {
              // median is the same
              oldMedian
            } else {
              // shift left to one
              val previous = items.lowerKey(oldMedianVal)
              Median(Left(previous))
            }
          } else {
            if (isRemovedLeft || isRemovedMedian) {
              // shift right to one
              val nextItem = items.higherKey(oldMedianVal)
              Median(Left(nextItem))
            } else {
              // median is the same
              oldMedian
            }
          }
        case Right(oldMedianValComp) =>
          val m1 = oldMedianValComp._1
          val m2 = oldMedianValComp._2

          val isAddedLeft = items.comparator().compare(m1, newItem) > 0
          val isRemovedLeft = items.comparator().compare(m1, removingItem) > 0

          if (isAddedLeft) {
            if (isRemovedLeft) {
              // median is the same
              oldMedian
            } else {
              if (removingItem == m1) {
                Median(Right(items.lowerKey(m1) -> m2))
              } else if (removingItem == m2) {
                Median(Right(items.lowerKey(m1) -> m1))
              } else {
                Median(Right(items.lowerKey(m1) -> m1))
              }
            }
          } else {
            if (isRemovedLeft) {
              Median(Right(items.higherKey(m1) -> items.higherKey(items.higherKey(m1))))
            } else {
              if (removingItem == m1) {
                Median(Right(items.higherKey(m1) -> items.higherKey(items.higherKey(m1))))
              } else if (removingItem == m2) {
                val nextM1 = items.higherKey(m1)
                Median(Right(m1 -> (if (nextM1 == m2) items.higherKey(nextM1) else nextM1)))
              } else {
                Median(Right(m1 -> items.higherKey(m1)))
              }
            }
          }
      }
    }


    val maxIndicies = mutable.HashMap.empty[Int, Int]
    val sortedWindow: java.util.TreeMap[Item, Int] = new java.util.TreeMap((o1: Item, o2: Item) => {
      val v = o1.value.compareTo(o2.value)
      if (v == 0) o1.index.compareTo(o2.index) else v
    })
    val originalOrderedWindow = mutable.ListBuffer.empty[Item]
    var n = 0
    var median = Option.empty[Median]
    for (currentValue <- values) {

      // get current item with index for duplicates
      maxIndicies += currentValue -> maxIndicies.get(currentValue).map(v => v + 1).getOrElse(0)
      val currentItem = Item(currentValue, maxIndicies(currentValue))

      originalOrderedWindow += currentItem
      sortedWindow.put(currentItem, 0)

      if (originalOrderedWindow.size < d) {
        // collect elements if they are not enough do nothing
      } else {
        // enough elements, try to figure out median size
        if (median.isEmpty) {
          median = Option(getMedian(sortedWindow))
        } else {
          if (currentValue >= (median.get.value match {
            case Left(l) => l.value * 2
            case Right(l) => l._1.value + l._2.value
          })) {
            n = n + 1
          }

          median = Option(getMedianAfterAddition(sortedWindow, median.get, currentItem, originalOrderedWindow.head))
        }
      }

      // remove element from head
      if (originalOrderedWindow.size > d) {
        sortedWindow.remove(originalOrderedWindow.head)
        originalOrderedWindow -= originalOrderedWindow.head
      }
    }

    n
  }

  def activityNotificationsCountSort(values: Seq[Int], d: Int) = {
    def getMedian(items: Array[Int], size: Int) = {
      def getElementAt(items: Array[Int], index: Int): Int = {
        var iterator = -1
        items.zipWithIndex.dropWhile(v => {
          iterator = iterator + v._1
          iterator < index
        }).head._2
      }

      if (size % 2 == 0) {
        val median1 = getElementAt(items, d / 2 - 1)
        val median2 = getElementAt(items, d / 2)
        Option(Right(median1, median2))
      } else {
        val median = getElementAt(items, d / 2)
        Option(Left(median))
      }
    }

    val countingSort = new Array[Int](201)
    val originalOrderedWindow = mutable.ListBuffer.empty[Int]
    var n = 0
    for (currentValue <- values) {
      if (originalOrderedWindow.size < d) {
        // collect elements if they are not enough do nothing
      } else {
        val median: Option[Either[Int, (Int, Int)]] = getMedian(countingSort, d)
        if (currentValue >= (median.get match {
          case Left(l) => l * 2
          case Right(l) => l._1 + l._2
        })) {
          n = n + 1
        }
      }

      originalOrderedWindow += currentValue
      countingSort(currentValue) = countingSort(currentValue) + 1

      // remove element from head
      if (originalOrderedWindow.size > d) {
        countingSort(originalOrderedWindow.head) = countingSort(originalOrderedWindow.head) - 1
        originalOrderedWindow -= originalOrderedWindow.head
      }
    }
    n
  }

  def activityNotificationsDummy(values: Seq[Int], d: Int) = {
    val originalOrderedWindow = mutable.ListBuffer.empty[Int]
    var n = 0
    for (currentValue <- values) {
      if (originalOrderedWindow.size < d) {
        // collect elements if they are not enough do nothing
      } else {
        if (d % 2 == 0) {
          val median1 = originalOrderedWindow.sorted.toList(originalOrderedWindow.size / 2 - 1)
          val median2 = originalOrderedWindow.sorted.toList(originalOrderedWindow.size / 2)

          if (currentValue >= median1 + median2) {
            n = n + 1
          }
        } else {
          val median = originalOrderedWindow.sorted.toList(originalOrderedWindow.size / 2)
          if (currentValue >= median * 2) {
            n = n + 1
          }
        }
      }

      originalOrderedWindow += currentValue

      if (originalOrderedWindow.size > d) {
        originalOrderedWindow -= originalOrderedWindow.head
      }
    }
    n
  }


}
