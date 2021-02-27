package com.hoult.hello

import scala.collection.mutable

object NewWordCount {
  def main(args: Array[String]): Unit = {
    val lineList: List[(String, Int)] = List(("Hello Scala World", 4), ("Hello World", 3), ("Hello Hadoop", 2), ("Hello Hbase", 1))

    // 分成单个元组数据
    val flatMapList: List[(String, Int)] = lineList.flatMap(t => {
      val words: mutable.ArrayOps[String] = t._1.split("\\s+")
      words.map(x => (x, t._2))
    })

//    val wordToSum: Map[String, Int] = flatMapList.groupBy(t => t._1).map(t => {
//      val list: List[Int] = t._2.map(t => t._2)
//      (t._1, list.sum)
//    })
//    println(wordToSum)

    val wordToSumMap: Map[String, Int] = flatMapList.groupBy(t => t._1).mapValues(datas => datas.map(tt => tt._2).sum)
//    println(wordToSumMap)
    //将统计的结果进行降序排列
    val resultList: List[(String, Int)] = wordToSumMap.toList.sortWith((l, r) => l._2 > r._2).take(3)
    println(resultList)

  }

}
