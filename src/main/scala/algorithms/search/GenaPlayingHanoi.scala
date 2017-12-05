package algorithms.search

import scala.collection.mutable


object GenaPlayingHanoi extends App {
  val n = scala.io.StdIn.readLine.toInt
  val items = scala.io.StdIn.readLine.split(' ').map(_.toInt)
    .zipWithIndex
    .toList
    .groupBy(v => v._1)
    .mapValues(values => values.map(v => v._2))

  println(minMovesToBuildOnI1(items))

  def minMovesToBuildOnI1(initialState: Map[Int, Seq[Int]]): Int = {
    // doing bfs on different state changes to get min number
    // try to do all possible moves
    def applyMove(move: (Int, Int), fromState: Map[Int, Seq[Int]]): Option[Map[Int, Seq[Int]]] = {
      val fromTower = fromState.get(move._1)
      val toTower = fromState.get(move._2)

      val item = fromTower.flatMap(_.headOption)
      val ontoItem = toTower.flatMap(_.headOption)

      item.flatMap(itemVal => {
        if (itemVal < ontoItem.getOrElse(Int.MaxValue)) {
          Some(fromState.map(v => {
            if (v._1 == move._1) {
              (v._1, v._2.drop(1))
            } else {
              if (v._1 == move._2) {
                (v._1, itemVal :: v._2.toList)
              } else {
                (v._1, v._2)
              }
            }
          }))
        } else Option.empty
      })
    }


    def minMoves(count: Int,
                 allStates: mutable.HashSet[Map[Int, Seq[Int]]],
                 currentLayerOfStates: List[Map[Int, Seq[Int]]],
                 currentFilledLayerOnFirst: Int,
                 depth: Int
                ): Int = {
      // complete recursion here
      val allMoves = (1 to 4)
        .flatMap(v1 => (1 to 4).filter(v2 => v2 != v1).map(v2 => v1 -> v2))
      val newStates = currentLayerOfStates
        .flatMap(state => allMoves
          .map(move => applyMove(move, state))
          .filter(newState => newState.isDefined)
          .map(newState => newState.get)
          .filter(newState => !allStates.contains(newState))
          .toList
        )

      if (newStates.exists(newState => newState(2).isEmpty && newState(3).isEmpty && newState(4).isEmpty)) {
        depth + 1
      } else {
          minMoves(count, allStates ++ newStates, newStates, currentFilledLayerOnFirst, depth + 1)
      }
    }

    val filledState = Map(
      1 -> initialState.getOrElse(1, List.empty),
      2 -> initialState.getOrElse(2, List.empty),
      3 -> initialState.getOrElse(3, List.empty),
      4 -> initialState.getOrElse(4, List.empty))

    // do recursively and breadth first
    minMoves(filledState.values.flatten.size, mutable.HashSet(), List(filledState), 0, 0)
  }
}
