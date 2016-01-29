package observables
import org.scalatest.{ShouldMatchers, FlatSpec, Matchers}
import rx.lang.scala.Observable
import scala.concurrent.duration._

class ObservableKataTest extends FlatSpec with ShouldMatchers {

  "observables" should "work with foreach" in {
    val o = Observable.just(1,2,3)
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
    val o=Observable.interval(200 millis).take(5)
    for (num <- o) println(num)
    waitFor(o)
    println("done with interval thingy")
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
    b.toBlocking.single should be (true)
  }

def waitFor[T](observable: Observable[T]):Unit = {
    observable.toBlocking.toIterable.last
  }

}
