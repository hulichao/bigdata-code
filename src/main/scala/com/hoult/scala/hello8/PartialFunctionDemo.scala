package com.hoult.scala.hello8

object PartialFunctionDemo {
  def main(args: Array[String]): Unit = {
    // PartialFunction[Any, Int]: 偏函数接收的数据类型是Any，返回类型为Int
    val partialFun = new PartialFunction[Any, Int] {
      // 如果返回true，就调用 apply 构建实例对象；如果返回false，过滤String数据
      override def isDefinedAt(x: Any): Boolean = {
        println(s"x = $x")
        x.isInstanceOf[Int]
      }
      // apply构造器，对传入值+1，并返回
      override def apply(v1: Any): Int = {
        println(s"v1 = $v1")
        v1.asInstanceOf[Int] + 1
      }
    }
    val lst = List(10, "hadoop", 20, "hive", 30, "flume", 40, "sqoop", "50")
    // 过滤字符串，对整型+1
    // collect通过执行一个并行计算（偏函数），得到一个新的数组对象
    lst.collect(partialFun).foreach(println)
    // 实际不用上面那么麻烦
    lst.collect{case x: Int => x+1}.foreach(println)
  }


}
