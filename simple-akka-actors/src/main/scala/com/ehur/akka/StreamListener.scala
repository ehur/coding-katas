package com.ehur.akka

import akka.actor.{Actor, ActorRef, ActorSystem, Props, Terminated}


case class InitAidPid(adId:Long, placementId: Long)

object StreamListener extends App {

  val initializerSystem = ActorSystem("InitializerActorSystem")
  val initializerJobManager = initializerSystem.actorOf(Props[InitializerManager])
  for (i <- 5 to 1 by -1) {
    val aid = 1
    initializerJobManager ! InitAidPid(aid,i)
    Thread.sleep(1000)
  }

}

class InitializerManager extends Actor {

  val actorMap = Map[String,ActorRef]()

  def receive = {
      case InitAidPid(adId, placementId) => {
        println(s"Received init message for adId $adId and placementId $placementId")
        val actor = context.actorOf(Props(new InitializerActor(adId,placementId)))
        context.watch(actor)
      }
      case Terminated(someChild) => {
        println(s"child at ${someChild.path} terminated.")
      }
  }
}

class InitializerActor(adId:Long, placementId:Long) extends Actor {

  val sleepInterval = (adId + placementId) * 5000

  fakeAProcessThatJustTakesSomeTime()

  def fakeAProcessThatJustTakesSomeTime() = {
    println(s"$adId-$placementId sleeping for $sleepInterval ...")
    Thread.sleep(sleepInterval)
    println(s"$adId-$placementId terminating")
    context.stop(self)
  }

  def receive = {
    case _ => println(s"$adId-$placementId received a message. No idea what to do with it!")
  }

}

