package com.hoult.scala.hello1

/**
 *  插值器，s f raw
 */
object InterpolatorDemo {
  def main(args: Array[String]): Unit = {
    //s插值器，可以通过$获取变量和表达式
    val subject = "Scala"
    val message = s"hello,$subject"
    println(message)

    val array = (1 to 10).toArray
    val str = s"array.length=${array.length}"
    println(str)

    println(s"${10 * 9}")


    //f插值器
    val year = 2020
    val month = 8
    val day = 8

    println(s"$year-$month-$day")

    println(f"$year-$month%02d-$day%02d")

    //raw 插值器
    println("a\n\t\\c")
    println(raw"a\n\t\\c")

    println(
      """a\n\b\t
        |
        |d
        |""".stripMargin)
  }

}
