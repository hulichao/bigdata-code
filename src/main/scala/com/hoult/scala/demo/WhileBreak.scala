package com.demo

import scala.beans.BeanProperty

object WhileBreak {
  def main(args: Array[String]): Unit = {

  }
}

class Dog {
  @BeanProperty
  var name = "Jack"
  var lover = new Fish
  var age: Int = _
}

class Fish {
}

class Test {
}
