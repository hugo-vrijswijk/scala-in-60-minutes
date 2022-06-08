//> using lib "org.scalameta::munit::1.0.0-M1"

class OptionTest extends munit.FunSuite {

  test("Option.getOrElse or Some") {
    val o: Option[Int] = Some(1)
    assertEquals(o.getOrElse(0), 1)
    assertEquals(o.getOrElse(2), 1)
  }

  test("Option.getOrElse on None") {
    val o: Option[Int] = None
    assertEquals(o.getOrElse(0), 0)
    assertEquals(o.getOrElse(2), 2)
  }

  test("Option composition") {
    val result = for {
      a <- Some(1)
      b <- Some(2)
      c <- Some(3)
    } yield a + b + c

    assertEquals(result, Some(6))
  }

  test("Option composition short-circuit") {
    val result = for {
      a      <- Some(1)
      b: Int <- None
      c      <- Some(3)
      _ = println("I will never be printed")
    } yield a + b + c

    assertEquals(result, None)
  }

}

class EitherTest extends munit.FunSuite {

  case class Person(name: String, age: Int)

  def validateAge(person: Person): Either[String, Person] =
    if (person.age < 18) {
      Left(s"Age must be above 18. Was ${person.age} for ${person.name}")
    } else {
      Right(person)
    }

  test("Either composition") {
    val result = for {
      a <- Right(1)
      b <- Right(2)
      c <- Right(3)
    } yield a + b + c

    assertEquals(result, Right(6))
  }

  test("Either validation") {
    val result = for {
      p1 <- validateAge(Person("Hugo", 26))
      p2 <- validateAge(Person("Piet", 27))
      p3 <- validateAge(Person("Toon", 28))
    } yield p1.age + p2.age + p3.age

    assertEquals(result, Right(81))
  }

  test("Either validation short-circuit") {
    val result = for {
      p1 <- validateAge(Person("Hugo", 26))
      p2 <- validateAge(Person("Piet", 17))
      p3 <- validateAge(Person("Toon", 28))
    } yield p1.age + p2.age + p3.age

    assertEquals(result, Left("Age must be above 18. Was 17 for Piet"))
  }

}

class ForComprehension extends munit.FunSuite {

  test("combine types") {
    val result = for {
      a <- Set(1)
      b <- Option(2)
    } yield a + b

    assertEquals(result, Set(3))
  }
}
