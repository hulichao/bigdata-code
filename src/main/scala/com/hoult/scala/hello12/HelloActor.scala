package com.hoult.scala.hello12

import akka.actor.{Actor, ActorSystem, Props}

import scala.io.StdIn

class HelloActor extends Actor {
  // 接收消息并处理
  override def receive: Receive = {
    case "吃了吗" => println("吃过了")
    case "吃的啥" => println("北京卤煮")
    case "拜拜" => {
      //关闭自己
      context.stop(self)
      //关闭ActorSystem
      context.system.terminate()
    }
    case _ => {
      println("说错话了")
    }
  }
}
object HelloActor {
  //创建线程池对象MyFactor，myFactory为线程池的名称
  private val MyFactory = ActorSystem("myFactory")
  // 通过MyFactory.actorOf方法来创建一个actor；
  // 第一个参数传递自定义的HelloActor类，第二个参数是给actor起个名字
  private val helloActorRef = MyFactory.actorOf(Props[HelloActor], "helloActor")
  def main(args: Array[String]): Unit = {
    var flag = true
    while (flag) {
      print("请输入想发送的消息：")
      val consoleLine: String = StdIn.readLine()
      //通过！来发送消息
      helloActorRef ! consoleLine
      if (consoleLine.equals("拜拜")) {
        flag = false
        println("程序即将结束！")
      }
      // 休眠100毫秒
      Thread.sleep(100)
    }
  }
}