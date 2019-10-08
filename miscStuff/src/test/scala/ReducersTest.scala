import org.scalatest.{FlatSpec, Matchers}

import scala.collection.mutable

class ReducersTest extends FlatSpec with Matchers {

  "deduplicate" should "dedupelicate" in {

    val mapsy = mutable.Map[String,Int]()
    mapsy.put("one",1)
    mapsy.put("oneagain",1)
    mapsy.put("two",2)

    val seen  = mutable.Map[Int,String]()

    val dupes = mapsy.foldLeft(mutable.Map[String,Int]())((m, e) => {
      if (seen.contains(e._2)) m.put(e._1,e._2) else seen.put(e._2,e._1)
      m
    })

    dupes.size should be (1)

  }


}
