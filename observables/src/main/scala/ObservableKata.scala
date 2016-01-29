package observables

import rx.lang.scala.Observable
import scala.concurrent.duration._

object ObservableKata extends App {

  val o = Observable.interval(200 millis).take(5)
  o.subscribe(n => println("n = " + n))
  Observable.just(1, 2, 3, 4).reduce(_ + _)

}
