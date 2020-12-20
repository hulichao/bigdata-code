package com.hoult.scala.hello10

class Num {}
class RichNum(num: Num) {
  def rich() = {
    println("hello implicit")
  }
}

object ImplicitDemo {

  // 定义一个名称为num2RichNum的隐式函数
  implicit def num2RichNum(num: Num): RichNum = {
    new RichNum(num)
  }
  def main(args: Array[String]): Unit = {
    val num = new Num
    // num对象并没有rich方法，编译器会查找当前范围内是否有可转换的函数
    // 如果没有则编译失败，如果有则会调用。
    num.rich()
  }
}
