package com.demo

object VarDemo {
  def main(args: Array[String]): Unit = {
    var age : Int = 10
    var sal : Double = 10.9
    var a = sal.toString
//    println(s"$sal  $age :::: $a")
//    println(a.toCharArray)
    var arr = new Array[Int](10)
//    for (item <- arr) {
//      println("item = " + item)
//    }
//    var c = ()
//    println(c)
//    sayHello

    var b : Byte = 3
    var s = "12.5"
    var ++ = "hello, world"
    var `true` = "dsfaf "
    var Double = "fafd"
    println(Double)
//    println(`true`)
//    println(++)
//    println(s)
//    println(b)

    var num = if (5 > 4) 5 else 4
    println(num)
  }

  def sayHello() : Unit = {
    println("say hello")
  }
}
