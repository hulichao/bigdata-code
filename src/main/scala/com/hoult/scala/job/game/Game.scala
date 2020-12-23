package com.hoult.scala.job.game


import scala.io.StdIn


/**
 * 三部分，开战前，开战中，结束后
 */
object Game {

  val fistMap: Map[Int, String] = Map((1,"剪刀"),(2, "石头"), (3, "布"))
  val computer: Computer = new Computer()
  val user: User = new User()

  def main(args: Array[String]): Unit = {
    start
  }

  def start() = {
    println("--------------------欢迎进入游戏世界------------------")
    println("--------------------猜拳开始------------------")
    println("--------------------*************------------------")
    println("请选择对战角色:(1.刘备,2.关羽,3.张飞)")


    val roleNo = StdIn.readInt()

    roleNo match {
      case 1 => computer.setName("刘备")
      case 2 => computer.setName("关羽")
      case 3 => computer.setName("张飞")
      case _ => println("请重新输入对战角色，(1.刘备,2.关羽,3.张飞)")
    }

    println(s"你选择了与${computer.name}对战")
    println("要开始了吗？y/n")
    var in: String = null
    var count = 0
    while ((in = StdIn.readLine()) != null) {
      if (!in.equals("y")) {
        println("退出游戏！")
        println("--------------------------------------------")
        println(s"${computer.name} VS 游客")
        println(s"对战次数:$count 次")
        println("姓名\t得分\t胜局\t和局\t负局")
        println(s"游客\t${user.score}\t${user.winPingFail(0)}\t${user.winPingFail(1)}\t${user.winPingFail(2)}")
        println(s"${computer.name}\t${computer.score}\t${computer.winPingFail(0)}\t${computer.winPingFail(1)}\t${computer.winPingFail(2)}")
        System.exit(0)
      } else {
        count += 1
        println("请出拳! 1.剪刀 2.石头 3.布")
        var a: Int = 3
        try {
          a = StdIn.readInt()
        } catch {
            case ex: Exception => a = 3
            println("出拳不符合规定，默认给你布")
        }
        println(s"你出拳: ${fistMap(a)}")
        val b = computer.showFist()
        println(s"${computer.name}出拳: ${fistMap(b)}")

        val (a1,b1) = judge(a, b)
        user.score += a1
        computer.score += b1
      }
      println("是否开始下一轮 (y/n)")
    }


    def judge(a: Int, b: Int): Tuple2[Int, Int] = {
      //返回a 得分 和 b得分的元组
      if ((a - b) == 0) {
        println("结果：和局！ 下次继续努力")
        computer.winPingFail(1) += 1
        user.winPingFail(1)  += 1
        (0,0)
      } else if ((a - b) == 1 || (a - b) == -2 ) {
        println("恭喜，你赢啦")
        computer.winPingFail(0) += 1
        user.winPingFail(2)  += 1
        (1, 0)
      } else {
        println("不好意思，你输啦")
        computer.winPingFail(2) += 1
        user.winPingFail(0)  += 1
        (0, 1)
      }
    }

  }
}
