package com.hoult.scala.hello6


class Person2 { println("Person's constructor!") }
trait Logger { println("Logger's constructor!") }
trait MyLogger extends Logger { println("MyLogger's constructor!") }
trait TimeLogger extends Logger { println("TimeLogger's constructor!") }
//类既继承了类又继承了特质，要先写父类
class Student2 extends Person2 with MyLogger with TimeLogger {
  println("Student's constructor!")
}
object TraitDemoThree {
  def main(args: Array[String]): Unit = {
    new Student2
  }
}
