package generics

import org.scalatest.{FlatSpec, Matchers}

import scala.reflect._
import scala.reflect.runtime.{universe => ru}

class GenericSuite extends FlatSpec with Matchers {

  "TypeTags" should "be available" in {

    val l = List(1,2,3)

    val theType = getTypeTag(l).tpe

    theType should be (ru.typeOf[List[Int]])

  }

  it should "work for other stuff" in {
    val words:String = "this is a string"
    val theType = getTypeTag(words).tpe
    theType should be (ru.typeOf[String])
  }

  "ClassTags" should "be available" in {

    val ct = classTag[String]

    ct should be ("")

  }

  def getTypeTag[T: ru.TypeTag](obj: T) = ru.typeTag[T]
}