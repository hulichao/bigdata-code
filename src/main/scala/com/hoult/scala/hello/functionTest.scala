package com.hoult.hello

object functionTest {
  def main(args: Array[String]): Unit = {
//    test(name = "zhangsan", age = 90)
    def f = {
      println("function")
    }

    def f0() = {
      f _
    }

//    f0()

    //hanshu 克里hua
    def f3(i: Int)(j: Int) = {
      i * j
    }

//    println(f3(1)(2))
    //函数内部传递函数
    def f4(f: () => (Int)): Int = {
      f() + 10
    }

    def f5() = {
      20
    }

//    println(f4(f5))

    //使用匿名函数
    def f6(f: () => Unit) = {
      f()
    }

    f6(() => println("hello f7, 匿名"))
  }

  def test(name1: String = "lisi", name: String, age: Int = 10): Unit = {
    println(s"name1=${name1},name=${name},age=${age}")
  }


}
