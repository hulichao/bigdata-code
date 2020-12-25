package com.hoult.scala.hello3

import scala.collection.mutable.ArrayBuffer

//定长Aray
//变长ArrayBuffer
object ArrayDemo {
  def main(args: Array[String]): Unit = {
    println("###定长数组###########")
    val nums = new Array[Int](10)
    val strs = new Array[String](10)

    println(nums(1))
    println(strs(1))

    val arrays = Array(10,11)

    println(arrays)


    val numsBuffer = new ArrayBuffer[Int]()

    numsBuffer += 10
    numsBuffer += (1,2,3)
    numsBuffer ++= Array(1,10,3)

//    numsBuffer.foreach(println)

    //只会删除第一个
    println(numsBuffer.mkString(","))
    numsBuffer remove  1
    println(numsBuffer.mkString(","))
    numsBuffer --= Array(3,6)
    println(numsBuffer.mkString(","))


    numsBuffer(1) = 999
    println(numsBuffer.mkString(","))

    //移除最后三个元素
    numsBuffer.trimEnd(2)
    println(numsBuffer.mkString(","))

    //互相转换，变长和定长
    numsBuffer.toArray
    nums.toBuffer

    //遍历 until to
    for (i <- 0 until numsBuffer.length) {
      println(numsBuffer(i))
    }
    //增强for foreach
  }
}
