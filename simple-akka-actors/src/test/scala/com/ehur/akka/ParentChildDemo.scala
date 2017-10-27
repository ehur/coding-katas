package com.ehur.akka

import akka.actor.{ActorSystem, PoisonPill, Props}

object ParentChildDemo extends App {

  val actorSystem = ActorSystem("ParentChildDemo")
  val parent = actorSystem.actorOf(Props[Parent], name="Pops")
  parent ! CreateChild("Goofy")
  parent ! CreateChild("Moofy")
  Thread.sleep(500)

  println(s"time to kill off Goofy")
  val goofy = actorSystem.actorSelection("/user/Parent/Goofy")
  goofy ! PoisonPill
  Thread.sleep(500)
  println(s"Goofy should be gone by now, shutting down...")
  Thread.sleep(5000)
  actorSystem.shutdown()

}
