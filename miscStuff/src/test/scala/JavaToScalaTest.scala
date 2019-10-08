import org.scalatest.{FlatSpec, Matchers}

class JavaToScalaTest extends FlatSpec with Matchers {

  "Some java Integer with null" should "become None" in {
    val javaInt: java.lang.Integer = null
    val scalaOptInt: Option[Int] = Some(javaInt)
    scalaOptInt should be (None)

  }

}
