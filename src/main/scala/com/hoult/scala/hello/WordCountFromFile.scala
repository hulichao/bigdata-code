package com.hoult.hello

import scala.io.Source

object WordCountFromFile {
  def main(args: Array[String]): Unit = {
    //从文件获取数据，统计数量
    Source.fromFile("in/word.txt").getLines().toList
  }

}
