package com.hoult.scala.job.actor

import akka.actor.Actor

class BActor extends Actor {

  var count=0     //信息发送次数
  def incrementAndPrint={
    count+=1       //每通信一次次数增一
  }

  override def receive: Receive = {
    case "go" => {
      Thread.sleep(1000)
      println(s"BActor开始，第$count 轮说话")
      sender() ! "go" //发送给发送者
      incrementAndPrint
    }

  }
}
