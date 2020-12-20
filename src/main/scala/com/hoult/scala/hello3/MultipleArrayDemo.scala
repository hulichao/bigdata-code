package com.hoult.scala.hello3

object MultipleArrayDemo {
  def main(args: Array[String]): Unit = {
    //定义一个3行4列的二维数组
    val dim = Array.ofDim[Double](3, 4)

    dim(1)(1) = 12.5

    for (i <- 0 to 2;j <- 0 to 3) {
      print(dim(i)(j) + "  ")

      if (j == 3)
        println()
    }
  }
}
