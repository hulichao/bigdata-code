package com.hoult.scala.demo

object Test {
  def main(args: Array[String]): Unit = {
    val list: List[Int] = List(1, 5, 8, 3, 7)

    test("xx")("yy")
  }

  def test(a: String)(b: String) = {
    println(a + ":" + b)
  }
}
