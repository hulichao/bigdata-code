package com.hoult.scala.hello4

class Student(name: String, age: Int) {
  private var gender: String = _

  def sayHi(): Unit = {
    println(s"hello, $name, $age, $gender")
  }
}

//apply 方法 需要定义在对象中，通常用于
object Student {

  def apply(name: String, age: Int) = new Student(name, 100)

  def main(args: Array[String]): Unit = {
    Student("hu", 10).sayHi()
  }
}
