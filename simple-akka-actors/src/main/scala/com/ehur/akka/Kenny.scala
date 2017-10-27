package com.ehur.akka

import akka.actor.{Actor, ActorSystem, PoisonPill, Props, Terminated}

case object Explode

class Kenny extends Actor {
  def receive = {

    case Explode => throw new Exception("Boom!")
    case _ => println("Kenny received a message")
  }
  override def preStart { println("kenny: preStart") }
  override def postStop { println("kenny: postStop") }
  override def preRestart(reason: Throwable, message: Option[Any]) {
    println("kenny: preRestart")
    super.preRestart(reason, message)
  }
  override def postRestart(reason: Throwable) {
    println("kenny: postRestart")
    super.postRestart(reason)
  }
}

class KennyParent extends Actor {
  // start Kenny as a child, then keep an eye on it
  val kenny = context.actorOf(Props[Kenny], name = "Kenny")
  context.watch(kenny)
  def receive = {
    case Terminated(kenny) => println("OMG, they killed Kenny")
    case _ => println("Parent received a message")
  }
}

object DeathWatchTest extends App {

  // create the ActorSystem instance
  val system = ActorSystem("DeathWatchTest")

  // create the Parent that will create Kenny
  val parent = system.actorOf(Props[KennyParent], name = "Parent")

  // lookup kenny, then kill it
  val kenny = system.actorSelection("/user/Parent/Kenny")
//  kenny ! PoisonPill
  kenny ! Explode
  Thread.sleep(5000)
  kenny ! "Hello?"
  println("calling system.shutdown")
  system.shutdown
}