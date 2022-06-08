import scala.util.Random

class MatchIntTest extends munit.FunSuite {
  test("match int") {
    val x: Int = Random.nextInt(10)

    val result = x match {
      case 0 => "zero"
      case 1 => "one"
      case 2 => "two"
      case _ => "other"
    }
    println(result)
  }
}

class MatchCaseClassTest extends munit.FunSuite {
  trait Device

  case class Phone(model: String)                extends Device
  case class Computer(peripherals: List[String]) extends Device

  def showDevice(device: Device): String = {
    device match {
      case Computer(peripherals) if peripherals.length == 1 =>
        s"Your peripheral is ${peripherals.mkString(", ")}"

      case Computer(peripherals) =>
        s"Your peripherals are: ${peripherals.mkString(", ")}"

      case p: Phone =>
        s"Your phone is a ${p.model}"
    }
  }

  test("showDevice for phone") {
    val myPhone = Phone("Pixel 6")
    assertEquals(showDevice(myPhone), "Your phone is a Pixel 6")
  }

  test("showDevice for computer") {
    val myComputer = Computer(List("Keyboard", "Mouse"))
    assertEquals(showDevice(myComputer), "Your peripherals are: Keyboard, Mouse")
  }
}
