import org.joda.time.Days
import org.joda.time.format.DateTimeFormat
import org.scalatest.{FlatSpec, Matchers}


class DateTimeCalStuff extends FlatSpec with Matchers {

  "start-date end-date" should "find days between" in {
    val startDate = Option("2017-01-01")
    val endDate = Option("2017-01-31")
    val result = daysBetween(startDate.get, endDate.get)
    result should be(30)
    val daysRequested = (startDate,endDate) match {
      case (Some(start),Some(end)) => daysBetween(start, end)
      case _ => 0
    }
    daysRequested should be(30)
  }

  val daysBetween: (String,String) => Int = (fromDate,toDate) => {
    val fmt = DateTimeFormat.forPattern("y-M-d")
    val parsedFrom = fmt.parseDateTime(fromDate)
    val parsedTo = fmt.parseDateTime(toDate)
    val daysBetween = Days.daysBetween(parsedFrom,parsedTo)
    daysBetween.getDays
  }

}
