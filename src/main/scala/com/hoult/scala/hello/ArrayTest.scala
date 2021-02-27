package com.hoult.hello

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

object ArrayTest {
  def main(args: Array[String]): Unit = {
    val ints: Array[Int] = Array(1, 2, 3, 4)
    ints.update(2, 10)
    //    ints.foreach(println)
    val str: String = ints.mkString(",")
//    println(str)

    val ints1: Array[Int] = 3 +: ints :+ 5
//    ints1.foreach(println)
//    print(ints1.mkString("|"))

    //可变数组
    val ints2: ArrayBuffer[Int] = ArrayBuffer(5, 6, 7, 8)
    ints2(0) = 10
    ints2.update(1, 100)
//    ints2 :+ 10000
    ints2 += 999
    ints2.insert(1, 1000)
    print(ints2.mkString("、"))
    val buffer: mutable.Buffer[Int] = ints.toBuffer

  }
}
