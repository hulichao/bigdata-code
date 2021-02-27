package com.hoult.hello

object Process {
  def main(args: Array[String]): Unit = {
    val flag = false
    if (flag) {
      println("true")
    } else {
      println("false")
    }

    //

    println(if (flag) 1 else 0)

    var str = if (flag) {
      "aa"
    } else "1"
    val value: String = str

    println(value)
  }
}
