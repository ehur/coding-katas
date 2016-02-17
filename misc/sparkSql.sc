import com.cj.protocol.tracking.Action
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

import org.apache.spark.sql.SQLContext
import org.apache.spark.sql.SQLImplicits
import org.apache.spark.sql.SQLContext._
import org.apache.spark.sql.DataFrame
import org.joda.time.{DateTimeZone, DateTime}

def createTestActionFile(sc:SparkContext) = {
  val beginEventTime = new DateTime(2015,4,22,0,0,0)
  val endEventTime = new DateTime(2015,9,22,0,0,0)

  val action1 = Action.newBuilder(new Action).setEventId("event1").setEventTimestamp(beginEventTime.getMillis).build()
  val action2 = Action.newBuilder(new Action).setEventId("event2").setEventTimestamp(endEventTime.getMillis).build()

  val eventsToWrite: RDD[Action] = sc.parallelize(Seq(action1,action2))
  eventsToWrite.write.parquet("sparkSqlActions.parquet")
  eventsToWrite
}

//def keyByTimeAtStartOfDat(struct:DataFrame) = {
//  timeAtStartOfDay(struct("eventTimestamp")),struct("count")
//}
def timeAtStartOfDay(timeinMillis:Long) = {
  new DateTime(timeinMillis).withZone(DateTimeZone.UTC).withTimeAtStartOfDay().getMillis
}

val path = "/user/cjhadoop/datawarehouse/raw/ts/actions/102/byLogDate/20150827/000000"
val sc = new SparkContext(new SparkConf())
val sqlContext = new SQLContext(sc)
val df=sqlContext.read.parquet(path)
//val rddActions:RDD[Action]=sqlContext.read.parquet(path)
df.show()
df.printSchema()
val dfGroupBy = df.groupBy("eventTimestamp").count()
df.registerTempTable("Actions")
val sqlGroupBy=sqlContext.sql("select eventTimestamp, count(*) FROM Actions group by eventTimestamp")

val dfAtStartOfDay = dfGroupBy.map(r => (timeAtStartOfDay(r(0).asInstanceOf[Long]),r(1).asInstanceOf[Long]))
val sqlAtStartOfDay = sqlGroupBy.map(r => (timeAtStartOfDay(r(0).asInstanceOf[Long]),r(1).asInstanceOf[Long]))
//res7: Array[org.apache.spark.sql.Row] = Array([1440646076184,1], [1440646114472,1], [1440646136312,1], [1440646152584,1], [1440646178872,1], [1440646258784,1], [1440646277272,1], [1440646305400,1], [1440639117296,1], [1440639140872,1])
val groupedDf = dfAtStartOfDay.reduceByKey((r,c) => r + c)
//res6: Array[(Long, Long)] = Array((1440547200000,1216), (1440633600000,1497079))
val groupedSql = sqlAtStartOfDay.reduceByKey((r,c) => r + c)
//res7: Array[(Long, Long)] = Array((1440547200000,1216), (1440633600000,1497079))