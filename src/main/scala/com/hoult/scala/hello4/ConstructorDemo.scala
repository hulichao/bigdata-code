package com.hoult.scala.hello4

//主构造器与类名交织在一起，类名后面的参数就是主构造器的参数
//主构造器直接在类中，其代码不包含在任何方法中
class Animal(name: String, age: Int) {
  //下面的都是在主构造器中
  println(name)
  println(age)
  println("==================")

  var gender: String = ""

  //每个辅助构造器必须以主构造器或其他辅助构造器的调用作为第一句代码
  def this(name:String, age: Int, gender: String) {
    this(name, age)
    this.gender = gender
  }

  var color: String = ""

  def this(name: String, age: Int, gender: String, color: String) {
    this(name, age, color)
    this.color = color
  }
}

object ConstructorDemo {
  def main(args: Array[String]): Unit = {
    val gougou: Animal = new Animal("gougou", 4)

    val gougou2 = new Animal("wangci ", 3, "m")
    val gougou3 = new Animal("wangci ", 3, "m", "yellow")
  }

  //main 只能存在于单例对象中
}
