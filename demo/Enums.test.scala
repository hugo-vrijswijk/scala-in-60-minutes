//> using lib "org.scalameta::munit::1.0.0-M1"

class SimpleTest extends munit.FunSuite {

  enum Color {
    case Red, Green, Blue
  }

  test("Simple") {
    assertEquals(Color.values.toSeq, Seq(Color.Red, Color.Green, Color.Blue))
  }
}

class ParameterizedTest extends munit.FunSuite {

  enum Color(val rgb: Int) {
    case Red   extends Color(0xff0000)
    case Green extends Color(0x00ff00)
    case Blue  extends Color(0x0000ff)
  }

  test("Parameterized") {
    assertEquals(Color.Red.rgb, 0xff0000)
  }
}

class UserDefinedMembers extends munit.FunSuite {

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

  test("UserDefinedMembers") {
    assertEquals(Planet.Earth.surfaceGravity, 9.802652743337129)
  }

}

class ADTTest extends munit.FunSuite {
  enum Tree[+T] {
    case Node(left: Tree[T], right: Tree[T])
    case Leaf(elem: T)

    def size: Int = this match {
      case Node(left, right) => left.size + right.size
      case Leaf(_)           => 1
    }
  }

  test("node size") {
    val tree = Tree.Node(Tree.Node(Tree.Leaf(1), Tree.Leaf(2)), Tree.Node(Tree.Leaf(3), Tree.Leaf(4)))
    assertEquals(tree.size, 4)
  }
}
