package com.demo

object Hello {
  def main(args: Array[String]): Unit = {
    println("hello, scala")

    var v1 : Int = 10
    var v2 : Double = 20
    //java 版本输出
    println(v2 + v1)
    //c语言版本输出
    printf("%s : %f\n", v1, v2)
    //php版本输出
    println(s"php 版本输出 v1=$v1 v2 = $v2")

    val `sssssssssfsdafds_+asdfd` = "ddd"
    println(`sssssssssfsdafds_+asdfd`)

    def f1(): Int = {100}

    println(f1)

    var f2 = f1()
    var f3 = f1 _
    println(f2)
    println(f3())

    def f4= "venassa"
    println(f4)
  }

}
