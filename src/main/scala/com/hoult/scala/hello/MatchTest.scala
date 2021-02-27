package com.hoult.hello

object MatchTest {
  def main(args: Array[String]): Unit = {
    val oper = '-'
    val n1 = 20
    val n2 =10
    var res = 0

    //下划线的作用：模式匹配的其他场合，系统给变量赋初值，导包时候的类似java *, 给类起别名，隐藏类，代替方法的参数，可以让函数不执行
    oper match {
      case '+' => res = n1 + n2
      case '-' => res = n1 - n2
      case '*' => res = n1 * n2
      case '/' => res = n1 / n2
      case _ if oper.toString.contains("#") => println("##")
      case _ => println("error") //条件守卫
    }

    println("res=" + res)
  }
}
