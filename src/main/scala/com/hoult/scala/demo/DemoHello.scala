package com.demo

object DemoHello {
  def main(args: Array[String]): Unit = {
//    for (i <- -10 to -3) {
//      println(i)
//    }

    var list = List("hello", "10,30", "tom")
//    for (elem <- list) {
//      println(list)
//    }

//    println(s"from 1 until 3")
//    for (i <- 1 until 3) {
//      println(i)
//    }
//
//    println(s"1 to 3")
//    for (i <- 1 to 30 if i % 3 == 2 ;  j = i) {
//      println(s"i=$i j=$j")
//    }

    var res = for (i <- 1 to 3) yield i;
    println(res)
  }
}

