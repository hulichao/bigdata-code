package com.hoult.scala.hello9

object QueueDemo {
  def main(args: Array[String]): Unit = {
    //创建可变的队列
    val queue1 = new collection.mutable.Queue[Int]()
    println(queue1)
    //队列当中添加元素
    queue1 += 1
    //队列当中添加List
    queue1 ++= List(2,3,4)
    println(queue1)
    // 按照进入队列顺序，删除队列当中的元素（弹出队列）
    // 返回队列中的第一个元素，并从队列中删除该元素。
    val dequeue = queue1.dequeue()
    println(dequeue)
    println(queue1)
    // 向队列当中加入元素（入队列操作）
    // 元素入队列
    queue1.enqueue(5,6,7)
    println(queue1)
    //获取第一个、最后一个元素
    println(queue1.head)
    println(queue1.last)
  }
}
