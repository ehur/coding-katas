package com.ehur.akka

import akka.actor.{Actor, ActorRef, Props, Terminated}
import akka.actor.Actor.Receive

class WatchingParent extends Actor {

  override def receive: Receive = {
    case CreateChild(name) => {
      println(s"parent is going to create a new child: $name")
      val child = context.actorOf(Props[Child],name=s"$name")
      child ! Name(name)
      context.watch(child)
    }

    case CreateShortLivedChild(name) => {
      println(s"parent is going to create a new short-lived child: $name")
      val child = context.actorOf(Props(new ShortLivedChild(name)),name=s"$name")
      context.watch(child)
    }

    case Terminated(actorRef) => {
      println(s"well that one died: ${actorRef.path}")
    }

    case _ => println(s"parent got some other message...")
  }
}
