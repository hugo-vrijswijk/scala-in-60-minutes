import scala.concurrent.Future
import scala.concurrent.ExecutionContext
object FirstAttempt {

  // Create an extension for every thinkable type? Not very extensible...
  /** A Mappable is a type-class for types that can be mapped over. Such as Lists, Options, etc.
    */
  trait Mappable[A] {
    extension (m: List[A]) {
      def map[B](f: A => B): List[B] = m.map(f)
    }
    extension (m: Option[A]) {
      def map[B](f: A => B): Option[B] = m.map(f)
    }
    extension (m: Future[A])(using ExecutionContext) {
      def map[B](f: A => B): Future[B] = m.map(f)
    }

  }
}

object SecondAttempt {

  trait Mappable[F[_]] {
    extension [A](m: F[A]) {

      def map[B](f: A => B): F[B]

      // All sorts of useful things, for free!
      def tupleLeft[B](b: B): F[(B, A)]  = map(a => (b, a))
      def tupleRight[B](b: B): F[(A, B)] = map(a => (a, b))

      def lift[B](f: A => B): F[A] => F[B] =  _.map(f)

    }

  }

  // Works pretty well
  given Mappable[List] with {
    extension [A](m: List[A]) {
      def map[B](f: A => B): List[B] = m.map(f)
    }
  }
  given Mappable[Option] with {
    extension [A](m: Option[A]) {
      def map[B](f: A => B): Option[B] = m.map(f)
    }
  }
  // What about Map[K, V] ?
  // given Mappable[Map] with {
  //   extension [A](m: List[A]) {
  //     def map[B](f: A => B): List[B] = m.map(f)
  //   }
  // }
}

object ThirdAttempt {

  type MapKV = [K] =>> [V] =>> Map[K, V]

  // Or in value terms:
  // (x: K, y: V) => Map[K, V]
  // (x: K) => (y: V) => Map[K, V]

  import SecondAttempt.Mappable

  // Almost there...
  given Mappable[MapKV[String]] with {
    extension [A](m: MapKV[String][A]) {
      def map[B](f: A => B): MapKV[String][B] = m.mapValues(f).toMap
    }
  }

  given [K]: Mappable[MapKV[K]] with {
    extension [A](m: MapKV[K][A]) {
      def map[B](f: A => B): MapKV[K][B] = m.mapValues(f).toMap
    }
  }

}
