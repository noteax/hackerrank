package algorithms.sorting

object LilysHomework {

  def main(args: Array[String]): Unit = {
    scala.io.StdIn.readInt
    val items = scala.io.StdIn.readLine.split(" ").map(_.toInt)
    println(getSwapCount(items))
  }


  def getSwapCount(items: Seq[Int]): Int = {
    def getSwapCountInternal(itemsInternal: Seq[Int]): Int = {
      import scala.collection.mutable

      val mutableItems = mutable.ArrayBuffer.empty[Int] ++= itemsInternal
      val sortedItems = itemsInternal.sorted
      val itemIndexes = mutable.Map.empty[Int, Int] ++= itemsInternal.zipWithIndex.toMap
      var count = 0
      for (i <- mutableItems.indices) {
        val sorted = sortedItems(i)
        val origin = mutableItems(i)

        if (sorted != origin) {
          count = count + 1

          val swapItemIndex = itemIndexes(sorted)
          mutableItems(swapItemIndex) = origin
          mutableItems(i) = sorted
          itemIndexes(origin) = swapItemIndex
          itemIndexes(sorted) = i
        }
      }
      count
    }

    Math.min(getSwapCountInternal(items), getSwapCountInternal(items.reverse))
  }

}