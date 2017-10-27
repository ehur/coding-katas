package com.ehur.akka

import akka.actor.{Actor, ActorRef}

case object PingMessage
case object PongMessage
case object StartMessage
case object StopMessage

class Ping(pong: ActorRef) extends Actor {
  var count = 0
  def incrementAndPrint { count += 1; println("ping") }

  def receive = {
    case StartMessage =>
      incrementAndPrint
      pong ! PingMessage

    case PongMessage =>
      incrementAndPrint
      if (count > 99) {
        println(s"reached $count pong messages; sending stopmessage to pong, and ping is shutting down now....")
        sender ! StopMessage
        context.stop(self)
      } else {
        sender ! PingMessage
      }
  }
}
