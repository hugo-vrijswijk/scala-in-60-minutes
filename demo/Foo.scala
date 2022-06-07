object Simple {

  enum Color {
    case Red, Green, Blue
  }
}

object Parameterized {

  enum Color(val rgb: Int) {
    case Red   extends Color(0xff0000)
    case Green extends Color(0x00ff00)
    case Blue  extends Color(0x0000ff)
  }
}

object UserDefinedMembers {

  enum Planet(mass: Double, radius: Double) {
    final private val G = 6.67300e-11

    def surfaceGravity                   = G * mass / (radius * radius)
    def surfaceWeight(otherMass: Double) = otherMass * surfaceGravity

    case Mercury extends Planet(3.303e+23, 2.4397e6)
    case Venus   extends Planet(4.869e+24, 6.0518e6)
    case Earth   extends Planet(5.976e+24, 6.37814e6)
    case Mars    extends Planet(6.421e+23, 3.3972e6)
    case Jupiter extends Planet(1.9e+27, 7.1492e7)
    case Saturn  extends Planet(5.688e+26, 6.0268e7)
    case Uranus  extends Planet(8.686e+25, 2.5559e7)
    case Neptune extends Planet(1.024e+26, 2.4746e7)
  }

  def main(args: Array[String]) = {
    val earthWeight = 86

    val mass = earthWeight / Planet.Earth.surfaceGravity

    for p <- Planet.values do println(s"Your weight on $p is ${p.surfaceWeight(mass)}")
  }
}

object ADT {
  enum Tree[+T] {
    case Node(left: Tree[T], value: T, right: Tree[T])
    case Leaf

    def size: Int = this match {
      case Node(left, _, right) => 1 + left.size + right.size
      case Leaf                 => 0
    }
  }

}
