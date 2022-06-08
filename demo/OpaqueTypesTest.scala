object Unsafe {

  case class Person(name: String, email: String)

  def create() = {
    val email = "hugo.vanrijswijk@infosupport.com"
    val name  = "Hugo van Rijswijk"

    Person(email, name) // Oops
  }
}

object Safer {

  object Person {
    opaque type Email        = String
    opaque type Name         = String
    opaque type ErrorMessage = String

    // Only place to create Emails or Names now
    def email(email: String): Email = email
    def name(name: String): Either[ErrorMessage, Name] =
      if (name.isEmpty) Left("Name cannot be empty") else Right(name)
  }

  case class Person(name: Person.Name, email: Person.Email)

  def create() = {
    val email: String = "hugo.vanrijswijk@infosupport.com"
    val name: String  = "Hugo van Rijswijk"

    // Person(email, name)

    val opaqueName = Person.name(name) match {
      case Left(err)     => throw new Exception(err.toString) // Probbaly handle errors some other way
      case Right(result) => result
    }
    val opaqueEmail = Person.email(email)

    // Person(opaqueEmail, opaqueName)
    Person(opaqueName, opaqueEmail)

  }

}
