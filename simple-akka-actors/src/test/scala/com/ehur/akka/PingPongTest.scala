package com.ehur.akka

import akka.actor.{ActorSystem, Props}

object PingPongTest extends App {

  val system = ActorSystem("PingPongSystem")
  val pong = system.actorOf(Props[Pong],name = "ponger")
  val ping = system.actorOf(Props(new Ping(pong)),name = "pinger")
  ping ! StartMessage
  system.shutdown()

}
