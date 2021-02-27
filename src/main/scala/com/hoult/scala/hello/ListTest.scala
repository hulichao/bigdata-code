package com.hoult.hello

object ListTest {
  def main(args: Array[String]): Unit = {
//    val list: List[Int] = List(7, 2, 3, 5, 5)
//
//    list.sum
//    //乘积
//    list.product
//    println(list.reverse)
////    list.reduce()
////    list.filter()
////    list.map()
////    list.productIterator
////    list.iterator
////    list.groupBy()
//    val mapList: Map[Int, List[Int]] = list.groupBy(x => x % 2)
//    mapList.foreach(t => {
//      println(t._1 + "=" + t._2)})
////    list.sortBy()
//    val list0: List[String] = List("11", "23", "14", "22")
//    println(list0.sortBy(x => x.substring(1)))
//
////    println(list.sortBy(x => x))
////    list.sortWith()
//    println(list.sortWith((x, y) => x > y))
//    println(list.sortBy(x => x))
//    println(list.map((_, 1)))
//
//    val intToTuples: Map[Int, List[(Int, Int)]] = list.map((_, 1)).groupBy(x => x._1)
//    println(intToTuples.map(t => (t._1, t._2.size)).mkString(","))

    //wordcount 取前三名
    val list: List[String] = List("Hello", "Scala", "World", "Spark", "Hbase", "Hadoop", "Kafka", "Scala", "World", "Hello")
//    println(list.groupBy(x => x))
    //1.将相同的单词放到一起
    val wordToList: Map[String, List[String]] = list.groupBy(word => word)
    //2. 将数据结构转为(hello, size)
    val wordToCount: Map[String, Int] = wordToList.map(t => (t._1, t._2.size))
    //3 将数据降序
    val tuples: List[(String, Int)] = wordToCount.toList.sortWith((l, r) => l._2 > r._2).take(3)
    //4. 取前三个
    println(tuples)

    val list1: List[String] = List("hello boy", "hello scala", "hello spark", "hello hadoop")
    val tuples1: List[(String, Int)] = list1.flatMap( x => x.split("\\s+")).groupBy(x => x).map(t => (t._1, t._2.size)).toList.sortWith((l, r) => l._2 > r._2)
    println(tuples1.mkString(","))

  }
}
