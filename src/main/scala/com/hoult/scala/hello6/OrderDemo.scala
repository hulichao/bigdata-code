package com.hoult.scala.hello6

import scala.util.Sorting

case class Project(tag: String, score: Int) extends Ordered[Project] {
  override def compare(that: Project): Int =
    tag.compareTo(that.tag)
}

object OrderDemo {
  def main(args: Array[String]): Unit = {
    val projects = List(Project("hadoop", 40), Project("spark", 30), Project("flink", 40), Project("hive", 10))
    println(projects.sorted)


    val pairs = Array(("a", 6, 2),("b", 9, 2),("c", 8, 1))

    Sorting.quickSort(pairs)(Ordering.by[(String, Int, Int), Int](_._2))

    println(pairs.toBuffer)
  }

}
