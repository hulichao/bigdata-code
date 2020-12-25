package com.hoult.scala.hello10

class SpecialPerson(var name: String)
class Older(var name: String)
class Student(var name: String)
class Worker(var name: String)

object ImplicitDemoTwo {
  def buySpecialTickWindow(person: SpecialPerson): Unit = {
    if (person != null) {
      println(person.name + "购买了一张特殊票！")
    } else {
      println("你不是特殊人群，不能在此买票！")
    }
  }
  //隐式转换函数
  //注意：any参数的类型是Any
  implicit def any2SpecialPerson(any: Any): SpecialPerson = {
    any match {
      case any: Older => new SpecialPerson(any.asInstanceOf[Older].name)
      case any: Student => new SpecialPerson(any.asInstanceOf[Student].name)
      case _ => null
    }

  }
  def main(args: Array[String]): Unit = {
    val stu = new Student("jacky")
    val older = new Older("old man")
    val worker = new Worker("tom")
    ImplicitDemoTwo.buySpecialTickWindow(stu)
    ImplicitDemoTwo.buySpecialTickWindow(older)
    ImplicitDemoTwo.buySpecialTickWindow(worker)
  }
}
