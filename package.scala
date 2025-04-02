package object Multiplicacion {
  def PeasantAlgorithm(x: Int, y: Int): Int = {
    if (x == 0) 0
    else if (x % 2 == 0) PeasantAlgorithm((x / 2), (y + y))
    else y + PeasantAlgorithm((x / 2), (y + y))
  }

  def PeasantAlgorithmIt(x: Int, y: Int): Int = {
    def peasantIter(x: Int, y: Int, resultado: Int): Int = {
      if (x == 0) resultado
      else if (x % 2 == 0) peasantIter(x / 2, y + y, resultado)
      else peasantIter(x / 2, y + y, resultado + y)
    }
    peasantIter(x, y, 0)
  }

  def splitMultiply(x: Int, y: Int): Int = {

    def Pow10(pot: Int): Int = (math.pow(10, pot).toInt)

    def Flen(num: Int, len: Int): Int = if (num / Pow10(len) == 0) len / 2 else Flen(num, len + 1)

    def Front(num: Int, len: Int): Int = (num / Pow10(len))

    def Back(num: Int, len: Int): Int = num % Pow10(len)

    if (x / Pow10(1) == 0) x * y
    else {
      val len = Flen(x, 1)
      splitMultiply(Front(x, len), Front(y, len)) * Pow10(2 * len) + (splitMultiply(Back(x, len), Front(y, len)) + splitMultiply(Front(x, len), Back(y, len))) * Pow10(len) + splitMultiply(Back(x, len), Back(y, len))
    }
  }

  def fastMultiply(x: Int, y: Int): Int = {
    def Pow10(pot: Int): Int = (math.pow(10, pot).toInt)

    def Flen(num: Int, len: Int): Int = if (num / Pow10(len) == 0) len / 2 else Flen(num, len + 1)

    def Front(num: Int, len: Int): Int = (num / Pow10(len))

    def Back(num: Int, len: Int): Int = num % Pow10(len)

    if (x / Pow10(1) == 0) x * y
    else {
      val len = Flen(x, 1)
      val XtimesZ = fastMultiply(Front(x, len), Front(y, len))
      val YtimesW = fastMultiply(Back(x, len), Back(y, len))
      val Subs = fastMultiply(Front(x,len)-Back(x,len),Front(y,len)-Back(y,len))
      XtimesZ * Pow10(2 * len) + (XtimesZ+YtimesW-Subs) * Pow10(len) + YtimesW
    }
  }

}
