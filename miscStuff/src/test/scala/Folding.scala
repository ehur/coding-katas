import org.scalatest.{FlatSpec, Matchers}
import java.util

class Folding extends FlatSpec with Matchers{

  "the thing" should "fold along the lines" in {
    val seq = Seq("5","6","7","8")
    val foldedSeq = seq.foldLeft("")((c,r) => s"$c+$r").substring(1)
    foldedSeq should be ("5+6+7+8")
  }

  "simple array" should "fold" in {
    val ar:Array[Int] = Array(1,2,3)
    val sum = ar.toList.foldLeft(0)((c,r) => c+r )
    print(sum)
  }

}
