package com.hoult.scala.hello

object IfDemo {
  def main(args: Array[String]): Unit = {
    val num = 20
    //在scala中， if else 最后一行作为返回值

    val a = if (num > 20) "jack" else "tom"

    //多分枝
    val b = if (num > 20)
      0
    else if (num == 20)
      1
    else
      -1

    println(a)
    println(b)

    //如果缺省了else ，那么默认else的只是Unit 类型即（）
    val c = if (num != 20) "boy"

    println(c)
  }

}
