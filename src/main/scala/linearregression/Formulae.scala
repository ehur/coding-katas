package linearregression

import math.Matrix
import Matrix.{MatrixRow, Matrix}

object Formulae {

  def computeCost(X:Matrix,
                  y:MatrixRow,
                  theta:MatrixRow) : Double = {
    val m = y.size
    val thetaMatrix: Matrix = List(theta)
    val h=Matrix.multiply(thetaMatrix,Matrix.transpose(X)).head  //1 x 2 * 2 x m  -> 1 x m
    val hypError = Matrix.subtract(h,y)
    val squaredHypError = Matrix.elementwiseProduct(hypError,hypError)
    (1d/(2*m)) * Matrix.sum(squaredHypError)
  }

  def gradientDescent(xMatrix:Matrix, y:MatrixRow, theta:MatrixRow, alpha:Double, numIters:Int) : (MatrixRow, List[Double]) = {
      val m = y.size
      var jHistory = List[Double]()
      var thetaNew = List[Double]()
//      for ( i <- 0 until numIters) {
//           for (j <- 0 until theta.size) {
//             val firstMultiplier = Matrix.subtract(Matrix.transpose(Matrix.multiply(List(theta),xMatrix)),List(y))
//             val secondMultiplier = xMatrix map{m:MatrixRow => m(j)}
//             val deriv = Matrix.sum( Matrix.sumProd(firstMultiplier,secondMultiplier)   )
//             thetaNew = thetaNew :+ (theta(j) - alpha * (1/m) * deriv  )
//           }
//        jHistory = jHistory :+ computeCost(xMatrix,y,thetaNew)
//      }
    (jHistory, thetaNew)
  }
}
