package com.hoult.scala.hello3

/**
 * scala 常见算法
 */
object OperatorDemo {
  def main(args: Array[String]): Unit = {
    //将数组中的偶数加倍，奇数丢弃
    val nums = (1 to 10).toArray

    val result = for (elem <- nums if elem % 2 == 0) yield elem * 2
    println(result.mkString(","))
    //没有else 的化结果是unit
    val result2 = for (elem <- nums) yield if (elem % 2 == 0) elem * 2 else 0

    println(result2.mkString(","))

    //map filter
    println(nums.filter(_ % 2 == 0).map(_ * 2).mkString(","))

    println(nums.tail.mkString(","))
    println(nums.init.mkString(","))

    //最大，最小，和
    nums.sum
    nums.max
    nums.min

    //数组排序
    println(nums.sorted.toBuffer)
    //元素相乘
    println(nums.take(3).product)

    nums.reduce(_+_)
    nums.distinct
    nums.length

    //每个元素的数据索引
    println(nums.indices.mkString(","))

    nums.mkString("<", "*", ">")

    //count 后面必须加条件
    println(nums.count(_ != null))

    //filter filterNot
    println(nums.filterNot(_ > 2).toBuffer)


    println(nums.takeRight(3).toBuffer)
    println(nums.takeWhile(_ % 6 != 0).toBuffer)

    println(nums.drop(3).toBuffer)
    println(nums.dropWhile(_ < 3).toBuffer)

    //将数组分为两部分，前N个，和剩下的
    println(nums.splitAt(3)._1.toBuffer + ":" + nums.splitAt(3)._2.toBuffer)

    //对数组切片，取出从索引4， 5.。。的元素
    println(nums.slice(4, 6).toBuffer)


    //从前到后拉链截取相同长度
    val a1 = Array("A", "B", "C", "D", "E")
    val a2 = Array(1, 2, 3, 5)
    println(a1.zip(a2).toBuffer)

    // * 填充不一样的a1 -1 填充a2
    println(a1.zipAll(a2, "*", -1).toBuffer)


    println(a1.zipWithIndex.toBuffer)

    //unzip拆分数组
    println(a1.zip(a2).toBuffer.unzip)

    val (l1,l2,l3) = Array((1, "one", "1"),(1, "twon", "1"),(1, "three", "1")).unzip3

    println(l1.toBuffer + ":" + l2.toBuffer + ":" + l3.toBuffer)

    //数组的操作符， :+ 尾部 +:头部 ++ 连接集合，数组，列表
    val num1: Array[Int] = (1 to 4).toArray
    val num2: Array[Int] = (5 to 8).toArray

    val num3 = 10 +: num1
    val num4 = num2 :+ 9
    val num5 = num2 ++ num1

    println(num3.toBuffer)
    println(num4.toBuffer)
    println(num5.toBuffer)


    //排序
    val sortNum = Array(1,5,6,2,6,8)

    println(sortNum.sorted.toBuffer)
    println(sortNum.sorted.reverse.toBuffer)

    println(sortNum.sortWith(_ > _).toBuffer)
    println(sortNum.sortWith(_ < _).toBuffer)




  }

}
