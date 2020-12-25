package com.hoult.scala.job.spark

import java.util.UUID

import akka.actor.{Actor, ActorSelection, ActorSystem, Props}
import com.typesafe.config.ConfigFactory

import scala.collection.mutable

// worker注册信息
case class RegisterWorkerInfo(id: String, cpu: Int, ram: Int)
// 当worker注册成功，服务器返回一个RegisteredWorkerInfo对象

case object RegisteredWorkerInfo

// 该信息是保存到master的hashmap中，用于管理worker
class WorkerInfo(val id: String, val cpu: Int, val ram: Int) {
  // 最后更新的心跳时间戳
  var lastHeartBeatTime: Long = _
}

// 每隔一段时间发送告诉自己发送心跳
case object SendHeartBeat
// 向master发送心跳信息
case class HeartBeat(id: String)

// master给自己发送一个触发检查超时worker的信息
case object StartTimeOutWorker
// master给自己发消息，检测worker，移除心跳超时的worker
case object RemoveTimeOutWorker

import scala.concurrent.duration._
//master端Actor
class MasterActor extends Actor {
  val workers = mutable.Map[String, WorkerInfo]()
  override def receive: Receive = {
    case "start" => {
      println("master running....")
      // 检查超时worker
      self ! StartTimeOutWorker
    }

    case StartTimeOutWorker => {
      println("start check timeout worker...")
      // 定义定时器，每隔一段时间检查worker心跳是否超时
      import context.dispatcher
      context.system.scheduler.schedule(0 millis, 9000 millis, self, RemoveTimeOutWorker)
    }
    case RemoveTimeOutWorker => {
      // 获取workers中所有workerInfo
      val workerInfos = workers.values
      // 获取当前时间
      val currentTime = System.currentTimeMillis()
      // 找出超时6秒的worker
      workerInfos.filter(info => (currentTime - info.lastHeartBeatTime) > 6000)
        .foreach(workers -= _.id)
      println(s"===> workers.size = ${workers.size}")
    }

    case RegisterWorkerInfo(id, cpu, ram) => {
      //判断是否已注册
      if (!workers.contains(id)) {
        val info =
        // 添加数据
        workers += (id -> new WorkerInfo(id, cpu, ram)) //worker列表添加
        println("workers => 注册:" + workers)
        //注册成功回复消息
        sender() ! RegisteredWorkerInfo
      }

    }
    case HeartBeat(id) => {
      // 更新对应workinfo的心跳时间
      // 从workers中取出workerinfo
      val workerInfo = workers(id)
      // 更新最后心跳时间
      workerInfo.lastHeartBeatTime = System.currentTimeMillis()
      println(s"master updated worker ${id}'s heartbeat")
    }
  }
}

object MasterActorApp {
  def main(args: Array[String]): Unit = {
    //服务端地址
    val host = "127.0.0.1"
    //端口
    val port = "8800"
    //创建config对象，指定协议类型，监听的ip端口
    val conf = ConfigFactory.parseString(
      s"""
         |akka.actor.provider="akka.remote.RemoteActorRefProvider"
         |akka.remote.netty.tcp.hostname=$host
         |akka.remote.netty.tcp.port=$port
         |""".stripMargin)

    val masterActorSystem = ActorSystem("masterActorSystem", conf)
    //创建MasterActor 实例并返回代理
    val masterActorRef = masterActorSystem.actorOf(Props[MasterActor], "masterActor")

    masterActorRef ! "start"
  }
}

class WorkerActor extends Actor {
  var masterHost: String = _
  var masterPort: Int = _
  // worker的id号
  val id: String = UUID.randomUUID().toString
  var masterProxy: ActorSelection = _
  def this(masterHost: String, masterPort: Int) {
    this
    this.masterHost = masterHost
    this.masterPort = masterPort
  }
  override def preStart(): Unit = {
    val path = s"akka.tcp://masterActorSystem@${masterHost}:${masterPort}/user/masterActor"
    masterProxy = context.actorSelection(path)
    println(masterProxy)
  }
  override def receive: Receive = {

    case "start" => {
      println("worker running...")
      // 发送注册信息
      masterProxy ! RegisterWorkerInfo(id, 16, 16 * 1024)
    }
    case RegisteredWorkerInfo => {
      println(s"worker ${id} registered!")

      // 定义定时器，每隔一段时间告诉自己发送心跳
      import context.dispatcher
      // 0 millis：立即执行
      // 3000 millis：每隔3秒执行
      // self：接收对象，发给自己
      // SendHeartBeat：发送内容
      context.system.scheduler.schedule(0 millis, 3000 millis, self, SendHeartBeat)
    }
    //发送心跳
    case SendHeartBeat => {
      println(s"worker ${id} send heartbeat to master")
      masterProxy ! HeartBeat(id)
    }
  }
}
object WorkerApp {
  def main(args: Array[String]): Unit = {
    val (workerHost, workerPort, masterHost, masterPort) = ("127.0.0.1", 8801, "127.0.0.1", 8800)
    val config = ConfigFactory.parseString(
      s"""
         |akka.actor.provider="akka.remote.RemoteActorRefProvider"
         |akka.remote.netty.tcp.hostname=$workerHost
         |akka.remote.netty.tcp.port=$workerPort
        """.stripMargin)
    // 创建actorSystem
    val workerActorSystem = ActorSystem("workerActorSystem", config)
    // 创建WorkerActor并返回代理
    val workerActorRef = workerActorSystem.actorOf(Props(new WorkerActor(masterHost, masterPort)), "workerActor")
    workerActorRef ! "start"
  }
}
