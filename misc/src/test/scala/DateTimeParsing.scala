package scala

import org.joda.time.format.DateTimeFormat
import org.scalatest.{FlatSpec, Matchers}

class DateTimeParsing extends FlatSpec with Matchers {

  "[01/Oct/2017:00:00:01 -0700]" should "parseMonth" in {
    val dateTime = "[01/Oct/2017:00:00:01 -0700]"
    println (dateTime)
    val fmt = DateTimeFormat.forPattern("[d/MMM/y:H:m:s Z]")
    val parsed = fmt.parseDateTime(dateTime)
    val yyyyMm = s"${parsed.getYear}${parsed.getMonthOfYear}"
    yyyyMm should be ("201710")
  }

}
