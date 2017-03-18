val listy = List(0.0,0.0)
val list2= listy :+ 5.6
var thrifty:List[Double] = Nil :: List(2.4)
val strings=List("first\tsecond\tthe one with the ipad\tthe other ones","first\tsecond\tthe one with the opad\tthe other ones","first\tddsecond\tthe one with the ipad\tthe other ones")
val out = for (lister <- strings.map(x => x.split("\t").toList)) yield lister(2)
"iPad".toLowerCase
import breeze.linalg.{*, sum, DenseVector, DenseMatrix}
import breeze.stats.{mean,stddev}
val x=new DenseMatrix[Double](5,2,Array(4.0,4.0,4.0,4.0,4.0,7.0,6.0,7.0,8.0,9.0))
val muByCol:DenseMatrix[Double]=mean(x(::,*)) //mean for each column (mean of all row values)
val muByRow:DenseVector[Double]=mean(x(*,::)) //mean for each row (mean of all col values)
val sigmaByCol:DenseMatrix[Double]=stddev(x(::,*))
muByCol(0,::).t
x.t
val xNormFirst = x.t(::,*)-muByCol(0,::).t

val xNormSecond = xNormFirst(::,*)/sigmaByCol(0,::).t
//val xNorm = xNormSecond.t