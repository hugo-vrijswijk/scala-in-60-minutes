import scala.util.Random

@main def matchInt() = {

  val x: Int = Random.nextInt(10)

  val result = x match {
    case 0 => "zero"
    case 1 => "one"
    case 2 => "two"
    case _ => "other"
  }
  println(result)
}

@main def matchCaseClass() = {
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
  val myPhone    = Phone("Pixel 6")
  val myComputer = Computer(List("Keyboard", "Mouse"))

  println(showDevice(myPhone))

  println(showDevice(myComputer))
}
