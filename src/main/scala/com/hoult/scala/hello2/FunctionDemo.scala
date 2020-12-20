package com.hoult.scala.hello2

object FunctionDemo {
  def main(args: Array[String]): Unit = {
    println("计算阶乘：" + factorial(4))
    println("计算斐波那契：" + fibonaci(6))
    println("参数默认值：" + add(6))
    println("变长参数：" + addSum(8, 6, 10))
    //使用parameter: _* 的形式，告诉编译器，这个参数被当成参数序列来处理
    //变长参数只能出现在参数列表的尾部，并且只能有一个
    //在spark的源码中有大量的变长参数
    println("变长参数：" + addSum(1 to 10: _*))
  }

  //通过递归的函数来计算阶乘
  def factorial(num: Int): Int = {
    if (num == 1)
      1
    else
      num * factorial(num - 1)
  }

  //通过递归函数来实现斐波那契
  def fibonaci(n: Int): Long = {
    if (n == 1 || n == 2)
      1
    else
      fibonaci(n - 1) + fibonaci(n - 2)
  }

  //默认参数
  def add(x: Int = 10, y: Int = 20): Int = {
    x + y
  }

  //变长参数：参数类型右边加上*号
  def addSum(nums: Int*) = {
    nums.sum
  }

}
