package com.ehur.akka

import akka.actor.Actor
import akka.actor.Actor.Receive

class ShortLivedChild(name:String) extends Actor {

  override def receive: Receive = {
    case EndInFive => {
      println("I will end in 5 seconds ... ")
      Thread.sleep(5000)
      context.stop(self)
    }
    case _ => println(s"actor at ${self.path} got some other message")
  }
}
