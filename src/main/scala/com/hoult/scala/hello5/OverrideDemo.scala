package com.hoult.scala.hello5

abstract class Programmer(name: String, age: Int) {
  def coding() = {
    println("im coding...")
  }
}

class ScalaProgrammer(name: String, age: Int, workNo: String) extends Programmer(name, age) {
  override def coding(): Unit = {
    super.coding()
    println(s"im scala coding")
  }
}
object OverrideDemo {
  def main(args: Array[String]): Unit = {
    val scala = new ScalaProgrammer("hoult", 10, "1024")
    scala.coding()
  }
}
