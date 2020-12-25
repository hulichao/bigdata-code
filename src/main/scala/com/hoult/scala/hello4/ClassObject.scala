package com.hoult.scala.hello4

class ClassObject {
  private var name = "hoult"

  def printInfo(): Unit = {
    println(ClassObject.num)
    println("hello class")
  }
}
object ClassObject {
  private val num = 10

  def main(args: Array[String]): Unit = {
    val classObject = new ClassObject
    println(classObject.name)
    println("hello object")
    classObject.printInfo()
  }
}
