//> using lib "org.scalameta::munit::1.0.0-M1"
import scala.annotation.targetName

class SimpleExtensionTest extends munit.FunSuite {
  case class Circle(radius: Double)

  extension (c: Circle) def circumference: Double = c.radius * math.Pi * 2

  test("circumference extension") {
    assertEquals(Circle(0.5).circumference, math.Pi)
  }

}

class OperatorsTest extends munit.FunSuite {

  extension (x: String) {
    @targetName("lesserThan")
    def <(y: String): Boolean = x.compareTo(y) < 0
  }

  extension (x: Int) infix def min(y: Int): Int = if (x < y) x else y

  test("operator extension") {
    assert("ab" < "c", true)
    assertEquals(5 min 3, 3)
  }

}

class GenericsTest extends munit.FunSuite {
  extension [T](xs: List[T]) def second = xs.tail.head

  test("generic extension") {
    assertEquals(List(1, 2, 3, 4).second, 2)
  }
}
