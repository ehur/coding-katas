import java.util

import org.scalatest.{FlatSpec, Matchers}

import scala.collection.{immutable, mutable}

class DentistTest  extends FlatSpec with Matchers {

  "balloonstripper" should "work" in {

    var tester = "BAONXXOLL"
    var res = stripBalloon(tester)
    res should be (1)
    tester = "BAOOLLNNOLOLGBAX"
    stripBalloon(tester) should be (2)
     tester = "QAWABAWONL"
    stripBalloon(tester) should be (0)
    tester = "ONLABLABLOON"
    stripBalloon(tester) should be (1)
  }
  def stripBalloon(s:String) = {
    val balloonMap: mutable.Map[Char, Int] = "BALLOON".toList.map(c => c -> 1).foldLeft(mutable.Map[Char,Int]())((c, r) => {
      c(r._1) = c.getOrElse(r._1,0) + 1
      c
    })
    val testMap: mutable.Map[Char, Int] = s.toList.map(c => c -> 1).foldLeft(mutable.Map[Char,Int]())((c, r) => {
      c(r._1) = c.getOrElse(r._1,0) + 1
      c
    })
    var entriesLeft = true
    var counter = 0
    while (entriesLeft) {
      balloonMap.foreach{entry =>
        testMap(entry._1) = testMap.getOrElse(entry._1,0) - entry._2
      }
      if (!testMap.filter(x => x._2 < 0).isEmpty ) {
        entriesLeft = false
      } else {
        counter += 1
      }
    }
    counter

  }

  "arrayFinder" should "find array" in {
    //val arr= Array(0,1,1,1,0,1,1,1,0,1,1,1)
    val arr= Array[Int]()
    val res = solutionX(arr)
    res should be (-1)
  }

  def solutionX(A: Array[Int]): Int = {
    var n: Int = A.length;
    var i: Int = n - 1;
    var result: Int = -1;
    var k: Int = 0;
    var maximal: Int = 0;
    while (i > 0) {
      if (A(i) == 1) {
        k = k + 1;
        if (k >= maximal) {
          maximal = k;
          result = i;
        }
      } else {
        k = 0;
      }
      i = i - 1;
    }
    if (i >= 0 && A(i) == 1 && k + 1 > maximal)
        result = 0;
    return result;
  }

  "ocr output" should "work" in {
    var s = "A2Le"
    var t = "2pL1"
    solution(s,t) should be (true)
  }

  def solution(s: String, t: String): Boolean = {
    //parse digits
    val sDigits = ("""\d+""".r findAllIn s).toList
    val tDigits: immutable.Seq[String] = ("""\d+""".r findAllIn t).toList
    val replacedS = sDigits.foldLeft(s)((r,c) => {
      val underscores = List.fill(c.toInt)("_").mkString("")
      r.replaceAll(c,underscores)
    })
    var r:String = t
    val replacedT = tDigits.foldLeft(t)((r,c) => {
       val underscores = List.fill(c.toInt)("_").mkString("")
       r.replaceAll(c,underscores)
    })
    equivalent(replacedS,replacedT)
  }

  def equivalent(s:String, t:String): Boolean = {
    //strings are equal if the characters in same index position are the same, or one is an underscore.
    if (s.length != t.length) false
    else {
      var same = true
      var i = 0
      val underscore: Char = '_'
      while (same && i < s.length) {
        if (s(i).equals(t(i)) || s(i).equals(underscore) || t(i).equals(underscore)){
          i+=1
        }
        else {
          same = false
          i+=1
        }
      }
      same
    }
  }
}
