object InlineConstants {
  inline def pi  = 3.141592653589793
  inline def pie = "🥧"

  println(pi)
}

object InlineMethods {
  inline def logged[T](enabled: Boolean, message: String)(inline op: T): T = {
    if (enabled) println(s"Computing $message")
    val res = op
    if (enabled) println(s"Result of $message: $res")
    res
  }

  logged(false, "factorial") { (1 to 10).sum }

  logged(true, "factorial2") { (1 to 10).sum }
}

object InlineParams {
  inline def perimeter(inline radius: Double): Double = {
    2 * math.Pi * radius
  }

  val per = perimeter(3.0)
  println(per)
}

object TransparentInline {
  transparent inline def default(inline name: String): Any =
    inline if name == "Int" then 0
    else inline if name == "String" then ""
    else null

  default("Int")
  default("String")
  default("Object")
}

object InlineMatches {
  // Note the inline in the body
  transparent inline def half(x: Any): Any =
    inline x match
      case x: Int    => x / 2
      case x: String => x.substring(0, x.length / 2)

  val result = half(6)

  half("hello world")
  // half(true)
}
