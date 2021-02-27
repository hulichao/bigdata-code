package com.hoult.demo

object VariableUse {
  var money = 1000
  //吃一顿消费50
  def eat():Unit={
    money=money-50;
  }
  //余额
  def balance():Int={
    eat()
    money
  }
  //打印余额,传值
  def printMoney(x:  Int):Unit={
    for (a <- 1 to 5 ){
      println(f"你的余额现在为:$x")
    }
  }

  //打印余额,传名
  def printMoney1(x: => Int):Unit={
    for (a <- 1 to 5 ){
      println(f"你的余额现在为:$x")
    }
  }

  def main(args: Array[String]): Unit = {
//    printMoney(balance())
//    printMoney1(balance())
//    println(test())
    f11()
  }

  def test(): Nothing = {
    throw new Exception("不对")
  }

  @throws(classOf[NumberFormatException])
  def f11()  = {
    "abc".toInt
  }


}
