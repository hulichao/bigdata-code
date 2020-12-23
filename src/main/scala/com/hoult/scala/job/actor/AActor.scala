package com.hoult.scala.job.actor

import akka.actor.{Actor, ActorRef}

class AActor extends Actor {

  var count=0     //信息发送次数
  def incrementAndPrint={
    count+=1       //每通信一次次数增一
  }

  // 持有BActorRef
  var bActorRef: ActorRef = _
  // 构建辅助构造器用以绑定BActorRef对象
  def this(bActorRef: ActorRef) {
    this
    // 绑定BActorRef对象
    this.bActorRef = bActorRef
  }
  // 重写receive方法
  override def receive: Receive = {
    // 开始信号
    case "start" => {
      println("AActor 开始了")
      // 发送消息给自己，触发case "go"
      self ! "go"
    }
    // 通讯信号
    case "go" => {
      println(s"AActor: 第$count 轮说话")
      incrementAndPrint
      Thread.sleep(1000)
      // 发送消息给BActor
      bActorRef ! "go"
    }
  }
}
