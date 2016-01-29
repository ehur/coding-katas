
import com.cj.protocol.actionprocessing.SuccessfulAction
import org.joda.time.{DateTimeZone, DateTime}
import com.cj.sparquet.avro._

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}


def dayAtMidnight(k:scala.Long):String = {
  val p = "%02d"
  val day = new DateTime(k).withZone(DateTimeZone.UTC).withTimeAtStartOfDay()
  val formattedDay = s"${day.getYear}${p.format(day.getMonthOfYear)}${p.format(day.getDayOfMonth)}"
  formattedDay
}

val testDate="20150923" //bucket currently with event times in 2 different days
val path=s"datawarehouse/clean/ap/successfulActions/byEventTimeDay/$testDate"

val pathToRaw="datawarehouse/raw/ap/successfulActions/byLogDate/2015092*"

val sc = new SparkContext(new SparkConf())
val results: RDD[SuccessfulAction]=sc.parquetFile[SuccessfulAction](pathToRaw)

val groupedResults=results.map(x => (x.getAction.getActionTime, x)).groupByKey()
val theKeys=groupedResults.keys
val days=theKeys.map(k=> {
  val p="%02d"
  val day = new DateTime(k).withZone(DateTimeZone.UTC).withTimeAtStartOfDay()
  s"${day.getYear}${p.format(day.getMonthOfYear)}${p.format(day.getDayOfMonth)}"
})
days.collect().toSet //before bucketer fix, there are 2 values here, after bucketer fix, should be just 1