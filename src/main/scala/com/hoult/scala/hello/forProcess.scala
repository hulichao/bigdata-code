package com.hoult.hello

import scala.util.control.Breaks._

object forProcess {
  def main(args: Array[String]): Unit = {
    /*    for ( int i = 0; i < 10; i++ ) {
      println(i)
    }*/

    val result = for (i <- -1 until 3; j <- 1 to 3; k <- Range(1, 10, 2)) yield (i, j, k)
    //    println(result)
    breakable {
      for (i <- -1 to 10) {
        if (i == 5) {
          break()
        }

        println(s"i=$i")
      }
    }


    println("JIESHU")

    //    test("aa","ccc")
    //    test()
    for (i <- 1 to 10) (println(i));
  }
  def test(name: String*) = {
    println(name)
  }




}
