package com.hoult.hello

import com.hoult.hello.test.Temp


package object test {
  val defaultValue = "zhangsan"
}
package test {
  class Temp {
    def main(args: Array[String]): Unit = {
      println(defaultValue)
    }
  }
}

object packageTest {
  def main(args: Array[String]): Unit = {
    val temp = new Temp()
    println(temp)

  }
}
