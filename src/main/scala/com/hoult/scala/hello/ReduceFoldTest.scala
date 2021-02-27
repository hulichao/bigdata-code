package com.hoult.hello

import scala.collection.mutable

object ReduceFoldTest {
  def main(args: Array[String]): Unit = {
    val list: List[Int] = List(1, 2, 3, 4)
    println(list.reduce(_ + _))


    val map1: mutable.Map[String, Int] = mutable.Map(("a", 1), ("b", 2), ("c", 2))
    val map2: mutable.Map[String, Int] = mutable.Map(("a", 3), ("c", 2), ("d", 1))

    val stringToInt: mutable.Map[String, Int] = map1.foldLeft(map2)((map, t) => {
      val v: Int = map.getOrElse(t._1, 0) + t._2
      map(t._1) = v
      map
    })
//    println(stringToInt)

    val ints: List[Int] = List(3, 5, 6, 1)
    val result: Int = ints.reduceLeft((a, b) => if (a > b) a else b)
    println(result)



  }
}
