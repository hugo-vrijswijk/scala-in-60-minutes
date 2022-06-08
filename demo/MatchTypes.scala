object MatchTypes {

  // So much code!!!

  def firstCharOf(string: String): Char =
    if string.isEmpty then throw new NoSuchElementException
    else string.charAt(0)

  def firstElemOf[T](arr: Array[T]): T =
    if arr.isEmpty then throw new NoSuchElementException
    else arr.head

  def firstElemOf[T](iter: Iterable[T]): T = iter.head

  // More generic:
  type FirstElem[X] = X match
    case String      => Char
    case Array[t]    => FirstElem[t]
    case Iterable[t] => FirstElem[t]
    case AnyVal      => X

  val x: FirstElem[String]           = 'a'
  val y: FirstElem[Array[Int]]       = 1
  val z: FirstElem[Iterable[String]] = '1'

  def firstElem[X](x: X): FirstElem[X] = x match
    case x: String      => x.charAt(0)
    case x: Array[t]    => firstElem(x(0))
    case x: Iterable[t] => firstElem(x.head)
    case x: AnyVal      => x

  val x1: Char = firstElem("a")
  val y1: Int  = firstElem(Array(1))
  val z1: Char = firstElem(Iterable("1"))
}
