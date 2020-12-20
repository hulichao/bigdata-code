package com.hoult.scala.hello9

object MapDemo {
  def main(args: Array[String]): Unit = {
    // 可使用两种方式定义Map
    // Map缺省是不可变的，值不能更改
    val a = Map("a" -> 1, "b" -> 2, "c" -> 3)
//    val a = Map(("a", 1),("b", 2),("c", 3))
    a.keys
    a.values
    // 获取Map中的值：
    a("a")
    // 访问不存在的key时，会抛出异常。Java.util.NoSuchElementException: key not found: x
    a("x")
    // 使用get方法，返回一个Option对象，要么是Some（键对应的值），要么是None
    a.get("a")
    // 获取键对应的值，如果键不存在返回给定的值（这里是0）
    a.getOrElse("a", 0)
    // 更新Map中的值(要使用可变的Map)
    val b = scala.collection.mutable.Map("a" -> 1, "b" -> 2, "c" -> 3)
//    b("a") = 2
//    // 增加了一个新元素
//    b("d") = 4
//    // 用 + 添加新的元素；用 – 删除元素
//    b += ("e" -> 1, "f" -> 2)
//    b -= "a"
//    // 增加元素
//    val b = Map("a" -> 1, "b" -> 2, "c" -> 3)
//    val c = b + ("a" -> 10, "b" -> 20)
//    // 通过包含键值对的二元组创建Map集合
//    val a = Map(("a", 1),("b", 2),("c", 3))
//    // 逐个访问 value
//    for(v <- a.values) println(v)
//    // key 和 value 做了交换
//    val b = for((k,v) <- a) yield (v,k)
//    // 下面才是具有scala风格的写法，推荐
//    a.map(x=>(x._2, x._1))
//    // 拉链操作创建Map
//    val a = Array(1,2,3)
//    val b = Array("a","b","c")
//    //c: Array[(Int, String)]
//    val c = a.zip(b)
//    //c: scala.collection.immutable.Map[Int,String]
//    val c = a.zip(b).toMap
  }
}
