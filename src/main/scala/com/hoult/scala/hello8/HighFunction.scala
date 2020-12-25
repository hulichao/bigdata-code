package com.hoult.scala.hello8

object HighFunction {
  def main(args: Array[String]): Unit = {
    //接收一个或多个函数作为输入的高阶函数
    val func = "*" * _

    (1 to 5).map(func).foreach(println)

    //输出一个函数的高阶函数
    val URLBuilder = (ssl: Boolean, domainName: String) => {
      val schema = if (ssl) "https://" else "http://"

      //输出一个匿名函数
      (endpoint: String, query: String) => s"$schema$domainName/$endpoint?$query"
    }


    val dom = "www.hulichao.top"

    def getUrl = URLBuilder(false, dom)

    println(getUrl("show", "id=1"))
  }
}
