//> using lib "org.scalameta::munit::1.0.0-M1"

object Configs {
  given Config = Config("localhost", 8080)
}

case class Config(host: String, port: Int)

class UsingConfigTest extends munit.FunSuite {

  def getUrl()(using config: Config) = {
    s"${config.host}:${config.port}"
  }

  test("pass given param") {
    import Configs.given

    assertEquals(getUrl(), "localhost:8080")
  }

  test("define a local given") {
    given config: Config = Config(host = "localhost", port = 443)

    assertEquals(getUrl(), "localhost:443")
  }

}

// AKA a type-class: an abstract, parameterized type that lets you add new behavior to any closed data type without using sub-typing
trait Monoid[T] {

  extension (x: T) def combine(y: T): T

  def unit: T
}

class MonoidTest extends munit.FunSuite {
  given Monoid[String] with {
    extension (x: String) def combine(y: String): String = x.concat(y)

    def unit: String = ""
  }

  given Monoid[Int] with {
    extension (x: Int) def combine(y: Int): Int = x + y

    def unit: Int = 0
  }

  def combineAll[T](list: List[T])(using monoid: Monoid[T]): T = {
    list.foldLeft(monoid.unit)(_.combine(_))
  }

  test("has a unit") {
    val result = summon[Monoid[String]].unit
    assertEquals(result, "")
  }
  test("can combine strings") {
    assertEquals("foo" combine "bar", "foobar")
  }

  test("can combine lists") {
    val result = combineAll(List(1, 2, 3))
    assertEquals(result, 6)
  }

}
