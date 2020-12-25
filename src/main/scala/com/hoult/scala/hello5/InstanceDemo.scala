package com.hoult.scala.hello5

class Person2
class Student2 extends Person2

object InstanceDemo {
  def main(args: Array[String]): Unit = {

    val p: Person2 = new Student2
    var s: Student2 = null

    //null 会返回false
    println(s.isInstanceOf[Person2])

    if (p.isInstanceOf[Student2])
      s = p.asInstanceOf[Student2]

    println(p.getClass == classOf[Person2])
    println(s.getClass == classOf[Student2])


    println("$$$$$$$$$$$$$$$$$$$")

    p match {
      case s: Person2 => println("studnet2 对象")
      case _ => println("nothing")
    }
  }
}
