import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object examples {

  // V1
  def add(a: Int, b: Int) = a + b

  // V2
  def addV2(a: Int, b: Int) = {
    println(s"Adding ${a} and ${b}")
    a + b
  }

  // V3
  def whatsTheTime(): Long = System.currentTimeMillis()

  // V4
  def longComputation(): Future[Int] = ???

  // V5
  def longComputationDescription(): () => Future[Int] = ???

}

object answers {

  import examples.*

  def V1OrV2 = {
    val five = add(2, 3)

    // All the same?
    val ten    = five + five
    val ten_v2 = add(2, 3) + add(2, 3)
    val ten_v3 = 5 + add(2, 3)
    val ten_v4 = 10
  }

  def V3 = {
    // Are these the same?
    val currentTime     = whatsTheTime()
    val russianRoulette = if (whatsTheTime() % 6 == 0) "BANG" else "Click"

    val russianRoulette_v2 = if (currentTime % 6 == 0) "BANG" else "Click"
  }

  def V4 = {
    val program1 = {
      for {
        a <- longComputation()
        b <- longComputation()
      } yield a + b
    }
  }

  val program2 = {
    val y = longComputation()

    for {
      a <- longComputation()
      b <- y
    } yield a + b

  }

  def V5 = {
    val program1 = {
      val x = longComputationDescription()

      for {
        a <- x()
        b <- x()
      } yield a + b
    }
  }
}
