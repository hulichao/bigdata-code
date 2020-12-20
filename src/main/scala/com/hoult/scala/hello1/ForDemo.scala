package com.hoult.scala.hello1

/**
 * while do while 与java类似，但是没有for
 * scala中的for: for(i <- 表达式或者集合)
 * 注意：
 *
 */
object ForDemo {
  def main(args: Array[String]): Unit = {
    //for 循环结构
    for (i <- 1 to 10) println(i)
    println("##################")
    for (i <- 1 until 10) println(i)

    //双重循环
    println("##################")
    for (i <- 1 to 3; j <- 1 to 5) {
      println(i * j)
    }

    println("##########使用变量########")
    for (i <- 1 to 3; k <- 1 to 3; j = 4-i) {
      println(s"$i $j $k")
    }


    println("##########使用变量 和上面一样，与位置没有关系########")
//    for (i <- 1 to 3; j = 4-i; k <- 1 to 3) {
//      println(s"$i $j $k")
//    }

    //守卫
    println("##################守卫 #############")
    for (i <- 1 to 3 if i != 2)
      println(i)

    println("#################推导式，使用yield接受返回结果")
    val result = for (i <- 1 to 5) yield i % 2

    println(result)

    println("九九乘法表")

    for (i <- 1 to 9;j <- 1 to i) {
      print(s"$i * $j = ${i * j}\t")
      if (i == j) println()
    }

    println("$$$$$$$$for 大括号#####")

    for {
      i <- 1 to 3
      from = 4 - i
      j <- from to 3
    }
      println(s"i=$i,j=$j")

    println("#######遍历字符串###########")
    val message = "hello spark"
    for (s <- message) {
      println(message(0))
      print(s + "\t")
    }

    //使用breakable 和 break 终止循环，需要导入scala.util.control.Breaks包
    import scala.util.control.Breaks._

    var res = 0
    breakable {
      for (i <- 1 until 10) {
        if (i == 5) break()
        res += i
      }

    }
    println(res)


  }

}
