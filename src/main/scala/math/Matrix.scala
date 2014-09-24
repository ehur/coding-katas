package math

object Matrix extends App {

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

  def elementwiseProduct(v1:MatrixRow,v2:MatrixRow) : (MatrixRow) = {
    v1.zip( v2 ).map{t:(Double,Double) => t._1 * t._2 }
  }
  def sumProd(v1:MatrixRow,v2:MatrixRow) : (Double) = {
    v1.zip( v2 ).map{t:(Double,Double) => t._1 * t._2 }.reduceLeft(_ + _)
  }

  def subtract(v1:MatrixRow,v2:MatrixRow) : (MatrixRow) = { //todo: add IllegalArgumentException handling
    v1.zip( v2 ).map{t:(Double,Double) => t._1 - t._2 }
  }

  def multiply(m1:Matrix,m2:Matrix) = {
    // m x n * n x o -> m x o
    if (m1.head.size != m2.size) //n's don't match { {}
      throw new IllegalArgumentException("matrix1 cols: " + m1.head.size + " cannot be multiplied with matrix2 rows: " + m2.size)
    else {
      for (m1row <- m1) yield
        for (m2col <- transpose(m2)) yield
          sumProd(m1row, m2col)
    }
  }

  def matrixSize(m:Matrix) : (Int,Int) = {
    val rows = m.size
    val cols = m.head.size
    return (rows,cols)
  }


//  def subtract(m1:Matrix,m2:Matrix): Matrix = {
//    m1
////    for(m1row <- m1) yield
////      for(m2row <- m2) yield
////        subtract(m1row,m2row)
//  }
}
