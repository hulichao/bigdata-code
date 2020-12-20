package com.hoult.scala.hello7

import scala.util.Random

object MatchDemo {
  def main(args: Array[String]): Unit = {
    val ch = '+'

    ch match {
      case '+' => println("加")
      case '-' => println("jian")
      case _ => println("nothing")
    }
    //字符串模式匹配

    val content = Array("hello", "spark", "flink")

    val arr = Array("hadoop", "zookeeper", "spark")
    val name = arr(Random.nextInt(arr.length))
    name match {
      case "hadoop" => println("大数据分布式存储和计算框架...")
      case "zookeeper" => println("大数据分布式协调服务框架...")
      case "spark" => println("大数据分布式内存计算框架...")
      case _ => println("我不认识你...")
    }

    //守卫式
    val character = 's'
    val num = character match {
      case '+' => 1
      case '-' => 2
      case _ if character.equals('*') => 3
      case _ => 9 //只会执行一行
      case _ => 4
    }
    println(character + " " + num)

//    typeMathc("Array(1,2)")

//    listMatch()
    tupleMatch()
  }

  //类型匹配
  def typeMathc (x: Any) = {
    x match {
      case _: String => println("字符串")
      case _: Int => println("整形")
      case _: Array[Int] => println("正星星数组")
      case _ => println("nothing")
    }
  }

  //数组模式匹配
  def arrayMatch(x: Array[Int]) = {
    x match {
      case Array(1,x,y) => println(x + ":" + y)
      case Array(1) => println("only 1 ....")
      case Array(1,_*) => println("1 开头的")
      case _ => println("nothing....")
    }

  }

  //list模式匹配
  def listMatch() = {
    val list = List(3, -1)
    //对List列表进行模式匹配，与Array类似，但是需要使用List特有的::操作符
    //构造List列表的两个基本单位是Nil和::，Nil表示为一个空列表
    //tail返回一个除了第一元素之外的其他元素的列表
    //分别匹配：带有指定个数元素的列表、带有指定元素的列表、以某元素开头的列表
    list match {
      case x :: y :: Nil => println(s"x: $x y: $y")
      case 0 :: Nil => println("only 0")
      case 1 :: _ => println("1 ...")
      case _ => println("something else")
    }
  }

  def tupleMatch() = {
    val tuple = (5, 3, 7)
    tuple match {
      case (1, x, y) => println(s"1, $x, $y")
      case (_, z, 5) => println(z)
      case _ => println("else")
    }
  }
}
