package object ManiobrasTrenes {
  type Vagon = Any
  type Tren = List[Vagon]
  type Estado = (Tren, Tren, Tren)

  trait Movimiento
  case class Uno(n: Int) extends Movimiento
  case class Dos(n: Int) extends Movimiento

  def aplicarMovimiento(e: Estado, m: Movimiento): Estado = {
    val (principal, uno, dos) = e
    m match {
      case Uno(n) if n > 0 =>
        val k = math.min(n, principal.length)
        (principal.dropRight(k), principal.takeRight(k) ++ uno, dos)

      case Uno(n) if n < 0 =>
        val k = math.min(-n, uno.length)
        (principal ++ uno.take(k), uno.drop(k), dos)

      case Dos(n) if n > 0 =>
        val k = math.min(n, principal.length)
        (principal.dropRight(k), uno, principal.takeRight(k) ++ dos)

      case Dos(n) if n < 0 =>
        val k = math.min(-n, dos.length)
        (dos.take(k) ++ principal, uno, dos.drop(k))

      case _ => e
    }
  }

  def aplicarMovimientos(e: Estado, movs: List[Movimiento]): List[Estado] =
    movs.foldLeft(List(e)) { (estados, mov) =>
      estados :+ aplicarMovimiento(estados.last, mov)
    }
}