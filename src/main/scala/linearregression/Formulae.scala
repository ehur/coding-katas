package linearregression

import math.Matrix
import Matrix.{MatrixRow, MxNMatrix}

object Formulae {

  def computeCost(X:MxNMatrix,
                  y:MatrixRow,
                  theta:MatrixRow) : Double = {
    val m = y.size
    val thetaMatrix: MxNMatrix = List(theta)
    val h=Matrix.multiply(thetaMatrix,Matrix.transpose(X)).head  //1 x 2 * 2 x m  -> 1 x m
    val hypError = Matrix.subtract(h,y)
    val squaredHypError = Matrix.elementwiseProduct(hypError,hypError)
    (1d/(2*m)) * Matrix.sum(squaredHypError)
  }

  def gradientDescent(xMatrix:MxNMatrix, y:MatrixRow, theta:MatrixRow, alpha:Double, numIters:Int) : (MatrixRow, List[Double]) = {
      val m = y.size
      var jHistory:List[Double] = Nil
      var thetaGrad:List[Double] = theta
      for ( i <- 0 until numIters) {
        var thetaNew:Array[Double] = new Array[Double](theta.size)
        for (j <- 0 until theta.size) {
             val thetaTransposeX = Matrix.multiplyVector(thetaGrad,Matrix.transpose(xMatrix)) //assumes 1 x n * n x o
             val firstMultiplier = Matrix.subtract(thetaTransposeX,y)
             val secondMultiplier = xMatrix map{m:MatrixRow => m(j)}
             val deriv = alpha * (1d/m) * Matrix.sumProd(firstMultiplier,secondMultiplier)
             thetaNew(j) =  thetaGrad(j) - deriv
           }
           thetaGrad = thetaNew.toList
           jHistory = jHistory :+ computeCost(xMatrix,y,thetaGrad) //TODO - cost is increasing all the time. Some calculation is wrong
      }
    val res = (jHistory, thetaGrad)
    res
  }
}
