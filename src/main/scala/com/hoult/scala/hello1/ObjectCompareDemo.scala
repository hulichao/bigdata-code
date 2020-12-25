package com.hoult.scala.hello1

object ObjectCompareDemo {
  def main(args: Array[String]): Unit = {
    println(1 == 1)
    println(1 == 2)
    println(1 != 2)

    val flag = List(1, 2, 3) == List(4,5,6)

    println(flag)

    println(Array(1,2,3) == Array(1,2,3))

    //比较不同类型的对象
    println(2 == 2.0)
    println(List(1,2,3) == "Scala")
  }

}
