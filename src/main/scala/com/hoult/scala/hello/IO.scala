package com.hoult.hello

import scala.io.StdIn

object IO {
  def main(args: Array[String]): Unit = {
    val line: String = StdIn.readLine()
    println(line)
  }
}
