package com.hoult.hello

object SeqTest {
  def main(args: Array[String]): Unit = {
    // 序列seq 不可变
    val ints: List[Int] = List(1, 2, 3, 4)
//    println(ints.length)
    val ints1: List[Int] = ints :+ 1
    val ints2: List[Int] = 10 :: ints ++ ints
    println(ints2)
    println(ints2)
    println(ints1)
  }

}
