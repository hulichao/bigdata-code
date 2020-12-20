package com.hoult.scala.hello4


class Dog {
  private var _leg = 0
  def leg: Int = _leg
  def leg_=(newLag: Int) = {
    _leg = newLag
  }

  def get() = {
    _leg
  }
}
object GetterAndSettre {
  def main(args: Array[String]): Unit = {
    val dog = new Dog
    dog.leg_=(4) //等同于 dog.leg = 4 ,都是修改了_leg的值
    println(dog.get())
    dog.leg = 5
    println(dog.get())

  }

}
