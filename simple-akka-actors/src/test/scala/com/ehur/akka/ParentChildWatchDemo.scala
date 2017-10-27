package com.ehur.akka

import akka.actor.{ActorSystem, PoisonPill, Props}

object ParentChildWatchDemo extends App {

  val system = ActorSystem("ParentChildWatchDemo")
  val parent = system.actorOf(Props[WatchingParent],name="watching-parent")
  parent ! CreateChild("Rufus")
  val rufus = system.actorSelection("/user/Parent/Rufus")
  rufus ! PoisonPill
  Thread.sleep(1000)

  println("creating Shorty...")
  parent ! CreateShortLivedChild("Shorty")
  val shorty = system.actorSelection("user/Parent/Shorty")
  println(s"created Shorty at ${shorty.toString()}")
  shorty ! EndInFive
  //println("calling shutdown")
  //system.shutdown()


}
