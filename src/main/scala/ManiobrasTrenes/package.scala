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
        val (mover, quedarse) = principal.splitAt(n)
        (quedarse, uno ++ mover, dos)

      case Uno(n) if n < 0 =>
        val (mover, quedarse) = uno.splitAt(-n)
        (mover ++ principal, quedarse, dos)

      case Dos(n) if n > 0 =>
        val (mover, quedarse) = principal.splitAt(n)
        (quedarse, uno, dos ++ mover)

      case Dos(n) if n < 0 =>
        val (mover, quedarse) = dos.splitAt(-n)
        (mover ++ principal, uno, quedarse)

      case _ => e
    }
  }

  def aplicarMovimientos(e: Estado, movs: List[Movimiento]): List[Estado] = {
    movs.foldLeft(List(e)) { (estados, mov) =>
      estados :+ aplicarMovimiento(estados.last, mov)
    }
  }
}
