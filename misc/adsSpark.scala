import com.cj.protocol.ads.Ad
import com.cj.sparquet.avro._

val pathToAds = "/user/cjhadoop/datawarehouse/raw/ads/ad/"
val adsMaybe=sc.parquetFile[Ad](pathToAds)
adsMaybe.count //returns res0: Long = 225921


