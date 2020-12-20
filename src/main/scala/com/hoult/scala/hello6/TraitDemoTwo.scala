package com.hoult.scala.hello6

trait People {
  val name: String
  val age: Int = 30
  def eat(message: String): Unit = {
    println(s"$message")
  }
}

trait Worker {
  //这个trait也定义了age字段
  val age = 25
  def work: Unit = {
    println("Working......")
  }
}

// Student类继承了Worker、Person这两个特质，需要使用extends、with这两个关键字
class Student extends Worker with People{
  //重写抽象字段，override可以省略
   val name: String = "张三"
  //继承的两个trait中都有age字段，此时需要重写age字段，override不能省略
  override val age = 20
}

object TraitDemoTwo {

}
