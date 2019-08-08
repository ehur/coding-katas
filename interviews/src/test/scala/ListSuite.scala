import org.scalatest.{FlatSpec, Matchers}

import scala.util.Random

class ListSuite extends FlatSpec with Matchers {

  def getLeastTwo(list:List[Int]): (Int,Int) = {
    val leastOne = list.reduceLeft(_ min _)
    val nextLeastOne = list.reduceLeft((a,b) =>
      {
        if (a == leastOne)  //if a or b is same as leastOne
          b
        else
          if (b == leastOne)
            a
          else
            if (a < b)
              a
            else
              b
      }
    )
    (leastOne,nextLeastOne)
  }

  "thing" should "find smallest 2 numbers in list" in {
    //given a list
    val randomList = List.fill(20)(Random.nextInt(100))
    println(randomList)
    val theList = List(4, 6, 7, 2, 8, 9)
    //when
    val (least1,least2) = getLeastTwo(theList)
    //then
    least1 should be (2)
    least2 should be (4)
  }

  "thing" should "find smallest 2 numbers in random list" in {
    //given a list
    val randomList = List.fill(20)(Random.nextInt(1000))
    println(randomList)
    //when
    val (least1,least2) = getLeastTwo(randomList)
    //then
    least1 should be (randomList.min)
    val filteredList = randomList.filter(_ != least1)
    least2 should be (filteredList.min)
    println((least1,least2))
//    least2 should be (4)
  }



}
