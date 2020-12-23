package com.hoult.scala.job.game

import scala.beans.BeanProperty
import scala.util.Random

class Computer {

  @BeanProperty var name: String = _
  @BeanProperty var score: Int = _
  @BeanProperty var winPingFail: Array[Int] = new Array[Int](3)

  def User(name: String, score: Int): Unit ={
    this.name = name
    this.score = score
  }
  def showFist(): Int = {
    println(s"${name}出拳!")
    Random.nextInt(3) + 1
  }
}
