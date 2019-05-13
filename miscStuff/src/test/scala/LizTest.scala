import java.time.Instant

import org.scalacheck.{Arbitrary, Gen}
import org.scalatest.FlatSpec
import org.scalatest.prop.PropertyChecks

class LizTest extends FlatSpec with PropertyChecks {

  it should "make sense" in {
    //List(0)
    def genPubIds: Gen[List[Long]] = Gen.containerOfN[List,Long](1, Gen.oneOf(List(0L)))

    //List(33, 44, 88, 11, 99)
    def genAdvIds: Gen[List[Long]] = Gen.containerOfN[List,Long](5, Gen.oneOf(List(11,22,33,44,55,66,77,88,99,100)))

    //List(7445, 2858, 2916), List()
    def genAdvIdsVar: Gen[List[Long]] = Gen.containerOf[List, Long](Gen.choose(1L, 10000L))

    forAll(genPubIds, genAdvIds, genAdvIdsVar, sizeRange(8))
      {(pubIds:List[Long],advIds:List[Long], advIdsVar:List[Long]) => {
        println(s"pubIds: $pubIds - advIds: $advIds - advIdsVar: $advIdsVar")
      }}

  }

  it should "work with options" in {
    //List(0)
    def genPubIds = Gen.option(Gen.containerOfN[List,Long](1, Gen.oneOf(List(0L))))

    //List(33, 44, 88, 11, 99)
    def genAdvIds = Gen.option(Gen.containerOfN[List,Long](5, Gen.oneOf(List(11,22,33,44,55,66,77,88,99,100))))

    //List(7445, 2858, 2916), List()
    def genAdvIdsVar = Gen.option(Gen.containerOf[List, Long](Gen.choose(1L, 10000L)))

    forAll(genPubIds, genAdvIds, genAdvIdsVar, sizeRange(8))
    {(pubIds:Option[List[Long]],advIds:Option[List[Long]], advIdsVar:Option[List[Long]]) => {
      println(s"pubIds: $pubIds - advIds: $advIds - advIdsVar: $advIdsVar")
    }}

  }

  it should "work with instants" in {

    val rangeStart = Instant.parse("2015-01-01T00:00:00Z")
    val rangeEnd = Instant.now
    def genInstant: Gen[Instant] = Gen.choose(rangeStart.toEpochMilli, rangeEnd.toEpochMilli)
      .map(i => Instant.ofEpochMilli(i))
    forAll (genInstant) {(ins:Instant) =>println(ins)}

  }
}
