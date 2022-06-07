@main def simpleExtension = {
  case class Circle(radius: Double)

  extension (c: Circle) def circumference: Double = c.radius * math.Pi * 2

  println(Circle(0.5).circumference)
}

@main def operators = {

  extension (x: String) def <(y: String): Boolean = x.compareTo(y) < 0

  extension (x: Int) infix def min(y: Int): Int = if (x < y) x else y

  println("ab" < "c")
  println(5 min 3)

}

@main def generics = {
  extension [T](xs: List[T]) def second = xs.tail.head

  val result = List(1, 2, 3, 4).second

  println(result)

}
