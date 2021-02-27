package com.hoult.util

object DerivedFunctions {
  def booleanToString(a: Boolean, b: String, c: String): String = {
    return if (a) b else c
  }

  def main(args: Array[String]): Unit = {
//    println(booleanToString(false, "1", "2"))
    val ffff: (Int) => Int = fff()
    println(ffff(1))
  }

  def ff(i: Int): Int = {
    return i + 10
  }

  def fff() = {
    ff _
  }
}
