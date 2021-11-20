package com.hoult.scala.hello13

/**
 * scala内部类和外部类
 */
class Outer {
  class Inner {
    private def f() = println("f()")
    class InnerTest() {
      f()
    }
  }

//  new Inner().f() // 错误，无法访问f
}
