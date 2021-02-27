package com.hoult.hello

object Hi {
  def main(args: Array[String]): Unit = {
    println("Hi")
    //printf
    val a = 1 //四种输出语法
    printf("a=%d", a)
    println(s"a=${a}")
    println(f"a=${a}.2f")
    println (raw"a=${a}\n")
  }
}
