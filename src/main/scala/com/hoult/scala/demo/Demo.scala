package com.hoult.demo

import scala.io.StdIn

object Demo {
  def main(args: Array[String]): Unit = {
    var a = StdIn.readLine()
    println(a)
  }

}

object Cat extends AAA {
  //方法
  def sayHi() : Unit = {
    println("wang wnag")
  }

  def main(args: Array[String]): Unit = {
    sayHi()
    sayHello
  }
}


trait AAA {
  def sayHello: Unit = {
    println("AAA say Hello")
  }
}
