package com.hoult.scala.hello4

object Object {
  println("hello 单例！")

  def printInfo = {
    println("hell scala object")
  }
}

//main 只能定义在object 中
object ObjectDemo {
  def main(args: Array[String]): Unit = {
//    val o1 = Object
//    val o2 = Object

    Object.printInfo
    Object.printInfo
  }
}
