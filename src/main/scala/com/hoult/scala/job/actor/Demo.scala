package com.hoult.scala.job.actor

import akka.actor.{ActorSystem, Props}
import com.hoult.scala.hello12.HelloActor

import scala.io.StdIn

object Demo {
  private val MyFactory = ActorSystem("myFactory")
  // 通过MyFactory.actorOf方法来创建一个actor；
  //只给A发消息，让他们两个Actor开始对话
  private val bActorRef = MyFactory.actorOf(Props[BActor], "bAcator")
  private val aActorRef = MyFactory.actorOf(Props(new AActor(bActorRef)), "aAcator")
  def main(args: Array[String]): Unit = {
    var flag = true
    while (flag) {
      val consoleLine: String = StdIn.readLine()
      //通过！来发送消息
      aActorRef ! consoleLine
      if (consoleLine.equals("拜拜")) {
        flag = false
        println("程序即将结束！")
      }
      // 休眠100毫秒
      Thread.sleep(100)
    }
  }
}
