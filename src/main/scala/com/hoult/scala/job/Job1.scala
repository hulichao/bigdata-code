package com.hoult.scala.job

/**
 * 每瓶啤酒2元，3个空酒瓶或者5个瓶盖可换1瓶啤酒。100元最多可喝多少瓶啤酒？（不允许借啤酒）
 * 思路：利用递归算法，一次性买完，然后递归算出瓶盖和空瓶能换的啤酒数
 */
object Job1 {
  def extralPeer(bot: Int, cap: Int): Int = {
    val count = bot / 3 + cap / 5
    if (count <= 0)
      return 0

    val modBot = bot % 3
    val modCap = cap % 5
    count + extralPeer(count + modBot, count + modCap)
  }

  def main(args: Array[String]): Unit = {
    println(100 / 2 + extralPeer(100 / 2, 100 / 2))
  }


}
