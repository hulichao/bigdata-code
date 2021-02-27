package com.hoult.hello

object List2Test {
  def main(args: Array[String]): Unit = {
    val list1: List[Int] = List(1, 2, 3, 4)
    val list2: List[Int] = List(4, 5, 6, 7)
    //zip 拉链
    val tuples: List[(Int, Int)] = list1.zip(list2)
    println(tuples)
    //集合并集
    println(list1.union(list2))
    //交集
    println(list1.intersect(list2))
    //差集
    println(list1.diff(list2))

  }

}
