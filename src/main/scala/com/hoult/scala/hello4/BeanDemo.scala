package com.hoult.scala.hello4

import scala.beans.BeanProperty

class Teacher {
  @BeanProperty
  var name: String = _
}
class BeanDemo {
  def main(args: Array[String]): Unit = {
    val teacher = new Teacher
    teacher.name = ("xx")
    teacher.setName("xx")
    teacher.getName
    teacher.name
  }
}
