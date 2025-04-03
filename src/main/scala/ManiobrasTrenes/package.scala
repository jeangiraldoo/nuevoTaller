package object ManiobrasTrenes {
  type Vagon = Any
  type Tren = List[Vagon]
  type Estado = (Tren, Tren, Tren)
  type Maniobra = List[Movimiento]

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

  def definirManiobra(t1: Tren, t2: Tren): Maniobra = {
    def aux(principal: Tren, uno: Tren, dos: Tren, objetivo: Tren, acc: Maniobra): Maniobra = {
      (objetivo.isEmpty, uno.isEmpty, dos.isEmpty) match {
        case (true, true, true) => acc.reverse

        case (false, _, _) =>
          val next = objetivo.head
          (uno.contains(next), dos.contains(next)) match {
            case (true, _) =>
              val index = uno.indexOf(next)
              if (index == 0) {
                val newAcc = Uno(-1) :: acc
                aux(next :: principal, uno.tail, dos, objetivo.tail, newAcc)
              } else {
                val moveToPrincipal = Uno(-index)
                val newState1 = aplicarMovimiento((principal, uno, dos), moveToPrincipal)
                val moveToDos = Dos(index)
                val newState2 = aplicarMovimiento(newState1, moveToDos)
                aux(newState2._1, newState2._2, newState2._3, objetivo,
                  moveToDos :: moveToPrincipal :: acc)
              }

            case (_, true) =>
              val index = dos.indexOf(next)
              if (index == 0) {
                val newAcc = Dos(-1) :: acc
                aux(next :: principal, uno, dos.tail, objetivo.tail, newAcc)
              } else {
                val moveToPrincipal = Dos(-index)
                val newState1 = aplicarMovimiento((principal, uno, dos), moveToPrincipal)
                val moveToUno = Uno(index)
                val newState2 = aplicarMovimiento(newState1, moveToUno)
                aux(newState2._1, newState2._2, newState2._3, objetivo,
                  moveToUno :: moveToPrincipal :: acc)
              }

            case _ => acc.reverse
          }

        case _ =>
          (uno.nonEmpty, dos.nonEmpty) match {
            case (true, _) =>
              val newAcc = Uno(-uno.length) :: acc
              val newState = aplicarMovimiento((principal, uno, dos), Uno(-uno.length))
              aux(newState._1, newState._2, newState._3, objetivo, newAcc)

            case (_, true) =>
              val newAcc = Dos(-dos.length) :: acc
              val newState = aplicarMovimiento((principal, uno, dos), Dos(-dos.length))
              aux(newState._1, newState._2, newState._3, objetivo, newAcc)

            case _ => acc.reverse
          }
      }
    }

    val movimientosIniciales = List(Uno(t1.length))
    val estadoInicial = aplicarMovimiento((t1, Nil, Nil), Uno(t1.length))

    aux(estadoInicial._1, estadoInicial._2, estadoInicial._3, t2, movimientosIniciales)
  }


}