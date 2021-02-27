package com.hoult.demo

import com.alibaba.fastjson.{JSON, JSONObject}

import scala.util.Try

object Test {
  def main(args: Array[String]): Unit = {
    val json: JSONObject = Try(JSON.parseObject("{}")).getOrElse(null)
    println(json)
  }
}
