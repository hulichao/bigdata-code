package com.hoult.scala.hello10

object Doubly {
  //在print函数中定义一个隐式参数fmt
  def print(num: Double)(implicit fmt: String): Unit = {
    println(fmt format (num))
  }
  def main(args: Array[String]): Unit = {
    //此时调用print函数需要为第二个隐式参数赋值
    print(3.12)("%.1f")
    //定义一个隐式变量
    implicit val printFmt="%.3f"
    //当调用print函数时没有给第二个隐式参数赋值，
    //那么Scala会在当前作用域内寻找可见的val或var定义的隐式变量，一旦找到就会应用
    print(3.12)
  }
}
