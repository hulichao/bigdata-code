package com.hoult.scala.hello5

class Person(name: String, age: Int) {
  println("fu person")
}

class Student(name: String, age: Int, stuNo: String) extends Person(name, age) {
  println("zi student")
}
object ExtendsDemo {
  def main(args: Array[String]): Unit = {
    val student = new Student("jon", 19, "1000")
  }
}
