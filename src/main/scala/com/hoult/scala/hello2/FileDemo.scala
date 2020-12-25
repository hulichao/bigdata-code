package com.hoult.scala.hello2
import scala.io._
/**
 * IO 操作是一门编程语言的重要内容
 * 相比于Java语言中的IO类，Scala最长用的是Source这个
 * 更多是调用Java中的Io，或者对Java中的io进行相应封装来实现io层面的操作
 */
object FileDemo {
  def main(args: Array[String]): Unit = {
//    val lines = scala.io.Source.fromFile("src/main/resources/hdfs-site.xml").getLines()
//    lines.foreach(println)

    readFromNet
  }

  //读取网络资源
  def readFromNet: Unit = {
    val soure = Source.fromURL("http://www.baidu.com")
    soure.getLines().foreach(println)
  }

}
