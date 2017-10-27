package scala

import org.scalatest.{FlatSpec, Matchers}

class Folding extends FlatSpec with Matchers{

  "the thing" should "fold along the lines" in {
    val seq = Seq("5","6","7","8")
    val foldedSeq = seq.foldLeft("")((c,r) => s"$c+$r").substring(1)
    foldedSeq should be ("5+6+7+8")
  }
}
