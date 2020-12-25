package com.hoult.scala.hello1

/**
 * {} 表达式，可以包含一系列表达式，块中最后一个表达式的值就是整个块的值
 */
object BlockDemo {

  def main(args: Array[String]): Unit = {
    val x1 = 1
    val y1 = 2
    val x2 = 3
    val y2 = 4

    val distance = {
      val dx = x2-x1
      val dy = y2 - y1
      math.sqrt(dx*dx + dy*dy)
    }

    println(distance)
  }

  //赋值语句的值是Unit类型的
  var y = 0
  val x = y = 1
  println(x)
}
