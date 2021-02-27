package com.demo

object Exercise01 {
  def main(args: Array[String]): Unit = {
//    println(fbn(10))
    println("第一天的桃子" + tao(1))
  }

  def fbn(n : Int) : Int = {
    if (n == 1 || n ==2 ) {
      1
    } else
      fbn(n - 1) + fbn(n - 2)
  }

  def tao(n : Int) : Int = {
    if (n == 10) {
      1
    } else {
      (tao(n+1)+1) * 2
    }
  }

  def dai(add: String = "localhost", port : Int = 123) {
    println(s"port:" + port)
  }
}
