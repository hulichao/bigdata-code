package com.hoult.hello

object TupleTest {
  def main(args: Array[String]): Unit = {
    val tuple: (String, String, String) = ("zhangsna", "1111", "name")
    println(tuple._1)
    println(Tuple1("1"))
    for (elem <- tuple.productIterator) {
      println(elem)
    }

    var tupleMap: Map[Int, Int] = Map((1, 2), (2, 4))
//    tupleMap.foreach(println)
    tupleMap += (4 -> 5)
    tupleMap.foreach(println)

    Console.err.println("xxx")
    System.err.println("ddd")

    var a = 0;
    val b: Int = a.max(5)
    println(b)

  }

}
