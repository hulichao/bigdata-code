package com.hoult.spark

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

object WordCount {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("WordCount")
    val sc = new SparkContext(conf)
//     val lines: RDD[String] = sc.textFile("hdfs://linux121:8899/wcinput/wc.txt")
    val lines: RDD[String] = sc.textFile("data/spark/wc.txt")
    // val lines: RDD[String] = sc.textFile("data/wc.dat")
    lines.flatMap(_.split(" ")).map((_, 1)).reduceByKey(_+_).collect().foreach(println)
    sc.stop()
  }
}
