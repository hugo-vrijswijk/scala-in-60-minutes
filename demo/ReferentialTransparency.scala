@main def v1 = {

  def add(a: Int, b: Int) = a + b

  val five = add(2, 3)

  // All the same?
  val ten    = five + five
  val ten_v2 = add(2, 3) + add(2, 3)
  val ten_v3 = 5 + add(2, 3)
  val ten_v4 = 10
}

@main def v2 = {

  def add(a: Int, b: Int) = {
    println(s"Adding ${a} and ${b}")
    a + b
  }

  val five = add(2, 3)

  // All the same?
  val ten    = five + five
  val ten_v2 = add(2, 3) + add(2, 3)
  val ten_v3 = 5 + add(2, 3)
  val ten_v4 = 10
}

@main def v3 = {

  def whatsTheTime(): Long = System.currentTimeMillis()

  // Are these the same?
  val currentTime     = whatsTheTime()
  val russianRoulette = if (whatsTheTime() % 6 == 0) "BANG" else "Click"

  val russianRoulette_v2 = if (currentTime % 6 == 0) "BANG" else "Click"
}

@main def v4 = {

  import scala.concurrent.Future
  import scala.concurrent.ExecutionContext.Implicits.global

  def longComputation(): Int = ???

  def program1 = {
    longComputation() + longComputation()
  }

  def program2 = {
    val y = longComputation()

    longComputation() + y
  }

}
