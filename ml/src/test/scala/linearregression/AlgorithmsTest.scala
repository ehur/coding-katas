package test.scala.linearregression

import java.io.File

import breeze.linalg._
import linearregression.linearregression
import linearregression.linearregression.Formulae
import main.scala
import main.scala.linearregression.Formulae
import org.scalatest._
import breeze.stats.{mean,stddev}

class AlgorithmsTest extends FlatSpec with ShouldMatchers {

  "computeCostBreeze" should "calculate correctly" in {
    val xy:DenseMatrix[Double] = csvread(new File("/home/lhurley/git/coding-katas/ex1data1.txt"))
    val onesM = DenseMatrix.ones[Double](xy.rows,1)
    val xCol = xy(::,0).toDenseMatrix.t
    val xMatrix = DenseMatrix.horzcat(onesM,xCol) //col of ones and first col of file data - X has now 2 features
    val y = xy(::,1).toDenseMatrix               // y is a row vector with entries for each example
    val theta = DenseMatrix.zeros[Double](1,xMatrix.cols) //theta is a row vector(DenseMatrix with 1 row here) with entries for each feature - we have 2 features
    val J=Formulae.computeCostBreeze(xMatrix,y,theta)

    J should be (32.072733877455654d)
    println("Cost is: " + J)
  }

  "gradientDescentBreeze" should "descend" in {
    val xy:DenseMatrix[Double] = csvread(new File("/home/lhurley/git/coding-katas/ex1data1.txt"))
    val onesM = DenseMatrix.ones[Double](xy.rows,1)
    val xCol = xy(::,0).toDenseMatrix.t

    val xMatrix = DenseMatrix.horzcat(onesM,xCol) //col of ones and first col of file data - X has now 2 features
    val y = xy(::,1).toDenseMatrix               // y is a row vector with entries for each example
    val theta = DenseMatrix.zeros[Double](1,xMatrix.cols) //theta is a row vector(DenseMatrix with 1 row here) with entries for each feature - we have 2 features
    //    val theta = new DenseMatrix[Double](1,2,Array(0.1,0.6))

    val alpha: Double = 0.01d
    val numIters: Int = 1500

    val (jHistory, thetaNew):(DenseMatrix[Double],DenseMatrix[Double]) = Formulae.gradientDescentBreeze(xMatrix,y,theta,alpha,numIters)

    jHistory.size should be(numIters)
    thetaNew(0,0) should be(-3.63029143940436)
    thetaNew(0,1) should be(1.166362350335582)
    println ("theta new is: " + thetaNew(0,0) + ", " + thetaNew(0,1))
    println("==============================================")
    for (i <- 0 until numIters-1) {
      println("iter number: " + i + " and cost is: " + jHistory(0,i))
      jHistory(0,i) should be > jHistory(0,i+1) //cost should be descending
    }
  }

  "featureNormalize" should "normalize features" in {
    val xy:DenseMatrix[Double] = csvread(new File("/home/lhurley/git/coding-katas/ex1data2.txt"))
    val xCols = xy(::,0 to 1).toDenseMatrix.t
//    xCols(0,4) should be (3000)
//    xCols(1,3) should be (2)
    val (xNorm, mu, sigma) = scala.linearregression.Formulae.featureNormalize(xCols)
    val muFirst = mean(xNorm(::,0))
    val muSecond = mean(xNorm(::,1))
    muFirst should be(0)
    muSecond should be(0)
    val stdFirst  = stddev(xNorm(::,0))
    val stdSecond  = stddev(xNorm(::,1))
    stdFirst should beValueOne
    stdSecond should beValueOne
  }

  val beValueOne = be >= 0.999 and be <= 1.0001


}
