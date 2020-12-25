package demo

import org.apache.spark.{SparkConf, SparkContext}

object RddBase {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("wordcount")
    val sc = new SparkContext(conf)

    val input = sc.textFile("data/wc/wc.txt")
  }
}
