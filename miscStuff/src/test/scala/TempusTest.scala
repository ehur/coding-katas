import org.joda.time.{DateTime, DateTimeZone, Days, Hours}
import org.scalatest.{FlatSpec, Matchers}
import java.sql.Timestamp


class TempusTest extends FlatSpec with Matchers {

  "The last hour" should "work" in {
    val rightNow = new DateTime().withZone(DateTimeZone.UTC)
    println(s"hour of day is ${rightNow.getHourOfDay()}")
    val upToToday = new DateTime().withZone(DateTimeZone.UTC).withTimeAtStartOfDay()
    println(s"hour of day for upToYesterday is ${upToToday.getHourOfDay}")
    val between = Hours.hoursBetween(rightNow,upToToday).getHours
//    val pathCompleteMonths = for
  }


  "if and cases" should "work for case statements" in {

    "ssssStart" match {
      case s: String if s.startsWith("ssss") => println(s"$s starts withs ssss")
      case _  => println("it does not")
    }


    "sStart" match {
      case s: String if s.startsWith("ssss") => println(s"$s starts withs ssss")
      case _  => println("it does not")
    }
  }
}
