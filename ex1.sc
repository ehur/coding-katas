val xs = List(1,2,3,4)
val h = List(List(1,2,3,4),List(5,4,3,2))
val thingy = h.map(_.head)
val thingy2 =  h.map(_.tail)

val source = scala.io.Source.fromFile("/home/lhurley/git/coding-katas/ex1data1.txt")
val lines = source.getLines().toList
def getCol(x: scala.collection.immutable.List[String], colIndex: Int) = {
  x map {_.split(",").toList} map { a : scala.collection.immutable.List[String] => a(colIndex)}
}
val X= getCol(lines,0) map {x:String => x.toDouble}
val y = getCol(lines,1) map {x:String => x.toDouble}
type MatrixRow = scala.collection.immutable.List[Double]
type Matrix = scala.collection.immutable.List[MatrixRow]
def sum(xs: scala.collection.immutable.List[Double]): Double = {
  def inner(xs: scala.collection.immutable.List[Double], accum: Double): Double = {
    xs match {
      case x :: tail => inner(tail, accum + x)
      case Nil => accum
    }
  }
  inner(xs, 0)
}
def transpose(m:Matrix):Matrix = {
  if (m.head.isEmpty) Nil else m.map(_.head) :: transpose(m.map(_.tail))
}

def dotProd(v1:MatrixRow,v2:MatrixRow) : (MatrixRow) = {
  v1.zip( v2 ).map{t:(Double,Double) => t._1 * t._2 }
}
def sumProd(v1:MatrixRow,v2:MatrixRow) : (Double) = {
  v1.zip( v2 ).map{t:(Double,Double) => t._1 * t._2 }.reduceLeft(_ + _)
}

def minuss(v1:MatrixRow,v2:MatrixRow) : (MatrixRow) = {
  v1.zip( v2 ).map{t:(Double,Double) => t._1 - t._2 }
}

def addOnes(matrix:Matrix) : Matrix = {
  val one: Double = 1
  matrix map {row:MatrixRow => one :: row}
}
val newX=transpose(List(X))
val newXWithOnes=addOnes(newX)

def multiplied(m1:Matrix,m2:Matrix) = {
  // m x n * n x o -> m x o
  if (m1.head.size != m2.size) //n's don't match { {}
    throw new IllegalArgumentException("matrix1 cols: " + m1.head.size + " cannot be multiplied with matrix2 rows: " + m2.size)
  else {
    for (m1row <- m1) yield
      for (m2col <- transpose(m2)) yield
        sumProd(m1row, m2col)
  }
}

def minused(m1:Matrix,m2:Matrix)  {
  for(m1row <- m1) yield
    for(m2row <- m2) yield
      minuss(m1row,m2row)
}

def computeCost(X:Matrix,
                y:MatrixRow,
                theta:MatrixRow) : Double = {
  val m = y.size
  val thetaMatrix: Matrix = List(theta)
  val thetaT = transpose(thetaMatrix)
  val XT = transpose(X)
  val h=multiplied(thetaT,XT).head  //1 x 2 * 2 * m  -> 1 x m
  val hMinusy = minuss(h,y)
  val squared = dotProd(hMinusy,hMinusy)
  (1/(2*m)) * sum(squared)
}

val theta:(MatrixRow)= List(0,0)
//val J=computeCost(X,y,theta)

//val  testx : MatrixRow = List(1,2,3,4)
//val testy: MatrixRow = List(2,2,2,2)
//val prod = dotProd(testx,testy)
//val mtinus = minuss(testx,testy)
//val testr: Matrix = List(List(1,2,3),List(4,4,4),List(1,1,1),List(2,2,2))
//val multipl = multiplied(List(testx),testr)
//val goodmulti = multiplied(List(testx),transpose(List(testy)))
//val badmultipl = multiplied(List(testx),List(testy))
//
source.close()