package com.hoult.scala.hello2

object LazyDemo {
  def main(args: Array[String]): Unit = {
    lazy val file1 = scala.io.Source.fromFile("src/test.scala")
    val map=Map(("a", a),("b", b))
  }

  def a = {
    println(1)
  }

  def b = {
    println(2)
  }

}
