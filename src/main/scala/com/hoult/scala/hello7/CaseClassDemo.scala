package com.hoult.scala.hello7

class Amount
//定义样例类Dollar，继承Amount父类
case class Dollar(value: Double) extends Amount
//定义样例类Currency，继承Amount父类
case class Currency(value: Double, unit: String) extends Amount
//定义样例对象Nothing，继承Amount父类
case object Nothing extends Amount
object CaseClassDemo {
  def main(args: Array[String]): Unit = {
    judgeIdentity(Dollar(10.0))
    judgeIdentity(Currency(20.2,"100"))
    judgeIdentity(Nothing)
  }
  //自定义方法，模式匹配判断amt类型
  def judgeIdentity(amt: Amount): Unit = {
    amt match {
      case Dollar(value) => println(s"$value")
      case Currency(value, unit) => println(s"Oh noes,I got $unit")
      case _: Currency => println(s"Oh noes,I go")
      case Nothing => println("Oh,GOD!")
    }
  }
}
