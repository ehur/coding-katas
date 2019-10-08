import org.scalatest.{FlatSpec, Matchers}

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

class HackerRank extends FlatSpec with Matchers {

  type Triplet = (Int,Int,Int)

  "compareTriplets" should "work" in {
    //given
    val a: Triplet = (1,2,3)
    val b: Triplet = (3,2,1)
    //when
    val (alice,bob) = compareTripletsT(a,b)
    //then
    alice should be (1)
    bob should be (1)
  }

  "compareTriplets Array" should "also work" in {
    //given
    val a: Array[Int] = Array(1,2,3)
    val b: Array[Int] = Array(3,2,1)
    //when
    val (alice,bob) = compareTripletsArrayTup(a,b)
    //then
    alice should be (1)
    bob should be (1)
  }

  "compareTriplets Array Another" should "also work" in {
    //given
    val a: Array[Int] = Array(17,28,30)
    val b: Array[Int] = Array(99,16,8)
    //when
    val aliceNBob = compareTriplets(a,b)
    //then
    aliceNBob(0) should be (2)
    aliceNBob(1) should be (1)
  }

  def compareTriplets(a:Array[Int], b:Array[Int]): Array[Int] = {
    var zero = (0,0)
    def compare(summ:(Int,Int),x:(Int,Int)): (Int,Int) =
      ( if (x._1 - x._2 > 0) summ._1 + 1 else summ._1 + 0,
        if (x._2 - x._1 > 0) summ._2 + 1 else summ._2 + 0)
    //todo: zip a and b then foldLeft with compare
    val (alice,bob) =a.zip(b).foldLeft(zero)(compare)
    Array(alice,bob)
  }

  def compareTripletsArrayTup(a:Array[Int], b:Array[Int]): (Int,Int) = {
    var zero = (0,0)
    def compare(summ:(Int,Int),x:(Int,Int)): (Int,Int) =
      ( if (x._1 - x._2 > 0) summ._1 + 1 else summ._1 + 0,
      if (x._2 - x._1 > 0) summ._2 + 1 else summ._2 + 0)
    //todo: zip a and b then foldLeft with compare
     a.zip(b).foldLeft(zero)(compare)
  }

  def compareTripletsT(a:Triplet, b:Triplet): (Int,Int) = {
    val alice1 =  if (a._1 - b._1 > 0)  1 else 0
    val alice2 =  if (a._2 - b._2 > 0)  1 else 0
    val alice3 =  if (a._3 - b._3 > 0)  1 else 0
    val bob1 = if (b._1 - a._1 > 0 ) 1 else 0
    val bob2 = if (b._2 - a._2 > 0 ) 1 else 0
    val bob3 = if (b._3 - a._3 > 0 ) 1 else 0
    return (alice1+alice2+alice3, bob1+bob2+bob3)
  }

  "aVeryBigSum" should "work" in {
    val arr:Array[Long] = Array(1000,2000,3000)
    val summ = aVeryBigSum(arr)
    summ should be (6000)
  }

  def aVeryBigSum(ar: Array[Long]): Long = {
    ar.foldLeft(0L)(_ + _)
  }

  "diagonalDifference" should "work" in {
    val arr: Array[Array[Int]] = Array(Array(11,2,4),Array(4,5,6),Array(10,8,-12))
    val absDiff = diagonalDifference(arr)
    absDiff should be (15)
  }

  def diagonalDifference(arr: Array[Array[Int]]): Int = {
    val lr:Array[Int] = for ((row,index) <- arr.zipWithIndex) yield row(index)
    val rl: Array[Int] = for ((row,index) <- arr.reverse.zipWithIndex) yield row(index)
    Math.abs(lr.sum - rl.sum)
  }

  "plusMinus" should "printstuff" in {
    val arr: Array[Int] = Array(-4, 3, -9, 0, 4, 1 )
    val result = plusMinus(arr)
  }

  def plusMinus(arr: Array[Int])= {
    def round(bd:scala.math.BigDecimal): String = {
      val scaled = bd.setScale(6,scala.math.BigDecimal.RoundingMode.HALF_DOWN)
      f"$scaled%.6f"
    }

    val positives = arr.filter(_ > 0)
    val negatives = arr.filter(_ < 0)
    val zeros = arr.filter(_ == 0)
    println(round(scala.math.BigDecimal(positives.length) / arr.length))
    println(round(scala.math.BigDecimal(negatives.length) / arr.length))
    println(round(scala.math.BigDecimal(zeros.length) / arr.length))
  }

  "staircase" should "step" in {
    val caseSize = 6
    staircase(6)

  }
  def staircase(n: Int) {
    val out: Seq[String] = for (i <- 1 to n) yield {
      (Array.fill(n-i)(" ") ++ Array.fill(i)("#")).mkString("")
    }
    out.foreach(println)
  }

  "minimax" should "find min and max 4 of 5" in {
    //396285104 573261094 759641832 819230764 364801279
    val arr:Array[Int] = Array(396285104,573261094,759641832,819230764,364801279)
    val res = miniMaxSum(arr)
    //2093989309 2548418794
    res._1 should be (2093989309L)
    res._2 should be (2548418794L)
  }

  def miniMaxSum(arr: Array[Int]): (Long,Long) = {
    var min = Long.MaxValue
    var max = 1L
    for (i <- 0 to arr.length-1)
    {
        val x = arr(i)
        val others = arr.diff(Array(x))
        val longSum: Long = others.foldLeft(0L)((c,r) => (c + r).toLong)
        if (longSum > max) max = longSum
        if (longSum < min) min = longSum
    }
    println(s"$min $max")
    (min,max)
  }

  "smallest missing" should "return smallest missing" in {
    val arr: Array[Int] = Array(-1000000,1000000)
    val res = smallestMissing(arr)
    res should be (1)
  }

  def smallestMissing(arr:Array[Int]):Int = {
    val positives = for (i <- 1 to arr.size+1) yield i
    val missingPositives = positives.diff(arr)
    val smallestMissing = missingPositives.head
    smallestMissing
  }

  "magicSquare" should "return lowest cost" in {
    val s:Array[Array[Int]] = Array(Array(4,9,2),Array(3,5,7),Array(8,1,5))
    val minCost = formingMagicSquare(s)
    minCost should be (1)
  }

  "cost" should "calculate cost" in {
    val orig = Array(Array(1,2,3),Array(4,5,6))
    val newish = Array(Array(1,9,3),Array(4,5,6))
    cost(orig,newish) should be (7)
    val o = Array(Array(1,2,3),Array(4,5,6))
    val n = Array(Array(1,9,3),Array(1,5,6))
    cost(o,n) should be (10)
    val o2 = Array(Array(1,2,3),Array(4,5,6))
    val n2 = Array(Array(1,2,3),Array(4,5,6))
    cost(o2,n2) should be (0)
  }

  def cost(orig: Array[Array[Int]], newSquare: Array[Array[Int]]) = {
    val origFlat = orig.flatten
    val newFlat = newSquare.flatten
    val theCost = origFlat.zipWithIndex.foldLeft(0)((tot,thing) => {
      val ind = thing._2
      val origVal = thing._1
      tot + Math.abs(origVal - newFlat(ind))
    })
    theCost
  }

  def formingMagicSquare(s: Array[Array[Int]]): Int = {

    val possibilities = Array(
      Array(Array(8, 1, 6), Array(3, 5, 7), Array(4, 9, 2)),
      Array(Array(6, 1, 8), Array(7, 5, 3), Array(2, 9, 4)),
      Array(Array(4, 9, 2), Array(3, 5, 7), Array(8, 1, 6)),
      Array(Array(2, 9, 4), Array(7, 5, 3), Array(6, 1, 8)),
      Array(Array(8, 3, 4), Array(1, 5, 9), Array(6, 7, 2)),
      Array(Array(4, 3, 8), Array(9, 5, 1), Array(2, 7, 6)),
      Array(Array(6, 7, 2), Array(1, 5, 9), Array(8, 3, 4)),
      Array(Array(2, 7, 6), Array(9, 5, 1), Array(4, 3, 8))
    )
    val costs = for (p<-possibilities) yield cost(s,p)
    costs.foldLeft(costs.head)((tot,r) => if (tot < r) tot else r)
  }

  "findDigits" should "find all divisor digits" in {
      val num: Int = 1012
      findDigits(num) should be (3)
  }

  def findDigits(n: Int): Int = {
    def isDivisor(d:Int, n:Int) = if (d == 0) false else (n % d == 0)
    n.toString.map(_.asDigit).filter(isDivisor(_,n)).length
  }

  case class PositionRange(pos:Int,atOrAbove:Int,upTo:Int)

  def derivePositionRangeSubOptimal(scoreboard: Array[Int]): Array[PositionRange] = {
    //could just get distinct values of scoreboard...?
    //assumes scoreboard is sorted.
    val disScores: Array[Int] = scoreboard.distinct //todo: speed it up - eliminate this step
    val positionRanges = disScores.zipWithIndex.map(x => {
      val pos = x._2
      val atOrAbove = x._1    //todo: if atOrAbove same as previous, pos is same as previous, otherwise pos is previousPos+1
      val upTo = if (pos==0) Integer.MAX_VALUE else disScores(pos-1)
      PositionRange(pos+1,atOrAbove,upTo)
    })
    val bottomRankUpTo = positionRanges(positionRanges.length-1).atOrAbove
    val bottomRank = PositionRange(positionRanges.length+1,0,bottomRankUpTo)
    positionRanges :+ bottomRank
  }

  def derivePositionRange(scoreboard: Array[Int]): Array[PositionRange] = {
    var positionRanges:scala.collection.mutable.ArrayBuffer[PositionRange] = ArrayBuffer[PositionRange]()
    scoreboard.zipWithIndex.foreach(x => {
      val ind = x._2
      val atOrAbove = x._1
      if (ind==0) {positionRanges += PositionRange(ind+1, x._1, Integer.MAX_VALUE)}
      else
        if (atOrAbove == positionRanges.last.atOrAbove) {
          //no need to add a range

      } else {
        positionRanges += PositionRange(positionRanges.last.pos+1, atOrAbove, positionRanges.last.atOrAbove )
      }
    })
    val bottomRankUpTo = positionRanges(positionRanges.length-1).atOrAbove
    val bottomRank = PositionRange(positionRanges.length+1,0,bottomRankUpTo)
    (positionRanges += bottomRank).toArray
  }

  "derivePositionRanges" should "derive position ranges from a scoreboard" in {
    val scoreboard = Array(100,100,50,40,40,20,10)
    val expectedPR = Array(
      PositionRange(1,100,Int.MaxValue),
      PositionRange(2,50,100),
      PositionRange(3,40,50),
      PositionRange(4,20,40),
      PositionRange(5,10,20),
      PositionRange(6,0,10)
    )
    derivePositionRange(scoreboard) should be (expectedPR)
  }

  def findPositionInScoreboard(givenScoreboard: Array[Int], newScore: Int):Int = {
    val positions = derivePositionRange(givenScoreboard)
    val newPosition = positions.filter(pr => newScore >= pr.atOrAbove && newScore < pr.upTo)(0)
    newPosition.pos
  }

  "findPositionInScoreBoard" should "find position for a score" in {
    val givenScoreboard = Array(100,100,50,40,40,20,10)
    var newScore = 6
    findPositionInScoreboard(givenScoreboard, newScore) should be (6)
    newScore = 106
    findPositionInScoreboard(givenScoreboard, newScore) should be (1)
    newScore = 55
    findPositionInScoreboard(givenScoreboard, newScore) should be (2)
  }

  def findPositionAndUpdatedScoreBoard(scoreBoard: Array[Int], newScore: Int): (Int, Array[Int]) = {
    var positionRanges:scala.collection.mutable.ArrayBuffer[PositionRange] = ArrayBuffer[PositionRange]()
    var newScorePos:Int = Int.MaxValue
    scoreBoard.zipWithIndex.foreach(x => {
      val ind = x._2
      val atOrAbove = x._1
      val currAtOrAbove = x._1
      val prevAtOrAbove = if (ind == 0) Int.MaxValue else positionRanges(ind-1).atOrAbove
      val prevRank = if (ind == 0) 1 else positionRanges(ind-1).pos
      val prevUpTo = if (ind == 0) Int.MaxValue else positionRanges(ind-1).upTo
      //if new inbetween curr and previous => append new ranking for new
      if (newScore > currAtOrAbove && newScore < prevAtOrAbove) {
        newScorePos = prevRank + 1
        positionRanges += PositionRange(prevRank + 1, newScore, prevUpTo)
        positionRanges += PositionRange(prevRank + 2, currAtOrAbove, newScore)
      } else {
        //if curr same as previous => skip else append new ranking for curr
        if (currAtOrAbove  == prevAtOrAbove) {
          if (newScore == currAtOrAbove) newScorePos = prevRank
        }
        else {
          positionRanges += PositionRange(prevRank + 1, currAtOrAbove, prevAtOrAbove)
        }
      }


      if (ind==0) { //FIRST POSITION!
        positionRanges += PositionRange(ind+1, atOrAbove, Integer.MAX_VALUE)
        newScore match {
          case n if n > atOrAbove => {
            newScorePos = 1
            positionRanges += PositionRange(1, newScore, Integer.MAX_VALUE)
            positionRanges += PositionRange(2, atOrAbove, newScore)
          }
          case n if n == atOrAbove => {
            newScorePos = 1
            positionRanges += PositionRange(1, atOrAbove, Integer.MAX_VALUE)
          }
          case _ =>
        }
      }
      else {  //ANY OTHER POSITION
        newScore match {
          case n if n > atOrAbove => {
            newScorePos = positionRanges.last.pos+1
            positionRanges += PositionRange(positionRanges.last.pos+1, newScore, positionRanges.last.upTo)
            positionRanges += PositionRange(positionRanges.last.pos+2, atOrAbove, newScore)
          }
          case n if n == atOrAbove => {
            newScorePos = positionRanges.last.pos+1
          }
          case _ =>
        }
      }
    })
    val bottomRankUpTo = positionRanges(positionRanges.length-1).atOrAbove
    val bottomRank = PositionRange(positionRanges.length+1,0,bottomRankUpTo)
    val newScoreBoard = (positionRanges += bottomRank).toArray.map(x => x.atOrAbove)
    (newScorePos, newScoreBoard)

  }

  def climbingLeaderboard(scores: Array[Int], alice: Array[Int]): Array[Int] = {

    def looper(scores: Array[Int], scoreBoard: Array[Int], positions: Array[Int]): Array[Int] = {
      val position = findPositionInScoreboard(scoreBoard, scores.head)  //todo: can these 2 steps be combined to avoid traversing the scoreboard twice?
      val newScoreBoard = insertInDescOrder(scoreBoard, scores.head)
      if (scores.tail.isEmpty) {
        positions :+ position
      } else {
        looper(scores.tail, newScoreBoard, positions :+ position)
      }
    }
    looper(alice, scores, Array())
  }


  def climbingLeaderboardFaster(scores: Array[Int], alice: Array[Int]): Array[Int] = {

    def looper(scores: Array[Int], scoreBoard: Array[Int], positions: Array[Int]): Array[Int] = {
      val (position,newScoreBoard) = findPositionAndUpdatedScoreBoard(scoreBoard, scores.head)
      if (scores.tail.isEmpty) {
        positions :+ position
      } else {
        looper(scores.tail, newScoreBoard, positions :+ position)
      }
    }
    looper(alice, scores, Array())
  }

  "climbing the leaderboard" should "climb" in {
    //given
    var scoreboard = Array(100,100,50,40,40,20,10)
    var alice = Array(5,25,50,120)
    //when
    var alicesRankings = climbingLeaderboardFaster(scoreboard, alice)
    //then
    alicesRankings should be (Array(6,4,2,1))

    //given
    scoreboard = Array(100,90,90,80,75,60)
    alice = Array(50,65,77,90,102)
    //when
    alicesRankings = climbingLeaderboardFaster(scoreboard, alice)
    //then
    alicesRankings should be (Array(6,5,4,2,1))

  }

  def insertInDescOrder(arr:Array[Int], newbie: Int) = {
    var i = 0
    var found = false
    while (!found && i <= arr.length -1) {
      if (arr(i) > newbie) i = i+1 else found = true
    }
    arr.slice(0,i) ++ Array(newbie) ++ arr.slice(i,arr.length)
  }

  "insertInDescOrder" should "insert in desc order" in {
    val ordered = Array(10,8,4,3)
    val newbie = 5
    insertInDescOrder(ordered,newbie) should be (Array(10,8,5,4,3))
  }


  "extra long factorials" should "work" in {
   val input = 25
    val result = extraLongFactorials(input)
    //result should be (BigInt(15511210043330985984000000))
    println(result)
  }

  def extraLongFactorials(n: Int) = {
    val nums = scala.List.range(n,0,-1)
    nums.foldLeft(BigInt(1))((a:BigInt,b:Int) => a * b)
  }

  "nonDivisibleSubset" should "find maximal subset" in {
    val max = Int.MaxValue
    val arr: Array[Int] = Array(1,7,2,4)
    val k:Int= 3
    val res = nonDivisibleSubset(k,arr)
    res should be (3)

    val arr2 = Array(278,576,496,727,410,124,338,149,209,702,282,718,771,575,436)
    val k2 = 7
    val res2 = nonDivisibleSubset(k2,arr2)
    res2 should be (11)
  }
  def nonDivisibleSubset(k: Int, s: Array[Int]) = {

    def getNextPair(curr:Long, incoming: Array[Int], buffer: Array[(Long,Long,Boolean)]):Array[(Long,Long, Boolean)] = {
      if (incoming.tail.isEmpty) {
        buffer :+ (curr, incoming.head.toLong, (curr + incoming.head.toLong) % k == 0)
      } else {
        val buf: Array[(Long, Long, Boolean)] = (for (i <- 0 to incoming.length-1) yield (curr, incoming(i).toLong,(curr+incoming(i)) % k == 0 )).toArray
        getNextPair(incoming.head,incoming.tail, buffer ++ buf)
      }
    }

    val subsets = getNextPair(s.head, s.tail, Array())
    subsets.foreach(println)
    subsets.filter(x => !x._3)
  }

  def folderChecker() = {
    val arr = Array(1,7,2,4)
    val thing = arr.foldLeft(Array(arr.head))((c,r) => {
      var disqualified = false
      var i=0
      while (!disqualified && i < c.length) {
        if (r + c(i) % 3 != 0) {
          disqualified = true
        }
      }
      if (disqualified) c else c :+ r
    })

  }


  it should "find Some(null)" in {
    val someNull = Some(null)
    val someThing = Some("thing")
    var matchThis = someNull
    someNull match {
      case Some(null) => println("found Some(null)")
      case o: Option[String] => println("found Some(string)")
    }
    someThing match {
      case Some(null) => println("found Some(null)")
      case o: Option[String] => println("found Some(string)")
    }
  }

  "appendAndDelete" should "work" in {
    var s = "ashley"
    var t = "ash"
    var k = 2
//    appendAndDelete(s,t,k) should be ("No")
//    s = "hackerhappy"
//    t = "hackerrank"
//    k = 9
//    appendAndDelete(s,t,k) should be ("Yes")
//    s = "aba"
//    t = "aba"
//    k = 7
//    appendAndDelete(s,t,k) should be ("Yes")
//    s = "hackerhappy"
//    t = "hackerrank"
//    k = 8
//    appendAndDelete(s,t,k) should be ("No")
    s = "abcd"
    t = "abcdert"
    k = 10
    appendAndDelete(s,t,k) should be ("No")
  }

  def appendAndDelete(s: String, t: String, k: Int): String = {
    //find first commonUpToIndex. how many chars of s left + how many chars of t left should be <= k
    var counter = 0
    var same = true
    while (same && counter < t.length && counter < s.length) {
      if (s.charAt(counter) != t.charAt(counter)) same = false
      counter += 1
    }
    val commonIndex = counter -1 //TODO: if counter == s.length
    val sRemaining = if(counter == s.length) 0 else {if (s.length - commonIndex > 0) s.length - commonIndex else 0}
    val tRemaining = if (t.length - commonIndex > 0) t.length - commonIndex else 0
    if (sRemaining + tRemaining <= k) "Yes" else "No"
  }
}
