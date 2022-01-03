package com.hoult.scala.hello9

object SetDemo {
  def main(args: Array[String]): Unit = {
    // 判断元素是否存在
    val set = Set(1, 2, 3, 4, 5, 6, 7)
    println(set.exists(_ % 2 == 0))
    // 删除元素
    set.drop(1)
    // 引入可变的Set
    import scala.collection.mutable.Set
    Set.empty
    val mutableSet = Set(4, 5, 6)
    // 增加元素、删除元素；执行成功返回true，否则返回false
    mutableSet.add(7)
    println(mutableSet)
    mutableSet.remove(7)
    println(mutableSet)
    // 使用 += / -= 增加、删除元素，表达更简洁
    mutableSet += 5
    mutableSet -= 2
    // 集合典型操作交、并、差
    //交集（&、intersect）
    println(Set(1, 2, 3) & Set(2, 3, 4))
    println(Set(1, 2, 3).intersect(Set(2, 3, 4)))
    println(Set(1, 2, 3) intersect (Set(2, 3, 4)))
    //并集(++、|、union)
    println(Set(1, 2, 3) ++ Set(2, 3, 4))
    println(Set(1, 2, 3) | Set(2, 3, 4))
    println(Set(1, 2, 3).union(Set(2, 3, 4)))
    //差集(--、&~、diff)
    //返回:包含本集合中不包含在给定集合中的元素的集合
    println(Set(1, 2, 3) -- Set(2, 3, 4))
    println(Set(1, 2, 3) &~ Set(2, 3, 4))
    println(Set(1, 2, 3).diff(Set(2, 3, 4)))
  }
}
