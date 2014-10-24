package math

import math.Matrix._
import org.scalatest.{FlatSpec, ShouldMatchers}

class MatrixMathTest extends FlatSpec with ShouldMatchers {

  "computeCost" should "calculate correctly" in {

    val (xMatrix,y): (MxNMatrix, MatrixRow) = setupXandY
    val XwithOnes = addOnes(xMatrix)        //(m x 2 matrix)
    val theta: MatrixRow = List(0,0)
    val J=linearregression.Formulae.computeCost(XwithOnes,y,theta)

    J should be (32.072733877455654d)
    println("Cost is: " + J)
  }

  "Matrix multiplyVector 1 x n * n x o" should "give 1 x o" in {
    val a:MxNMatrix = List(List(1,1,1),List(2,2,2))
    val b:MatrixRow= List(2,2)
    val prod:MatrixRow = Matrix.multiplyVector(b,a)
    val expected:MatrixRow = List(6d,6d,6d)
    prod should be(expected)
  }

  "gradientDescent" should "descend" in {
    val (xMatrix,y): (MxNMatrix, MatrixRow) = setupXandY
    val XwithOnes = addOnes(xMatrix)        //(m x 2 matrix)
    val theta:MatrixRow= List(0,0)
    val alpha: Double = 0.01d
    val numIters: Int = 1500
    val (jHistory, thetaNew):(MatrixRow,List[Double]) = linearregression.Formulae.gradientDescent(XwithOnes,y,theta,alpha,numIters)
    jHistory.size should be(numIters)
    for (i <- 0 until numIters-1) {
      println("iter number: " + i + " and cost is: " + jHistory(i))
      jHistory(i) should be > jHistory(i+1) //cost should be descending
    }
  }

  def addOnes(matrix:MxNMatrix) : MxNMatrix = {
    val one: Double = 1
    matrix map {row:MatrixRow => one :: row}
  }

  def getCol(x: scala.collection.immutable.List[String], colIndex: Int) = {
    x map {_.split(",").toList} map { a : scala.collection.immutable.List[String] => a(colIndex)}
  }

  def setupXandY: (MxNMatrix, MatrixRow) = {
    val source = scala.io.Source.fromFile("/home/lhurley/git/coding-katas/ex1data1.txt")
    val lines = source.getLines().toList
    val xMatrix = Matrix.transpose(List(getCol(lines,0) map {x:String => x.toDouble})) //(m x 1 matrix)
    val y = getCol(lines,1) map {x:String => x.toDouble}
    source.close()
    (xMatrix,y)
  }

}
