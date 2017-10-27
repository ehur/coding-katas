package com.ehur.akka

import akka.actor.{Actor, ActorRef, Props, Terminated}
import akka.actor.Actor.Receive

case class CreateChild (name:String)
case class CreateShortLivedChild (name:String)
case class WatchChild (childRef:ActorRef)
case class Name (name:String)
case class EndInFive()

class Parent extends Actor {

  override def receive: Receive = {

    case CreateChild(name) =>
      println(s"parent is going to create a new child: $name")
      val child = context.actorOf(Props[Child],name=s"$name")
      child ! Name(name)


    case _ => println(s"parent got some other message...")
  }
}
