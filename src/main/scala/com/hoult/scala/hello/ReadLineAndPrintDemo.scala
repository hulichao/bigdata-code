package com.hoult.scala.hello

object ReadLineAndPrintDemo {
  def main(args: Array[String]): Unit = {
    println("请输入您的姓名： ")
    val name = scala.io.StdIn.readLine()
    println(name)

    println("请输入您的年龄：")
    val age = scala.io.StdIn.readInt()
    println(s"您的姓名是: $name，您的年龄是：$age")
    printf("您的姓名是：%s,年龄：%d",name, age)


  }
}
