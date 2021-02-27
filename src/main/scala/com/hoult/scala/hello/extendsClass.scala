package com.hoult.hello

object extendsClass {
  def main(args: Array[String]): Unit = {
    new C
  }
}

trait A {
  println("is A....")
}

class B {
  println("is B....")
}

class C extends B with A {
  println("is C...")
}
