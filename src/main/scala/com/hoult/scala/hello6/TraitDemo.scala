package com.hoult.scala.hello6

trait HelloTrait {
  def sayHello
}


trait MakeFriendTraid {
  def makeFriend
}

//重写trait 或者 abstract 的方法可以i省略overrider
// 第一个用extends 其他用with
class Person(name: String) extends HelloTrait with MakeFriendTraid {
  override def sayHello: Unit = println(s"say $name")

  override def makeFriend: Unit = println(s"say friend $name")
}
object TraitDemo {
  def main(args: Array[String]): Unit = {
    val jack = new Person("jack")
    jack.sayHello
    jack.makeFriend
  }
}
