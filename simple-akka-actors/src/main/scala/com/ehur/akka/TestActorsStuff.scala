import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props

class HelloActor(myName:String) extends Actor {
  def receive = {
    case "hello" => println(s"hello from $myName")
    case _       => println(s"'huh?' said $myName")
  }
}

object Main extends App {
  val system = ActorSystem("HelloSystem")
  // default Actor constructor
  val helloActor = system.actorOf(Props(new HelloActor("Liz")), name = "helloactor")
  helloActor ! "hello"
  helloActor ! "buenos dias"
}