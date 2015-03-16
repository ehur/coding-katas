package linearregression

import breeze.linalg.{*, DenseMatrix, DenseVector, sum}
import breeze.stats.{mean, stddev}

object Formulae {

  def computeCostBreeze(X:DenseMatrix[Double],
                        y:DenseMatrix[Double],
                        theta:DenseMatrix[Double]) : Double= {
    val m=y.cols
    val hyp:DenseMatrix[Double] = theta * X.t
    val hypError:DenseMatrix[Double] = hyp - y
    val squaredHypError = hypError :* hypError
    val retVal = (1d/(2*m)) * sum(squaredHypError)
    retVal
  }

  def gradientDescentBreeze(X:DenseMatrix[Double],
                        y:DenseMatrix[Double],
                        theta:DenseMatrix[Double],
                        alpha:Double, numIters:Int) : (DenseMatrix[Double],DenseMatrix[Double]) = {
      val m: Integer = y.cols
      val numFeatures = X.cols
      var jHistArray = new Array[Double](numIters)
      var thetaTemp = theta
      for (iter <- 0 until numIters){
        var thetaNew = new DenseMatrix[Double](1,numFeatures)
        for (featureNum <- 0 until numFeatures) {
          val derivFirst = (thetaTemp * X.t) - y
          val derivSecond = X(::,featureNum)
          val resy= derivFirst * derivSecond
          val deriv:Double = sum(derivFirst * derivSecond)
//          println("feature: " + featureNum + " deriv: " + deriv)
          thetaNew(0,featureNum) = thetaTemp(0,featureNum) - (alpha/m) * deriv;
        }
      thetaTemp = thetaNew
      jHistArray(iter) = computeCostBreeze(X,y,thetaTemp)
      }
    (new DenseMatrix[Double](1, numIters, jHistArray), thetaTemp)
  }

  def featureNormalize(xMatrix: DenseMatrix[Double]) : (DenseMatrix[Double],DenseVector[Double],DenseVector[Double]) = {
//    var mu = new Array[Double](xMatrix.cols)
//    var sigma = new Array[Double](xMatrix.cols)
    var xNorm = DenseMatrix.zeros[Double](xMatrix.rows,xMatrix.cols)
    val mu: DenseMatrix[Double] = mean(xMatrix(::,*))
    val sigma: DenseMatrix[Double] = stddev(xMatrix(::,*))
    for (featureNum <- 0 until xMatrix.cols) {
        if (sigma(0, featureNum) == 0)
          xNorm(::, featureNum) := 0.0
        else
          xNorm(::, featureNum) := (xMatrix(::, featureNum) - mu(0,featureNum)) :/ sigma(0,featureNum)
    }
    (xNorm,mu(0,::).t,sigma(0,::).t)
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

}
