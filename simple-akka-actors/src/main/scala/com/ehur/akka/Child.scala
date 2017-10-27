package com.ehur.akka

import akka.actor.Actor
import akka.actor.Actor.Receive

class Child extends Actor {

  var name = "No name"

  override def receive: Receive = {
    case Name(name) => {
      this.name = name
      println(s"I've just been named $name")
    }
    case _ => println(s"I, child $name, got a message")
  }

  override def postStop(): Unit = println(s"I've been stopped - $name: ${self.path}")

}
