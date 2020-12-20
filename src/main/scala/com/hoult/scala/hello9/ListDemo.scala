package com.hoult.scala.hello9

object ListDemo {
  def main(args: Array[String]): Unit = {
    // 构建List
    val lst1 = 1 :: 2:: 3:: 4 :: Nil
    // :: 是右结合的
    val lst2 = 1 :: (2:: (3:: (4 :: Nil)))
    // 使用 ::: 拼接List
    val lst3 = lst1 ::: lst2
    println(lst3.mkString(","))
    val lst4 = lst1 ++ lst2
    println(lst4.mkString(","))
    // 使用 head、tail获取头尾
    lst3.head //返回第一个元素
    lst3.tail //返回除第一个元素外的其它元素构成的新列表
    lst3.init //返回除最后一个元素外的其它元素构成的新列表
    lst3.last //返回最后一个元素
  }

  // 快排
  def quickSort(lst: List[Int]): List[Int] = {
    lst match {
      case Nil => Nil
      case head :: tail =>
        val (less, greater) = tail.partition(_ < head)
        quickSort(less) ::: head :: quickSort(greater)
    }
  }
}
