package com.demo

object ScalaExceptionDemo {
  @throws(classOf[Exception])
  def main(args: Array[String]): Unit = {
    try {
      var r = 1 / 0
    } catch {
      case ex:ArithmeticException => {
        println("补获除零的异常")
      }
    } finally {
      println("finally execute....")
    }

    println("ok,,,go on..")
  }
}
