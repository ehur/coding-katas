package scala.com.ehur.thefutureshere

import org.scalatest.{FlatSpec, Matchers}

import scala.concurrent.{Await, Future}
import scala.util.{Failure, Success, Try}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration


object AllAboutTheFuture extends App {

  val theList = List("1","2","three","4")
//convert futures to "Trys" - also known as "lifting". This is a way to to convert all the Future[T] results to Future[Try[T]]] instances, all of which are certain to be successful futures
  //see http://stackoverflow.com/questions/29344430/scala-waiting-for-sequence-of-futures. Now Future.sequence(lifted) will be completed when every future is completed, and will represent successes and failures using Try.
  def lifted[T](futures: List[Future[T]]) = futures.map(_.map(Success(_)).recover{ case x => Failure(x) })

    def futureParse(s:String) = Future {
      Thread.sleep(2000)
      s.toInt
    }

    val futureInts: List[Future[Int]] = theList.map(futureParse(_))

    val liftedFutureTrys = lifted(futureInts)

    val futureListOfTrys: Future[List[Try[Int] with Product with Serializable]] = Future.sequence(liftedFutureTrys)

    Await.ready(futureListOfTrys,Duration.Inf)
  val valuuu: Try[List[Try[Int] with Product with Serializable]] = futureListOfTrys.value.get
    valuuu match {
      case Success(thing) => {
        println(thing)
        thing.map(result => result match {
          case Success(r) => println(s"SUCCESS! $r")
          case Failure(f) => println(s"FAILURE :( $f")
        })
      }
      case _ => println("yikes - what happened?")
    }



}
