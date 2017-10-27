package com.ehur.akka

import akka.actor.Actor
import akka.actor.Actor.Receive

class Pong extends Actor {
  override def receive: Receive = {
    case PingMessage =>
      println("   pong")
      sender ! PongMessage
    case StopMessage =>
      println("pong stopping")
      context.stop(self)
  }
}
