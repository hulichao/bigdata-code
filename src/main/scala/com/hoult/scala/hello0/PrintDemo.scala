package com.hoult.scala.hello0

object PrintDemo {
  def main(args: Array[String]): Unit = {
    Console println 10
    val l = List(1, 2, 3)
    println(l)

    val m = Map(1 -> "2", 2 -> "2", (3, "dd"))
    println(m)

    println(null == 1)
    println(Set(1,2,3) == Set(2,3,1))
    println(null == null)
    println("1" + "2" == "12")
//    require(1 == 2)
    println(new Rational(1, 2))
  }


}
class Rational(n: Int, d: Int) {
  require(d != 0)
  override def toString = n + "/" + d
  def add(that: Rational): Unit = {
    new Rational(d, n)
  }
}
