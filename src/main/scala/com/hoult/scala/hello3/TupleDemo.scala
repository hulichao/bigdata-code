package com.hoult.scala.hello3

/**
 * tuple 元组可以存放不同数组类型的元素
 * 元组的索引从1 开始，不是从0开始
 */
object TupleDemo {
  def main(args: Array[String]): Unit = {
    val tuple = Tuple5(1,2.5,"spark", "a", true)
    val tuple2 = Tuple4(1,1.2, "scala", true)
    println(tuple._3)

    val (t1,t2,t3,t4),t5 = tuple2

    println(t1)
    println(t2)
    println(t3)
    println(t4)
    println(t5)

    val (t7,_,t8,_), t9 = tuple2

    println(s"$t7, $t8, $t9")

    //foreach 也可以
    for (t <- tuple.productIterator) {
      println(t)
    }
  }
}
