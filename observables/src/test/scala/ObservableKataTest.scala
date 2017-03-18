package observables
import org.scalatest.{FlatSpec, Matchers}
import rx.lang.scala.Observable

import scala.concurrent.duration._
/*
tinkering with examples from https://github.com/ReactiveX/RxScala/blob/0.x/examples/src/test/scala/examples/RxScalaDemo.scala
 */
class ObservableKataTest extends FlatSpec with Matchers {

  "observables" should "work with foreach" in {
    val o = Observable.from(Seq(1,2,3))
    //foreach is an alias to subscribe
    o.foreach(
    n => println(n),        //onNext
    e => e.printStackTrace(),   //onError
    () => println("done")       //onComplete
    )
  }

  "observables" should "work with for just for onNext" in {
    val o = Observable.just(1,2,3,4,5)
    for (num <- o) {
      println(num)
    }
  }

  "observables" should "work with explicit onNext, onError, onComplete" in {
    def onNextFn(n:Int) = println(n)
    def onErrFn(e:Throwable) = e.printStackTrace()
    def onComplFn = println("I'm done here")
    val o = Observable.just(7,8,9)
    o.foreach(
      onNext = (n:Int) => onNextFn(n),
      onError = (e:Throwable) => onErrFn(e),
      onComplete = () => onComplFn
    )
  }

  "observables" should "explicit onNext, onError, onComplete inlined" in {
    val o = Observable.just(7,8,9)
    o.foreach(
      onNext = (n:Int) => println(n),
      onError = (e:Throwable) => e.printStackTrace(),
      onComplete = () => println("I'm done here for inlined")
    )
  }

  "observables" should "work for intervals" in {
    val o=Observable.interval(200 millis).take(5)   // The Interval operator returns an Observable that emits an infinite sequence of ascending integers, with a constant interval of time of your choosing between emissions.
    for (num <- o) println(num)
    waitFor(o)
    println("done with interval thingy")
  }

  "observables" should "tumble their buffers" in {
    val fiveHundred = 1 to 500 toList
    val o = Observable.from(fiveHundred)
    val newO: Observable[(Int,Int)] = o.tumblingBuffer(5 seconds,50) map {
      // tumblingbuffer to capture buckets which we then map to new tuple. Those tuples will then be emitted as observable of tuples
      bucket =>
        if (bucket.nonEmpty) {
          (bucket.head, bucket.last)
        } else {
          (501, 501) //the end of the bucket
        }
    }
    newO.foreach(
      n => println(s"first of bucket: ${n._1} and last of bucket: ${n._2}"),
      e => e.printStackTrace(),   //onError
      () => println("done")       //onComplete
    )
  }

  "observables" should "slide their buffers" in {
    val fiveHundred = 1 to 500 toList
    val o = Observable.from(fiveHundred)
    val newO: Observable[(Int,Int)] = o.slidingBuffer(50,100) map {
      bucket =>
        if (bucket.nonEmpty) {
          (bucket.head, bucket.last)
        } else {
          (501, 501) //the end of the bucket
        }
    }
    newO.foreach(
      n => println(s"first of bucket: ${n._1} and last of bucket: ${n._2}"),
      e => e.printStackTrace(),   //onError
      () => println("done")       //onComplete
    )

  }

  "observables" should "compare" in {
    val first = Observable.from(List(10,11,12))
    val second = Observable.from(List(10,11,12))
    val a = (first zip second)
    a.foreach(
      n => println(n),
      e => e.printStackTrace(),
      () => println("that's all!")
    )
    val b = (first zip second) forall {case (a,b)=> a==b}
    b.toBlocking.single should be (true)     // A Single is something like an Observable, but instead of emitting a series of values — anywhere from none at all to an infinite number — it always either emits one value or an error notification.
  }

def waitFor[T](observable: Observable[T]):Unit = {
    observable.toBlocking.toIterable.last
  }

}
