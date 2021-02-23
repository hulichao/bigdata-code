package com.hoult.scala.json4s
import org.json4s.NoTypeHints
import org.json4s.native.Serialization


object Demo2 {
  def main(args: Array[String]): Unit = {
    // 11.对象转json
    val jsonMap = ("name" -> "joe")
    implicit val formats = Serialization.formats(NoTypeHints)
    val str = Serialization.write(jsonMap)
    println(str)


  }
}
