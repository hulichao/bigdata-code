package com.mylazy

object LazyDemo1 {
  def main(args: Array[String]): Unit = {
     lazy val res = sum(10, 20)
    println(s"....... ")
  }

  def sum(i: Int, i1: Int): Unit = {
    println(i + i1)
    i + i1
  }
}
