import ManiobrasTrenes._

// Aplicación de múltiples movimientos
val movimientos = List(Uno(2), Dos(3), Dos(-1), Uno(-2))
val estados = aplicarMovimientos((List('a', 'b', 'c', 'd'), Nil, Nil), movimientos)