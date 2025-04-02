//package multiplicacion
//
//import scala.annotation.tailrec
//
//object Main {
//  def main(args: Array[String]): Unit = {
//    println("Hello world!")
//
//    //    println(PeasantAlgorithmIt(74,36))
//    //    println(PeasantAlgorithmIt(0,36))
//    //    println(PeasantAlgorithmIt(2203434,6547))
//    //    println(PeasantAlgorithmIt(325677,0))
//    //    println(PeasantAlgorithmIt(356,8))
//
//    println(PeasantAlgorithm(74, 36))
//    println(PeasantAlgorithm(0, 36))
//    println(PeasantAlgorithm(2203434, 6547))
//    println(PeasantAlgorithm(325677, 0))
//    println(PeasantAlgorithm(356, 8))
//
//  }
//
//  def PeasantAlgorithmIt(a: Int, b: Int): Int = {
//    @tailrec
//    def PeasantAlgorithmItInter(a: Int, b: Int, product: Int): Int = {
//      if (a == 0) {
//        product
//      } else {
//        if (a % 2 == 0) PeasantAlgorithmItInter(a / 2, b + b, product) else PeasantAlgorithmItInter(a / 2, b + b, product + b)
//      }
//    }
//
//    PeasantAlgorithmItInter(a, b, 0)
//  }
//
//  def PeasantAlgorithm(x: Int, y: Int): Int = {
//    def makeProduct(y: Int, product: Int): Int = {
//      product + y
//    }
//
//    def PeasantAlgorithmInter(x: Int, y: Int, product: Int): Int = {
//      if (x == 0) {
//        product
//      } else {
//        if (x % 2 == 0) PeasantAlgorithmInter(x / 2, y + y, product)
//        else makeProduct(PeasantAlgorithmInter(x / 2, y + y, y), product)
//      }
//    }
//
//    PeasantAlgorithmInter(x, y, 0)
//  }
//
//}
